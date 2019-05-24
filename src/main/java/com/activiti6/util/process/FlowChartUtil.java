package com.activiti6.util.process;

import com.activiti6.util.BpmsActivityTypeEnum;
import com.activiti6.util.UtilMisc;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.HMProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class FlowChartUtil {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    ProcessEngineFactoryBean processEngine;

    public InputStream generateStream(HttpServletRequest request, HttpServletResponse resp, String processInstanceId, boolean needCurrent) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = null;
        List<String> executedActivityIdList = new ArrayList<String>();
        List<String> currentActivityIdList = new ArrayList<>();
        List<HistoricActivityInstance> historicActivityInstanceList = new ArrayList<>();
        if (processInstance != null) {
            processDefinitionId = processInstance.getProcessDefinitionId();
            if (needCurrent) {
                currentActivityIdList = this.runtimeService.getActiveActivityIds(processInstance.getId());
            }
        }
        if (historicProcessInstance != null) {
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
            historicActivityInstanceList =
                    historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());
            }
        }

        if (StringUtils.isEmpty(processDefinitionId) || executedActivityIdList.isEmpty()) {
            return null;
        }

        //高亮线路id集合
//        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
//        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, historicActivityInstanceList);

        BpmnModel model = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
        List<String> highLightedFlows = getHighLightedFlows(model, historicActivityInstanceList,"第二种");


        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        HMProcessDiagramGenerator diagramGenerator = (HMProcessDiagramGenerator) processEngineConfiguration.getProcessDiagramGenerator();
        //List<String> activeIds = this.runtimeService.getActiveActivityIds(processInstance.getId());

        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel, "png",
                executedActivityIdList, highLightedFlows,
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(),
                "宋体",
                null, 1.0, currentActivityIdList);

        return imageStream;
    }

    /**
     * 获取需要高亮的线
     *
     * @param bpmnModel
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances) {
        // 流转线ID集合
        List<String> flowIdList = new ArrayList<String>();
        // 全部活动实例
        List<FlowNode> historicFlowNodeList = new LinkedList<FlowNode>();
        // 已完成的历史活动节点
        List<HistoricActivityInstance> finishedActivityInstanceList = new LinkedList<HistoricActivityInstance>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            historicFlowNodeList.add((FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId(), true));
            if (historicActivityInstance.getEndTime() != null) {
                finishedActivityInstanceList.add(historicActivityInstance);
            }
        }
        // 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
        FlowNode currentFlowNode = null;
        for (HistoricActivityInstance currentActivityInstance : finishedActivityInstanceList) {
            // 获得当前活动对应的节点信息及outgoingFlows信息
            currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityInstance.getActivityId(), true);
            List<SequenceFlow> sequenceFlowList = currentFlowNode.getOutgoingFlows();
            /**
             * 遍历outgoingFlows并找到已已流转的
             * 满足如下条件认为已已流转：
             * 1.当前节点是并行网关或包含网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
             * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最近的流转节点视为有效流转
             */
            FlowNode targetFlowNode = null;
            if (BpmsActivityTypeEnum.PARALLEL_GATEWAY.getType().equals(currentActivityInstance.getActivityType())
                    || BpmsActivityTypeEnum.INCLUSIVE_GATEWAY.getType().equals(currentActivityInstance.getActivityType())) {
                // 遍历历史活动节点，找到匹配Flow目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlowList) {
                    targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sequenceFlow.getTargetRef(), true);
                    if (historicFlowNodeList.contains(targetFlowNode)) {
                        flowIdList.add(sequenceFlow.getId());
                    }
                }
            } else {
                List<Map<String, String>> tempMapList = new LinkedList<Map<String,String>>();
                // 遍历历史活动节点，找到匹配Flow目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlowList) {
                    for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
                        if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
                            tempMapList.add(UtilMisc.toMap("flowId", sequenceFlow.getId(), "activityStartTime", String.valueOf(historicActivityInstance.getStartTime().getTime())));
                        }
                    }
                }
                // 遍历匹配的集合，取得开始时间最早的一个
                long earliestStamp = 0L;
                String flowId = null;
                for (Map<String, String> map : tempMapList) {
                    long activityStartTime = Long.valueOf(map.get("activityStartTime"));
                    if (earliestStamp == 0 || earliestStamp >= activityStartTime) {
                        earliestStamp = activityStartTime;
                        flowId = map.get("flowId");
                    }
                }
                flowIdList.add(flowId);
            }
        }
        return flowIdList;
    }

    public List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstances, String s)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //24小时制
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId

        for (int i = 0; i < historicActivityInstances.size() - 1; i++)
        {
            // 对历史流程节点进行遍历
            // 得到节点定义的详细信息
            FlowNode activityImpl = (FlowNode)bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());


            List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();// 用以保存后续开始时间相同的节点
            FlowNode sameActivityImpl1 = null;

            HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);// 第一个节点
            HistoricActivityInstance activityImp2_ ;

            for(int k = i + 1 ; k <= historicActivityInstances.size() - 1; k++)
            {
                activityImp2_ = historicActivityInstances.get(k);// 后续第1个节点

                if ( activityImpl_.getActivityType().equals("userTask") && activityImp2_.getActivityType().equals("userTask") &&
                        df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))   ) //都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
                {

                }
                else
                {
                    sameActivityImpl1 = (FlowNode)bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());//找到紧跟在后面的一个节点
                    break;
                }

            }
            sameStartTimeNodes.add(sameActivityImpl1); // 将后面第一个节点放在时间相同节点的集合里
            for (int j = i; j < historicActivityInstances.size() - 1; j++)
            {
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点

//                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))  )
//                {// 如果第一个节点和第二个节点开始时间相同保存
                    FlowNode sameActivityImpl2 = (FlowNode)bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
//                }
//                else
//                {// 有不相同跳出循环
//                    break;
//                }
            }
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows() ; // 取出节点的所有出去的线

            for (SequenceFlow pvmTransition : pvmTransitions)
            {// 对所有的线进行遍历
                FlowNode pvmActivityImpl = (FlowNode)bpmnModel.getMainProcess().getFlowElement( pvmTransition.getTargetRef());// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }

        }
        return highFlows;

    }
}

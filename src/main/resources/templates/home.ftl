<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>home</title>
    <script src="editor-app/libs/jquery_1.11.0/jquery.min.js"></script>
</head>
<body>
<div id="image"></div>
<img src="/processImg/15001"/>
<input type="text" id="processInstanceId"><button id="getImg">获取流程图</button>
</body>
<script>

$("#getImg").click(function(){
    $.ajax({
        type:"get",
        url:"/processImg/"+$("#processInstanceId").val(),
        success:function(data){
            console.log(data);
            $("#image").html(data);
        }
    });
});


</script>
</html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>image</title>
    <script src="editor-app/libs/jquery_1.11.0/jquery.min.js"></script>
</head>
<body>
<input type="text" id="processInstanceId"><button id="getImg">获取流程图</button>
<div id="image"></div>
<#--<img src="/processImg/25001"/>-->
</body>
<script>

$("#getImg").click(function(){
    var xhr = new XMLHttpRequest();
    xhr.open("get","/processImg/"+$("#processInstanceId").val(), true);
    xhr.responseType = "blob";
    xhr.onload = function(){
        if (this.status==200){
            var blob = this.response;
            var img = document.createElement("img");
            img.onload = function(e){
                window.URL.revokeObjectURL(img.src);
            };
            img.src = window.URL.createObjectURL(blob);
            $("#image").html(img);
        }
    }
    xhr.send();
})


    /**
     * ajax不支持流文件
      */
// $("#getImg").click(function(){
//     $.ajax({
//         type:"get",
//         url:"/processImg/"+$("#processInstanceId").val(),
//         success:function(data){
//             console.log(data);
//             $("#image").html(data);
//         }
//     });
// });


</script>
</html>
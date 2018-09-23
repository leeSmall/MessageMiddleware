<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    System.out.println(path);
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    System.out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>用户注册-异步模式</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <script type="text/javascript" src="<%--<%=basePath%>--%>resources/js/jquery-1.11.0.min.js"></script>
    <style type="text/css">
        .h1 {
            margin: 0 auto;
        }

        #producer{
            width: 48%;
            border: 1px solid blue;
            height: 80%;
            align:center;
            margin:0 auto;
        }

        body{
            text-align :center;
        }
        div {
            text-align :center;
        }
        textarea{
            width:80%;
            height:100px;
            border:1px solid gray;
        }
        button{
            background-color: rgb(62, 156, 66);
            border: none;
            font-weight: bold;
            color: white;
            height:30px;
        }
    </style>
    <script type="text/javascript">

        function send(){
            $.ajax({
                type: 'get',
                url:'<%=basePath%>saveUser?userName='+$("#userName").val()
                  +'&email='+$("#userEmail").val()+'&phoneNumber='+$("#userNumber").val(),
                dataType:'text',
                success:function(data){
                    if(data=="suc"){
                        alert("注册成功！");
                    }else{
                        alert("注册失败！");
                    }
                },
                error:function(data){
                    alert("注册错误！");
                }

            });
        }
    </script>
</head>

<body>
<h1>用户注册-异步模式</h1>
<div id="producer">
    用户姓名：<input type="text" id="userName"/>
    <br>
    用户邮件：<input type="text" id="userEmail"/>
    <br>
    用户手机：<input type="text" id="userNumber"/>
    <br>
    <button onclick="send()">注 册</button>
    <br>
</div>
</body>
</html>

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

    <title>订单提交</title>

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
                url:'<%=basePath%>confirmOrder?goodsId='+$("#goods").val()
                  +'&amount='+$("#amount").val(),
                dataType:'text',
                success:function(data){
                    if(data=="suc"){
                        alert("提交成功！");
                    }else{
                        alert("提交失败！");
                    }
                },
                error:function(data){
                    alert("提交错误！");
                }

            });
        }
    </script>
</head>

<body>
<h1>确认提交订单</h1>
<div id="producer">
    商品编号：<input type="text" id="goods"/>
    <br>
    订单数量：<input type="text" id="amount"/>
    <br>
    <button onclick="send()">确 认</button>
    <br>
</div>
</body>
</html>

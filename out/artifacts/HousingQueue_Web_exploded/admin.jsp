<%@ page import="java.util.Iterator" %>
<%@ page import="entities.FullEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 04.12.2016
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Очередь на жилье</title>
    <script src="js/jquery-3.1.1.js"></script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- main.css -->
    <link rel="stylesheet" href="css/main.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<nav role="navigation" class="navbar navbar-default">

    <div class="navbar-header">
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand"><%=request.getSession().getAttribute("current_user")%></a>
    </div>

    <div class='collapse navbar-collapse'>
        <ul class='nav navbar-nav navbar-right'>
            <li role='presentation'><a href='/signout'>Выйти</a></li>
        </ul>
    </div>

</nav>
<body>

<div class="container-fluid">
    <div class="col-lg-6 col-lg-offset-3 banner block">
        <h1>This is Admin page</h1>
    </div>
</div>

<div class="container-fluid">
    <div class="col-lg-6 col-lg-offset-3 block">
        <h3 class="form-header">Неудовлетворенные</h3>
        <div class="row" style="display: none;">
            <table id="queue" style="width: 100%;" class="table">
                <thead>
                    <tr>
                        <th>Пользователь</th>
                        <th>Количество комнат</th>
                        <th>Нужен детсад</th>
                        <th>Нужна школа</th>
                        <th>Район</th>
                        <th>Право</th>
                    </tr>
                </thead>
                <tbody>
                <%Iterator<FullEntity> outQueue = ((List<FullEntity>)session.getAttribute("out")).iterator();
                    while (outQueue.hasNext()){
                        FullEntity entity = outQueue.next();%>
                    <tr>
                        <td><%=entity.getUserName()%></td>
                        <td><%=entity.getRoomsCount()%></td>
                        <td><%=entity.isKindergarden()? "Да" : "Нет"%></td>
                        <td><%=entity.isSchool()? "Да" : "Нет"%></td>
                        <td><%=entity.getDistrict()%></td>
                        <td>Внеочередное</td>
                    </tr>
                    <%}%>
                <%Iterator<FullEntity> firstQueue = ((List<FullEntity>)session.getAttribute("first")).iterator();
                    while (firstQueue.hasNext()){
                        FullEntity entity = firstQueue.next();%>
                    <tr>
                        <td><%=entity.getUserName()%></td>
                        <td><%=entity.getRoomsCount()%></td>
                        <td><%=entity.isKindergarden()? "Да" : "Нет"%></td>
                        <td><%=entity.isSchool()? "Да" : "Нет"%></td>
                        <td><%=entity.getDistrict()%></td>
                        <td>Первоочередное</td>
                    </tr>
                    <%}%>
                <%Iterator<FullEntity> usualQueue = ((List<FullEntity>)session.getAttribute("usual")).iterator();
                    while (usualQueue.hasNext()){
                        FullEntity entity = usualQueue.next();%>
                    <tr>
                        <td><%=entity.getUserName()%></td>
                        <td><%=entity.getRoomsCount()%></td>
                        <td><%=entity.isKindergarden()? "Да" : "Нет"%></td>
                        <td><%=entity.isSchool()? "Да" : "Нет"%></td>
                        <td><%=entity.getDistrict()%></td>
                        <td>Обычное</td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

            <div class='col-lg-3 col-lg-offset-9'>
                <div class='col-sm-offset-2 col-sm-10'>
                    <input type="button" id="grant" class="btn btn-primary" value="Удовлетворить" style="margin: 10px 0 20px 0; width: 100%">
                </div>
            </div>

        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="col-lg-6 col-lg-offset-3 block">
        <h3 class="form-header">Удовлетворенные</h3>
        <div class="row" style="display: none;">
            <table id="delete-queue" style="width: 100%;" class="table">
                <thead>
                <tr>
                    <th>Пользователь</th>
                    <th>Количество комнат</th>
                    <th>Нужен детсад</th>
                    <th>Нужна школа</th>
                    <th>Район</th>
                </tr>
                </thead>
                <tbody>
                <%Iterator<FullEntity> deletedQueue = ((List<FullEntity>)session.getAttribute("del")).iterator();
                    while (deletedQueue.hasNext()){
                        FullEntity entity = deletedQueue.next();%>
                <tr>
                    <td><%=entity.getUserName()%></td>
                    <td><%=entity.getRoomsCount()%></td>
                    <td><%=entity.isKindergarden()? "Да" : "Нет"%></td>
                    <td><%=entity.isSchool()? "Да" : "Нет"%></td>
                    <td><%=entity.getDistrict()%></td>
                </tr>
                <%}%>
                </tbody>
            </table>

            <div class='col-lg-3 col-lg-offset-9'>
                <div class='col-sm-offset-2 col-sm-10'>
                    <input type="button" id="delete" class="btn btn-primary" value="Удалить" style="margin: 10px 0 20px 0; width: 100%;">
                </div>
            </div>

        </div>
    </div>
</div>



<script>
    $(document).ready(function () {
        $(".form-header").click(function () {
            $(this).next().slideToggle();
        });
        $("tbody tr").click(function () {
            if ($(this).hasClass("clicked")){
                $(this).removeClass("clicked");
            }else{
                $(this).addClass("clicked");
            }
        });
        $("#grant").click(function () {
            var m_data = "mode=normal";
            $("#queue > tbody > .clicked").each(function (i, elem) {
                m_data += "&user-" + i + "=" + $(elem).find("td:first").text();
            });

            $.ajax({
                url:"/admin",
                method:"POST",
                data:m_data,
                success:function (response) {
                    document.location.href = response;
                }
            });

        });
        $("#delete").click(function () {
            var m_data = "mode=deleted";
            $("#delete-queue > tbody > .clicked").each(function (i, elem) {
                m_data += "&user-" + i + "=" + $(elem).find("td:first").text();
            });

            $.ajax({
                url:"/admin",
                method:"POST",
                data:m_data,
                success:function (response) {
                    document.location.href = response;
                }
            });

        });
    });

</script>
</body>
</html>

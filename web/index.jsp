<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.11.2015
  Time: 0:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <link href="css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/normalize/2.0.1/normalize.css">
  <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
  <script src="js/scrip.js"></script>
  <!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
  <title>AT</title>
</head>

<body  onload="get()" >
<div class="tabbed" >

  <input type="radio" name="tabs" id="tab-nav-1" checked>
  <label for="tab-nav-1">Запрос</label>
  <input type="radio" name="tabs" id="tab-nav-2">
  <label for="tab-nav-2">Ответ</label>
  <input type="radio" name="tabs" id="tab-nav-3">
  <label for="tab-nav-3">История</label>
  <form>
    <input placeholder="Поиск" type="search" class="search" >

  </form>
  <div class="tabs">
    <div>
      <form action="login" method="get">
        <h1>Проверка пользователя</h1>
        <input type="text" name="login" placeholder="Login">
        <input type="text" name="password" placeholder="Password">
        <input type="submit" >
      </form>
      <hr>

      <table style="width: 100%">
        <tr>
          <td>iD</td>
          <td>Login</td>
          <td>Password</td>
          <td>First Name</td>
          <td>Last Name</td>
        </tr>
        <c:forEach items="${Users}" var="user">
          <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
          </tr>

        </c:forEach>

      </table>
    </div>
    <div>

    </div>
    <div><table style="width: 100%">
      <thead>
      <tr>
        <td>Имя</td>
        <td>Пол</td>
        <td>Дата рождения</td>
        <td>Номер телефона</td>
      </tr>
      </thead>
      <tbody id="tb">
      </tbody>
    </table>
    </div>
  </div>
</div>
</body>
</html>

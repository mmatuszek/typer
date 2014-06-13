<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico" />

<title>Typer</title>
<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/docs.min.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="resources/css/typer.css" rel="stylesheet" />

<!-- jQuery -->
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery.formatDateTime.min.js"></script>

<!-- Custom JS -->
<script type="text/javascript" src="resources/js/typer.match.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body data-twttr-rendered="true">
	<header id="top" class="navbar navbar-static-top bs-docs-nav"
		role="banner">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" data-target=".bs-navbar-collapse"
					data-toggle="collapse" type="button">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<nav class="collapse navbar-collapse bs-navbar-collapse"
				role="navigation">
				<ul class="nav navbar-nav">
					<li><a href="">Typowanie</a></li>
					<li class="active"><a href="ranking">Ranking</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" data-toggle="dropdown"
						role="button"> <%=request.getUserPrincipal().getName()%></a>
						<ul id="menu3" class="dropdown-menu" aria-labelledby="drop6"
							role="menu">
							<li role="presentation"><a id="logout" href="#"
								role="menu-item">Wyloguj</a></li>
						</ul></li>
				</ul>
			</nav>
		</div>
	</header>
	<div id="banner" class="bs-docs-header">
		<div class="container">
			<div class="col-md-2" id="fuleco">
				<img src="resources/images/fuleco.png" style="height: 200px" />
			</div>
			<div class="col-md-8" id="banner-text">
				<h1>Ranking</h1>
				<p>Aktualna punktacja uczestników zabawy</p>
			</div>
			<div class="col-md-2" id="cup">
				<img src="resources/images/cup.png" style="height: 200px" />
			</div>
		</div>
	</div>

	<div class="container">
		<div id="messages"></div>
		<table class="table" id="ranking">
		<thead>
			<tr>
				<th style="width: 50px">Pozycja</th>
				<th>Imię i nazwisko</th>
				<th>Login</th>
				<th style="width: 100px">Trafione wyniki</th>
				<th style="width: 100px">Trafione zwycięstwa/remisy</th>
				<th style="width: 100px">Trafiony mistrz</th>
				<th style="width: 100px">Punkty</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ranking}" var="entry" varStatus="position" >
				<tr>
					<td<c:if test="${position.index == 0 }"> class="success"</c:if>>${position.index + 1}</td>
					<td<c:if test="${position.index == 0 }"> class="success"</c:if>>${entry.userName }</td>
					<td<c:if test="${position.index == 0 }"> class="success"</c:if>>${entry.userLogin }</td>
					<td<c:if test="${position.index == 0 }"> class="success"</c:if>>${entry.exactScorePoints }</td>
					<td<c:if test="${position.index == 0 }"> class="success"</c:if>>${entry.wldScorePoints }</td>
					<td<c:if test="${position.index == 0 }"> class="success"</c:if>>${entry.winnerPoints }</td>
					<td<c:if test="${position.index == 0 }"> class="success"</c:if>><span class="label label-success totalScore">${entry.totalPoints }</span></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		
		</div>
	</div>
	<!-- /container -->
</body>
</html>
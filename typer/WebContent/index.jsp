<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<body>
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
				<a class="navbar-brand" href="../">Bootstrap</a>
			</div>
			<nav class="collapse navbar-collapse bs-navbar-collapse"
				role="navigation">
				<ul class="nav navbar-nav">
					<li class="active"></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li></li>
				</ul>
			</nav>
		</div>
	</header>
	<div id="content" class="bs-docs-header">
		<div class="container">
			<p>Zabawa w typowanie wyników Mistrzostw Świata w Brazylii</p>
			<div id="carbonads-container">
				<div class="carbonad"></div>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="header" class="panel panel-default">
			<img src="resources/images/busy_30.gif">
		</div>
		<div id="messages"></div>
		<div id="matches">
			<img src="resources/images/busy_80.gif">&nbsp;
		</div>
	</div>
	<div class="modal fade" id="match-bets" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">...</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="winner-bets" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">...</div>
			</div>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
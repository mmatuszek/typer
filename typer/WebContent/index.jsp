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
					<li class="active"><a href="#">Typowanie</a></li>
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
				<h1>Typowanie</h1>
				<p>Wytypuj wyniki
					meczów oraz zwycięzcę turnieju. Zobacz jak typowali inni.</p>
			</div>
			<div class="col-md-2" id="cup">
				<img src="resources/images/cup.png" style="height: 200px" />
			</div>
		</div>
	</div>

	<div id="bet-tabs" class="container">
		<div id="messages"></div>
		<ul class="nav nav-tabs">
			<li class="active"><a id="future-tab" href="#future">Zaplanowane</a></li>
			<li><a id="current-tab" href="#current">Trwające</a></li>
			<li><a id="finished-tab" href="#finished">Zakończone</a></li>
			<li><a id="winner-tab" href="#winner">Mistrz</a></li>
		</ul>
		<div id="bet-tab-content" class="tab-content">
			<div id="future" class="tab-pane fade active in">
				<table class="table-borderless" id="future-matches">
				</table>
			</div>
			<div id="current" class="tab-pane fade">
				<table class="table-borderless" id="current-matches">
				</table>
			</div>
			<div id="finished" class="tab-pane fade">
				<table class="table-borderless" id="finished-matches">
				</table>
			</div>
			<div id="winner" class="tab-pane fade">
				<div id="winner-bet">
					<div style="text-align: center">
						<img src="resources/images/busy_80.gif">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="match-bets" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Typy wszystkich
						graczy</h4>
				</div>
				<div class="modal-body">
					<table class="table-borderless">
						<tr>
							<td style="text-align: center"><img
								src="resources/images/busy_80.gif"></td>
						</tr>
					</table>
				</div>
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
					<h4 class="modal-title" id="myModalLabel">Mistrzostwo Świata -
						Typy</h4>
				</div>
				<div class="modal-body">
					<table class="table-borderless">
						<tr>
							<td style="text-align: center"><img
								src="resources/images/busy_80.gif"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
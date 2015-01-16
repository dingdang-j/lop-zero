<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>Lop - <sitemesh:title /></title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">

<link rel="icon" href="../../favicon.ico">
<link type="text/css" rel="stylesheet" href="/static/plugins/bootstrap/3.3.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/static/plugins/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<link type="text/css" rel="stylesheet" href="/static/styles/default.css">
<!--[if lt IE 9]>
      <script src="/static/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="/static/plugins/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<sitemesh:head />
</head>

<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/WEB-INF/layouts/sidebar.jsp"%>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<sitemesh:body />
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/layouts/footer.jsp"%>

	<script type="text/javascript" src="/static/plugins/jquery/1.11.1/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/static/plugins/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
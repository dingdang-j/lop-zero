<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<html>
<head>
<title>银行管理</title>
</head>

<body>

	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">银行管理</a></li>
		<li class="active">列表</li>
	</ol>

	<c:if test="${not empty messageSuccess}">
		<div id="messageSuccess" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${messageSuccess }
		</div>
	</c:if>
	<c:if test="${not empty messageError}">
		<div id="messageError" class="alert alert-warning">
			<button data-dismiss="alert" class="close">×</button>${messageError }
		</div>
	</c:if>

	<form class="navbar-form navbar-left" action="bank" method="post">
		<div class="form-group">
			<label class="sr-only">编号</label> <input type="text" class="form-control" placeholder="编号" name="query_LIKE_S_code" value="${param['query_LIKE_S_code'] }" />
		</div>
		<div class="form-group">
			<label class="sr-only">名称</label> <input type="text" class="form-control" placeholder="名称" name="query_LIKE_S_name" value="${param['query_LIKE_S_name'] }" />
		</div>
		<div class="form-group">
			<label class="sr-only">大于创建日期</label> <input type="text" class="form-control" placeholder="大于创建日期" name="query_LE_DATE_createTime" value="${param['query_LE_DATE_createTime'] }" />
		</div>
		<div class="form-group">
			<label class="sr-only">小于创建日期</label> <input type="text" class="form-control" placeholder="小于创建日期" name="query_GE_DATE_createTime" value="${param['query_GE_DATE_createTime'] }" />
		</div>

		<button type="submit" class="btn btn-default">查询</button>
		<button type="button" class="btn btn-info" onclick="javascript: location.href = 'bank!input'">新增</button>
	</form>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>资产</th>
				<th>备注</th>
				<th>创建时间</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pageable.content }" var="vo">
				<tr>
					<td>${vo.code }</td>
					<td>${vo.name }</td>
					<td>${vo.asset }</td>
					<td>${vo.remark }</td>
					<td>${vo.createTime }</td>
					<td><button type="button" class="btn btn-info btn-xs" onclick="javascript: location.href = 'bank!input?id=${vo.id }'">修改</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<tags:pagination page="${pageable }" paginationSize="5" />

</body>
</html>
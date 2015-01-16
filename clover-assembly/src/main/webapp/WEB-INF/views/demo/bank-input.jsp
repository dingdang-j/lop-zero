<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>银行信息</title>
</head>

<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">银行管理</a></li>
		<c:choose>
			<c:when test="${id == null }">
				<li class="active">新增</li>
			</c:when>
			<c:otherwise>
				<li class="active">修改</li>
			</c:otherwise>
		</c:choose>
	</ol>

	<form action="bank!save" method="post">
		<div class="form-group">
			<label>名称</label> <input type="text" name="name" value="${name }" class="form-control" placeholder="名称" />
		</div>
		<div class="form-group">
			<label>资产</label> <input type="text" name="asset" value="${asset }" class="form-control" placeholder="资产" />
		</div>
		<div class="form-group">
			<label>备注</label>
			<textarea name="remark" class="form-control" rows="3">${remark }</textarea>
		</div>

		<button type="submit" class="btn btn-success">提交</button>
		<button type="button" class="btn btn-default" onclick="javascript: history.back();">返回</button>

		<input type="hidden" name="id" value="${id }" />
	</form>
</body>
</html>
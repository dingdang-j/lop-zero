<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	int current = page.getNumber() + 1;
	int begin = Math.max( 1, current - paginationSize / 2 );
	int end = Math.min( begin + ( paginationSize - 1 ), page.getTotalPages() );

	if ( ( end >= paginationSize ) && ( end - begin < paginationSize ) ) {
		begin = end - paginationSize + 1;
	}

	request.setAttribute( "current", current );
	request.setAttribute( "begin", begin );
	request.setAttribute( "end", end );
%>

<c:if test="${page.hasContent() }">
	<nav>
		<ul class="pagination">
			<%
				if ( page.hasPreviousPage() ) {
			%>
			<li><a href="?page=0&${queryParams }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			<li><a href="?page=${current - 2 }&${queryParams }" aria-label="Previous"><span aria-hidden="true">&lsaquo;</span></a></li>
			<%
				} else {
			%>
			<li class="disabled"><a href="javascript: void( 0 );" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			<li class="disabled"><a href="javascript: void( 0 );" aria-label="Previous"><span aria-hidden="true">&lsaquo;</span></a></li>
			<%
				}
			%>

			<c:forEach var="index" begin="${begin }" end="${end }">
				<c:choose>
					<c:when test="${index == current }">
						<li class="active"><a href="javascript: void( 0 );">${index }<span class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="?page=${index - 1 }&${queryParams }">${index }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<%
				if ( page.hasNextPage() ) {
			%>
			<li><a href="?page=${current }&${queryParams }" aria-label="Next"><span aria-hidden="true">&rsaquo;</span></a></li>
			<li><a href="?page=${page.totalPages - 1 }&${queryParams }" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			<%
				} else {
			%>
			<li class="disabled"><a href="javascript: void( 0 );" aria-label="Next"><span aria-hidden="true">&rsaquo;</span></a></li>
			<li class="disabled"><a href="javascript: void( 0 );" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			<%
				}
			%>
		</ul>
	</nav>
</c:if>
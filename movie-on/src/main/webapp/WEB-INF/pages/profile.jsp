<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<body>


	<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				Hello User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
	<c:forEach var="movie" items="${movieDetails}">

		<div class="row">
			<tr>
				<td><h4>Movie Name: ${movie.moviename}</h4></td>
			</tr>
			<tr>
				<c:forEach var="video" items="${movie.videoresult}">
					<td><iframe title="YouTube video player"
							class="youtube-player" type="text/html"
							src="http://www.youtube.com/embed/${video.videoId}"
							frameborder="0" allowFullScreen></iframe></td>
				</c:forEach>
			</tr>

		</div>
	</c:forEach>
</body>
</html>
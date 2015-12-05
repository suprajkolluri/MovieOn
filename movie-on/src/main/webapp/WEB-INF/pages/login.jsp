<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page session="true"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Login</title>

<!-- Bootstrap Core CSS -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">



<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/business-casual.css"
	rel="stylesheet">

<!-- Fonts -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="brand">MovieOn</div>
	<div class="address-bar">Highly-Rated, Latest Movies</div>

	<!-- Navigation 
	<h5>Looking for a <em>good</em> movie but you don't know what to watch? </h5>
    <nav class="navbar navbar-default" role="navigation">-->

	<!-- /.container -->
	</nav>

	<div class="container" height="10">

		<div class="row">
			<div class="box">
				<div class="col-lg-12 text-center">
					<div id="carousel-example-generic" class="carousel slide">
						<!-- Indicators -->
						<ol class="carousel-indicators hidden-xs">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>


						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<div class="item active">
								<img class="img-responsive img-full"
									src="${pageContext.request.contextPath}/img/slide1.jpg" alt="">
							</div>
							<div class="item">
								<img class="img-responsive img-full"
									src="${pageContext.request.contextPath}/img/slide2.jpg" alt="">
							</div>
							<div class="item">
								<img class="img-responsive img-full"
									src="${pageContext.request.contextPath}/img/slide3.jpg" alt="">
							</div>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							data-slide="prev"> <span class="icon-prev"></span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" data-slide="next"> <span
							class="icon-next"></span>
						</a>
					</div>
					<h2 class="brand-before">
						<small>Welcome!!</small>
					</h2>

					<hr class="tagline-divider">
					<h2>
						<small>

							<center>
								<div id="left">
									<form name='loginForm' action="<c:url value='/login' />"
										method='POST'>

										<table>

											<tr>
												<td>User Name:</td>
												<td><input type='text' name='username'></td>
											</tr>
											<tr>
												<td></td>
											</tr>
											<tr>
												<td>Password:</td>
												<td><input type='password' name='password' /></td>
											</tr>

											<tr>
												<td colspan='2'><input name="submit" type="submit"
													value="Login" /></td>
											</tr>

										</table>

										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />

									</form>
								</div>
							</center>
							<center>
								<div id="center">
									<form name='trendForm' action="trends" id="trend">
										<strong>Want to view the trending movies?? </strong> <input
											name="submit" type="submit" value="Click Here" />


									</form>
								</div>
							</center>
						</small>
					</h2>
				</div>
			</div>
		</div>





	</div>
	<!-- /.container -->


	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>

</body>

</html>

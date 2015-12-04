<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>


<head>
<title>Movie Trends</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Movie Trends</h2>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#home">Horror</a></li>
			<li><a data-toggle="tab" href="#menu1">Action</a></li>
			<li><a data-toggle="tab" href="#menu2">Comedy</a></li>
			<li><a data-toggle="tab" href="#menu3">Adventure</a></li>
			<li><a data-toggle="tab" href="#menu4">Animation</a></li>
		</ul>

		<div class="tab-content">
			<div id="home" class="tab-pane fade in active">
				<c:forEach var="movie1" items="${horrorList}">

					<div class="row">
						<table>
							<tr>
								<td><h4>Movie Name: ${movie1.moviename}</h4></td>
							</tr>
							<tr>
								<c:forEach var="video1" items="${movie1.videoresult}">
									<td><iframe title="YouTube video player"
											class="youtube-player" type="text/html"
											src="http://www.youtube.com/embed/${video1.videoId}"
											frameborder="0" allowFullScreen></iframe></td>
								</c:forEach>
							</tr>
						</table>

					</div>
				</c:forEach>
			</div>
			<div id="menu1" class="tab-pane fade">
				<c:forEach var="movie2" items="${actionList}">

					<div class="row">
						<table>
							<tr>
								<td><h4>Movie Name: ${movie2.moviename}</h4></td>
							</tr>
							<tr>
								<c:forEach var="video2" items="${movie2.videoresult}">
									<td><iframe title="YouTube video player"
											class="youtube-player" type="text/html"
											src="http://www.youtube.com/embed/${video2.videoId}"
											frameborder="0" allowFullScreen></iframe></td>
								</c:forEach>
							</tr>
						</table>

					</div>
				</c:forEach>
			</div>
			<div id="menu2" class="tab-pane fade">
				<c:forEach var="movie3" items="${comedyList}">

					<div class="row">
						<table>
							<tr>
								<td><h4>Movie Name: ${movie3.moviename}</h4></td>
							</tr>
							<tr>
								<c:forEach var="video3" items="${movie3.videoresult}">
									<td><iframe title="YouTube video player"
											class="youtube-player" type="text/html"
											src="http://www.youtube.com/embed/${video3.videoId}"
											frameborder="0" allowFullScreen></iframe></td>
								</c:forEach>
							</tr>
						</table>

					</div>
				</c:forEach>
			</div>
			<div id="menu3" class="tab-pane fade">
				<c:forEach var="movie4" items="${adventureList}">

					<div class="row">
						<table>
							<tr>
								<td><h4>Movie Name: ${movie4.moviename}</h4></td>
							</tr>
							<tr>
								<c:forEach var="video4" items="${movie4.videoresult}">
									<td><iframe title="YouTube video player"
											class="youtube-player" type="text/html"
											src="http://www.youtube.com/embed/${video4.videoId}"
											frameborder="0" allowFullScreen></iframe></td>
								</c:forEach>
							</tr>
						</table>

					</div>
				</c:forEach>
			</div>
		</div>
		<div id="menu4" class="tab-pane fade">
			<c:forEach var="movie5" items="${animationList}">

				<div class="row">
					<table>
						<tr>
							<td><h4>Movie Name: ${movie5.moviename}</h4></td>
						</tr>
						<tr>
							<c:forEach var="video5" items="${movie5.videoresult}">
								<td><iframe title="YouTube video player"
										class="youtube-player" type="text/html"
										src="http://www.youtube.com/embed/${video5.videoId}"
										frameborder="0" allowFullScreen></iframe></td>
							</c:forEach>
						</tr>
					</table>

				</div>
			</c:forEach>
		</div>
	</div>
	</div>

</body>
</html>

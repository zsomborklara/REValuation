<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Real Estate Valuation Demo</title>

<spring:url value="/resources/core/css/bootstrap.min.css"	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<spring:url value="/resources/core/js/bootstrap.min.js"  var="bootstrapJs" />
<script src="${bootstrapJs}"></script>
</head>

<spring:url value="/rev/estates" var="urlList" />
<spring:url value="/rev/estates/new" var="urlNewRealEstate" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlList}">Real Estate Listing</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlNewRealEstate}">Add Real Estate</a></li>
			</ul>
		</div>
	</div>
</nav>
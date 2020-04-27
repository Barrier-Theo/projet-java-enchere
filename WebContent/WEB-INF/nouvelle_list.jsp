<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="messages.LecteurMessage" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Application de démonstration</title>

    <!-- Bootstrap core CSS -->
   	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
     	  integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
          crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  
<!--[if gte mso 9]><xml>
<mso:CustomDocumentProperties>
<mso:_dlc_DocId msdt:dt="string">Z5HNVW24N33T-678105430-3967</mso:_dlc_DocId>
<mso:_dlc_DocIdItemGuid msdt:dt="string">6d0fcb7b-0299-4b95-9758-282cd8fa360b</mso:_dlc_DocIdItemGuid>
<mso:_dlc_DocIdUrl msdt:dt="string">http://inet/eni-transverse/ecole-num�rique/_layouts/15/DocIdRedir.aspx?ID=Z5HNVW24N33T-678105430-3967, Z5HNVW24N33T-678105430-3967</mso:_dlc_DocIdUrl>
</mso:CustomDocumentProperties>
</xml><![endif]-->
</head>

  <body class="container">
	<header class="py-3 bg-dark header-demodule fixed-top">
		<div class="container text-center text-white">
			<h1>Courses</h1>
		</div>
	</header>
    
	<div class="col-12">
		<h2 class="my-5 text-center">Nouvelle liste</h2>
	
		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>
      	<form class="row justify-content-center mb-2" action="${pageContext.request.contextPath}/ServletNouvelleListe" method="post">
      		<label for="nomListe" class="col-2 col-form-label">Nom :</label>
  			<div class="col-10">
					<input class="form-control"  name="nomListe" type="text" id="nom">
			</div>
			<br></br>
			<label for="nomArticle" class="col-2 col-form-label">Article</label>
			<div class="row">
				<ul class="list-group col-12">
						<li class="list-group-item d-flex justify-content-between align-items-center">
						<input class="form-control" name="nomArticle" type="text" id="nomArticle">
							<div>
								<input class="btn btn-primary" type="submit" value="Valider">
							</div>
					</li>
				</ul>
				
			</div>

		</form>


		<!--  <div class="row">
			<ul class="list-group col-12">
				<li class="list-group-item d-flex justify-content-between align-items-center">Article A
					<div>
						<a href="#supprimer" class="badge text-danger" title="Supprimer"><i class="material-icons">delete</i></a>
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-center">Article B
					<div>
						<a href="#supprimer" class="badge text-danger" title="Supprimer"><i class="material-icons">delete</i></a>
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-center">Article C
					<div>
						<a href="#supprimer" class="badge text-danger" title="Supprimer"><i class="material-icons">delete</i></a>
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-center">Article D
					<div>
						<a href="#supprimer" class="badge text-danger" title="Supprimer"><i class="material-icons">delete</i></a>
					</div>
				</li>
				<li class="list-group-item d-flex justify-content-between align-items-center">
					<input class="form-control" type="text" id="nom">
					<div>
						<a href="#add" class="badge text-success" title="Ajouter"><i class="material-icons">add</i></a>
					</div>
				</li>
			</ul>
		</div>-->


		<div class="row py-5"></div>
	</div>
	
	
	
    <!-- Footer -->
	<footer class="row bg-dark footer-demodule fixed-bottom py-1">
		<div class="col-lg-4 offset-lg-4 text-center">
			<a class="btn" href="${pageContext.request.contextPath}/ServletListAccueil" title="Retour à la liste des courses"><i class="material-icons">arrow_back</i></a>
		</div>
		<!-- /.container -->
	</footer>
  </body>

</html>

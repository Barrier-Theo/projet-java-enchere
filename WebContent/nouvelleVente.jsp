<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="messages.LecteurMessage" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="fr">

<head>
  <meta charset="UTF-8">

    <title>Nouvelle Vente</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    

</head>


<body>
	<div class="container">
	  <div class="row">
		<div class="col-sm">
			<div class="col-xs-8 col-sm-10">
				<img src="background.jpg" alt="..." class="img-thumbnail">
			</div>
		</div>
		
		<div class="col-sm">
			<form class="form-nouvelArticle" action="${pageContext.request.contextPath}/ServletNouvelArticleVendu" method="POST">
				<div class="form-group row">
					<label class="col-sm-5 col-form-label">Article</label>
					<div class="col-sm-7">
					  <input required class="form-control" name="libelleArticle">
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-5 col-form-label">Description</label>
					<div class="col-sm-7">
						<textarea required class="form-control" rows="3" name="descriptionArticle"></textarea>
					</div>
				</div>
			  
				<div class="form-group row">
					<label class="col-sm-5 col-form-label">Catégorie</label>
					<div class="col-sm-7">
					  <select class="form-control" name="idCategorie">
						<c:forEach items="${listeCategories}" var="uneCategorie">
							<option value="${uneCategorie.noCategorie}">${uneCategorie.libelle}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				
			  	<div class="form-group row">
					<label class="col-sm-5 col-form-label">Mise à  prix</label>
					<div class="col-sm-7">
					  <input required type="number" class="form-control" name="prixDepartArticle">
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-5 col-form-label">Photo de l'article</label>
					<div class="col-sm-7">
					  <input type="file" class="form-control-file" name="cheminPhotoArticle">
					</div>
				</div>
			  
			  	<div class="form-group row">
					<label class="col-sm-5 col-form-label">Début de l'enchère:</label>
					<div class="col-sm-7">
					  <input  required type="date" name="dateDebutArticle">
					</div>
				</div>
			  
			  	<div class="form-group row">
					<label class="col-sm-5 col-form-label">Fin de l'enchère:</label>
					<div class="col-sm-7">
					  <input required type="date" name="dateFinArticle">
					</div>
				</div>
				
				
				<span class="border border-primary form-group row">
					
					<label class="col-sm-12 col-form-label">Retrait</label>
					<c:forEach items="${unUtilisateur}" var="utilisateur">
						<div class="form-group row">
							<label class="col-sm-5 col-form-label ">&nbsp; Rue:</label>
							<div class="col-sm-7">
								<input class="form-control" name="rueArticle" value="${utilisateur.rue}">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-sm-5 col-form-label ">&nbsp; Code Postal:</label>
							<div class="col-sm-7">
								<input class="form-control" name="codePostalArticle" value="${utilisateur.codePostal}">
							</div>
						</div>
						
						<div class="form-group row">
							<label class="col-sm-5 col-form-label ">&nbsp; Ville:</label>
							<div class="col-sm-7">
								<input class="form-control" name="villeArticle" value="${utilisateur.ville}">
							</div>
						</div>
					</c:forEach>
				</span>
				
				
			  <button type="submit" class="btn btn-success">Enregistrer</button>
			  <button class="btn btn-danger">Annuler</button>
			</form>
			
		</div>
		
		<div class="col-sm">
		  
		</div>
	  </div>
	</div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="messages.LecteurMessage" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/Head.jsp"%>
<%@include file="fragments/Menu.jsp"%>

	<div class="container">
	  <div class="row">
	  <div class="col" style="text-align:center">
			<h3 style="padding:20px;">Nouvel article</h3>
		</div>
	  
		  <div class="col-12">
		  
			  <form class="form-nouvelArticle" action="${pageContext.request.contextPath}/ServletNouvelArticleVendu" method="POST">
				  <div class="row marginbottom">
				  
				  	<div class="col-6">
				  	
				  	<div style="margin-top:0px" class="card bg-secondary text-white">
					  <div class="card-header">Informations de l'article</div>
					  <div class="card-body">
					  
					  	<div class="form-group row">
							<label class="col-sm-5 col-form-label">Article</label>
							<div class="col-sm-7">
							  <input required class="form-control" name="libelleArticle">
							</div>
						</div>
					  
					    <div class="form-group row">
								<label class="col-sm-5 col-form-label">Description :</label>
								<div class="col-sm-7">
									<textarea required class="form-control" rows="3" name="descriptionArticle"></textarea>
								</div>
							</div>
						  
							<div class="form-group row">
								<label class="col-sm-5 col-form-label">Catégorie :</label>
								<div class="col-sm-7">
								  <select class="form-control" name="idCategorie">
									<c:forEach items="${listeCategories}" var="uneCategorie">
										<option value="${uneCategorie.noCategorie}">${uneCategorie.libelle}</option>
									</c:forEach>
								</select>
								</div>
							</div>
							
						  	<div class="form-group row">
								<label class="col-sm-5 col-form-label">Mise à  prix :</label>
								<div class="col-sm-7">
								  <input required type="number" class="form-control" name="prixDepartArticle">
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-5 col-form-label">Photo de l'article :</label>
								<div class="col-sm-7">
								  <input type="file" class="form-control-file" name="cheminPhotoArticle">
								</div>
							</div>
						  
						  	<div class="form-group row">
								<label class="col-sm-5 col-form-label">Début de l'enchère :</label>
								<div class="col-sm-7">
								  <input id="datepicker" required type="date" name="dateDebutArticle">
								</div>
							</div>
						  
						  	<div class="form-group row">
								<label class="col-sm-5 col-form-label">Fin de l'enchère :</label>
								<div class="col-sm-7">
								  <input required type="date" name="dateFinArticle">
								</div>
							</div>
						</div>
					</div>
				</div>

					  	
					  	<div class="col-6">
					  	
					  		<div class="col-12 ">
								<img style="margin-bottom:10px;" src="theme/images/article.png" align="center">
							</div>
							
							<div class="col-12 ">
							<div style="margin-top:0px" class="card text-white bg-secondary">
								<c:forEach items="${unUtilisateur}" var="utilisateur">
								  <div class="card-header">Retrait</div>
								  <div class="card-body">
								    <div class="form-group row">
											<label class="col-sm-5 col-form-label ">&nbsp; Rue:</label>
											<div class="col-sm-7">
												<input required class="form-control" name="rueArticle" value="${utilisateur.rue}">
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-sm-5 col-form-label ">&nbsp; Code Postal:</label>
											<div class="col-sm-7">
												<input required class="form-control" name="codePostalArticle" value="${utilisateur.codePostal}">
											</div>
										</div>
										
										<div class="form-group row">
											<label class="col-sm-5 col-form-label ">&nbsp; Ville:</label>
											<div class="col-sm-7">
												<input required class="form-control" name="villeArticle" value="${utilisateur.ville}">
											</div>
										</div>
								  </div>
								</c:forEach>
							</div>
							</div>
								
					  	</div>
					  
					  </div>
				
					<div class="row">
						<div class="offset-4 col-2">
							<button type="submit" class="btn btn-success">Enregistrer</button>
						</div>
						<div class="col-2">
					  		<a href="${pageContext.request.contextPath}/ServletAccueil" class="btn btn-danger">Annuler</a>
						</div>
					</div>
					</form>
				
				</div>
		
	  	</div>
	</div>

<%@include file="fragments/Bottom.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/Head.jsp"%>
<%@include file="fragments/Menu.jsp"%>

	<div class="container">
	  <div class="row">
		<div class="col" style="text-align:center;margin-top:20px;">
			<h3>Liste des enchères</h3>
		</div>
	  </div>
	  
	  <div class="container">
	  	<div class="row">
		<div class="col-6">
			<b>Filtres :</b>
			<input type="text" class="form-control" id="nomArticle" placeholder="Le nom de l'article contient">
			<br/>
			<b>Catégorie :</b>
			<select class="form-control" name="idCategorie">
				<c:forEach items="${listeCategories}" var="uneCategorie">
					<option value="${uneCategorie.noCategorie}">${uneCategorie.libelle}</option>
				</c:forEach>
			</select>
			<br/>
		</div>
		
		<div class="col-6" style="margin-top:25px;">
			
			<c:if test="${id != null}">
			
				<div class="radio">
					<div class="row">
						<div class="col">
							<label><input type="radio" class="achat" name="optradio" checked>Achats</label>
							<div class="checkedAchat">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input group1" id="checkedEncheresOuvertes">
									<label class="custom-control-label" for="checkedEncheresOuvertes">Enchères ouvertes</label>
								</div>
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input group1" id="checkedEncheresEnCours">
									<label class="custom-control-label" for="checkedEncheresEnCours">Mes enchères en cours </label>
								</div>
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input group1" id="checkedEncheresRemportees">
									<label class="custom-control-label" for="checkedEncheresRemportees">Mes enchères remportées</label>
								</div>
							</div>
						</div>
						
						<div class="col">
							<label><input type="radio" class="mesVentes" name="optradio">Mes Ventes</label>
							<div class="checkedVentes">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input group2" id="checkedVentesOuvertes">
									<label class="custom-control-label" for="checkedVentesOuvertes">Mes Ventes en cours</label>
								</div>
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input group2" id="checkedVentesNondebutees">
									<label class="custom-control-label" for="checkedVentesNondebutees">Ventes non débutées</label>
								</div>
								<div class="custom-control custom-checkbox">							
									<input type="checkbox" class="custom-control-input group2" id="checkedVentesTerminees">
									<label class="custom-control-label" for="checkedVentesTerminees">Ventes terminées</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</c:if>
			
			</div>
			<div class="offset-5 col-1">
				<button type="button" class="btn btn-primary btn-lg">Rechercher</button>
			</div>
		</div>
	  </div>
	  
		
		<div class="container">
			<div class="row">
				<c:forEach items="${listeArticle}" var="unArticle">
					<div class="col-4" style="margin-bottom:20px;">
						<div class="card">
						  <img class="card-img-top" src="theme/images/article.png" alt="Card image cap">
						  <div class="card-body">
						    
							<h4 class="card-title">${unArticle.nomArticle}</h4>
							
							<c:forEach items="${listeUtilisateur}" var="unUtilisateur">
								<c:if test="${unUtilisateur.id == unArticle.noUtilisateur}">
									<h6 class="card-subtitle mb-2 text-muted">Vendu par : ${unUtilisateur.pseudo}</h6>
	                    		</c:if>
	                		</c:forEach>
	                		
	                		<p class="card-text">${unArticle.description}</p>
	                		
							<c:if test="${listeEnchere != null}">
								<c:forEach items="${listeEnchere}" var="uneEnchere"> 
									<c:if test="${uneEnchere.noArticle == unArticle.noArticle}">
										<c:if test="${uneEnchere.prixVente < unArticle.miseAPrix || uneEnchere.prixVente == 0}">
											<h6 class="card-subtitle mb-2 text-muted">Prix : ${unArticle.miseAPrix} Pts</h6>
										</c:if>
										<c:if test="${uneEnchere.prixVente > unArticle.miseAPrix}">
											<h6 class="card-subtitle mb-2 text-muted">Prix : ${uneEnchere.prixVente} Pts</h6>
										</c:if>
				                    </c:if>
				                </c:forEach>
				            </c:if>
				            
							<h6 class="card-subtitle mb-2 text-muted">Fin de l'enchère : ${unArticle.dateFinEncheres}</h6>
						    
						    <c:if test="${id != null}">
								<a href="${pageContext.request.contextPath}/enchere?id=${unArticle.noArticle}" class="btn btn-secondary">Voir l'article</a>
							</c:if>
							<c:if test="${id == null}">
								<a href="${pageContext.request.contextPath}/ServletConnexionUtilisateur" class="btn btn-secondary">Voir l'article</a>
							</c:if>
						  </div>
						</div>
					</div>
				</c:forEach>			
			</div>
		</div>
		
<%@include file="fragments/Bottom.jsp"%>


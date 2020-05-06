<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/Head.jsp"%>
<%@include file="fragments/Menu.jsp"%>

	<div class="container">
	  <div class="row">
		<div class="col" style="text-align:center">
			<h3>Liste des enchères</h3>
		</div>
	  </div>
	  <div class="row">
		<div class="col">
	  		<form class="form-filtre" action="${pageContext.request.contextPath}/ServletFiltre" method="GET">
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
				<br/> 
				<button type="button" class="btn btn-primary btn-lg">Rechercher</button>
			</form>
		</div>
		<div class="col-6">
	
		</div>
		<c:forEach items="${listeArticle}" var="unArticle">
			
			<div class="col-6">
			<div class="card border-primary">
			  <div class="card-body">
				<br/>
				<u><a href="${pageContext.request.contextPath}/enchere?id=${unArticle.noArticle}"><h5>${unArticle.nomArticle}</h5></a></u>
				<br/>
				<c:if test="${listeEnchere.size() < 1}">
					<p>Prix : ${unArticle.miseAPrix} </p>
				</c:if>
				<c:if test="${listeEnchere != null}">
					<c:forEach items="${listeEnchere}" var="uneEnchere"> 
						<c:if test="${uneEnchere.noArticle == unArticle.noArticle}">
							<c:if test="${uneEnchere.prixVente < unArticle.miseAPrix || uneEnchere.prixVente == 0}">
								<p>Prix : ${unArticle.miseAPrix} </p>
							</c:if>
							<c:if test="${uneEnchere.prixVente > unArticle.miseAPrix}">
								<p>Prix : ${uneEnchere.prixVente} </p>
							</c:if>
	                    </c:if>
	                </c:forEach>
				</c:if>
				
				<p> Fin de l'enchère : ${unArticle.dateFinEncheres} </p>
				<c:forEach items="${listeUtilisateur}" var="unUtilisateur">
					<c:if test="${unUtilisateur.id == unArticle.noUtilisateur}">
						<div class="card-footer bg-transparent border-primary">Vendeur : ${unUtilisateur.pseudo}</div>
                    </c:if>
                </c:forEach>
				</div>
			</div>
			</div>
		</c:forEach>
		
	  </div>
	  
	</div>

<%@include file="fragments/Bottom.jsp"%>


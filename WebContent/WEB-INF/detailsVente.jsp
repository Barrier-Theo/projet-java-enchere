<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="fragments/Head.jsp"%>
<%@include file="fragments/Menu.jsp"%>

<body class="">
	<c:if test="${!empty listeCodesErreur}">
		<div class="alert alert-danger text-center" role="alert">
			<ul>
				<c:forEach var="code" items="${listeCodesErreur}">
					<li>${LecteurMessage.getMessageErreur(code)}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="container">

		<div class="row">
			<c:forEach items="${listeArticle}" var="a">

				<div style="margin-top: 10px;" class="col-12 text-center">
					<h2>Détail vente</h2>
					<h5> Mes crédits : ${credit}</h5>
				</div>
				
				<h1 style="margin: 10px 0px 20px 0px; padding-left: 0px;"
					class="col-12">
					<small>${a.nomArticle}</small>
				</h1>
			
				<div class="col-md-7" style="padding-left: 0px !important;">
					<img class="img-fluid" src="http://placehold.it/750x500" alt="">
				</div>

				<div class="col-md-5">
					<h3 style="margin-top: 0px !important;" class="my-3">Détails :</h3>
					<div class="row">
						<div class="col-7">
							<ul>
								<li><span class="font-weight-bold">Catégorie : </span>${nomCategorie}</li>
								<li><span class="font-weight-bold">Fin de l'enchère
										: </span>${a.dateFinEncheres}</li>
								<li><span class="font-weight-bold">Vendeur : </span>${pseudoVendeur}</li>
								<li><span class="font-weight-bold">Description : </span>${a.description}</li>
							</ul>
						</div>
						<div class="col-5">
							<ul>
								<li><span class="font-weight-bold">Mise à prix : </span>${a.miseAPrix}
									Pts</li>
								<c:choose>
									<c:when test="${meilleureOffre != 0}">
										<li><span class="font-weight-bold">Meilleure offre
												: </span>${meilleureOffre} Pts par ${speudoMeilleureOffre}</li>
									</c:when>
									<c:otherwise>
										<li><span class="font-weight-bold">Meilleure offre
												: </span>Aucune offre n'a été effectué</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
					<c:choose>
					<c:when test="${a.dateFinEncheres >= todayDate}">
						<c:if test="${idSession != no_utilisateur}">
							<form method="POST"
								action="${pageContext.request.contextPath}/enchere">
								<h3 style="margin-top: 0px !important;" class="my-3">Faire
								une enchère :</h3>
								<input id="id" name="id" type="hidden"
									value="${a.noArticle}"> 
									<input type="number"
									min="${minProposition}" value="${minProposition}" id="montant"
									name="montant">
									<button style="margin-left: 10px;" class="btn btn-primary"
										type="submit">Enchérir</button>
							</form>
						</c:if>
					</c:when>
					<c:when test="${meilleureOffre != 0}">
						<b>Etat de l'enchère</b></td>
						<strong>${speudoMeilleureOffre} </strong>a gagné
							l'enchère avec un prix de ${meilleureOffre}
					</c:when>
					<c:otherwise>
						<b>Etat de l'enchère</b>
						L'enchère est terminé, Il n'y a jamais eu d'offre
							d'enchères.
					</c:otherwise>
				</c:choose>
					<c:forEach items="${listeRetrait}" var="r">
						<div class="card text-white bg-dark mb-3"
							style="max-width: 18rem;">
							<div class="card-header">Retrait</div>
							<div class="card-body">
								<p class="card-text">${r.rue}<br>${r.codePostal}
									${r.ville}
								</p>
							</div>
						</div>
					</c:forEach>
				</div>
				

			</c:forEach>

		</div>

		<div class="row">
			<div class="offset-4 col-4">
				<a href="<%=request.getContextPath() %>/ServletRedirectForm"
					class="btn btn-lg btn-danger btn-block margintop" type="submit">Retour</a>
			</div>
		</div>



		<%@include file="fragments/Bottom.jsp"%>
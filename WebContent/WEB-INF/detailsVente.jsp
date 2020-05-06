<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
    
<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>eni-encheres</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body class="">
	<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger text-left" role="alert">
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>
    <div class="container-fluid">
        <div class="row">
		<c:forEach items="${listeArticle}" var="a">
            <div class="col-4">
                <div class="">
                    <img src="https://via.placeholder.com/350" align="right" class="img-fluid" alt="Responsive image">
                </div>
            </div>

            <div class="col-8">
                <div class="enchere">

                    <h1 class="h3 mb-3 font-weight-normal marginbottom text-center">Detail vente</h1>
					<p>Mes crédits : ${credit}</p>
                    <table class="table table-borderless text-left">
                        <tbody>
                            <tr>
                                <td colspan="2">
                                    <h3 class="h4 mb-3 font-weight-normal ">PC Gamer pour travailler</h3>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Description :</b></td>
                                <td>${a.description}</td>
                            </tr>
                            <tr>
                                <td><b>Catégorie :</b></td>
                                <td>${nomCategorie}</td>
                            </tr>
                            <tr>
                            <td>
                            	<b>Meilleur offre :</b></td>
                            </td>
                            <c:choose>
	                            <c:when test="${meilleureOffre != 0}">
	                              	<td>${meilleureOffre} par ${speudoMeilleureOffre}</td>
							    </c:when>
					        	<c:otherwise>
					        		<td>Aucune offre n'a été effectué</td>
					        	</c:otherwise>
				        	</c:choose>
                            <tr>
                                <td><b>Mise a prix :</b></td>
                                <td>${a.miseAPrix}</td>
                            </tr>
                            <tr>
                                <td><b>Fin de l'enchére :</b></td>
                                <td>${a.dateFinEncheres}</td>
                            </tr>
                            <c:forEach items="${listeRetrait}" var="r">
	                            <tr>
	                                <td><b>Retrait :</b></td>
	                                <td>${r.rue}<br>${r.codePostal} ${r.ville}</td>
	                            </tr>
                            </c:forEach>
                            <tr>
                                <td><b>Vendeur :</b></td>
                                <td>${pseudoVendeur}</td>
                            </tr>
                            <tr>
                            <c:if test="${idSession != no_utilisateur}">
                                <form method="POST" action="${pageContext.request.contextPath}/enchere">
                                    <td><b>Ma proposition :</b></td>
                                    <td><input id="id" name="id" type="hidden" value="${a.noArticle}">
                                        <input type="number" min="${minProposition}" value="${minProposition}" id="montant" name="montant">
                                        <button style="margin-left: 10px;" class="btn btn-info" type="submit">Enchérir</button></td>
                                </form>
                            </c:if>
                            </tr>
                        </tbody>
                    </table>
				
                    <!--<div class="row">
                        <div class="offset-1 col-4">
                            <button class="btn btn-lg btn-primary btn-block margintop" type="submit">Retour</button>
                        </div>
                    </div>-->
			
                </div>
            </div>
			</c:forEach>
        </div>
    </div>

</body>

</html>
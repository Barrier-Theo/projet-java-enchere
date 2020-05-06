<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@include file="fragments/Head.jsp"%>
<%@include file="fragments/Menu.jsp"%>

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
                                <td><b>Meilleur offre :</b></td>
                                <td></td>
                            </tr>
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
                                <form action="">
                                    <td><b>Ma proposition :</b></td>
                                    <td><input id="idEnchere" name="idEnchere" type="hidden" value="">
                                        <input id="idVendeur" name="idVendeur" type="hidden" value="">
                                        <input type="number" value="210" name="proposition">
                                        <button style="margin-left: 10px;" class="btn btn-info" type="submit">Enchérir</button></td>
                                </form>
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

<%@include file="fragments/Bottom.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/Head.jsp"%>
	
    <form class="form-signin connexion text-center" action="${pageContext.request.contextPath}/ServletConnexionUtilisateur" method="POST">

        <h1 class="h3 mb-3 font-weight-normal marginbottom">Connexion</h1>
		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger text-left" role="alert">
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>
		
        <label for="inputEmail" class="sr-only">Login</label>
        <input type="text" class="form-control" placeholder="Pseudo" required autofocus name="pseudo">

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" class="form-control" placeholder="Mot de passe" required name="password">

        <div class="row">
            <div class="col-6 marginbottom">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
            </div>
            <div class="col-6">
                <div style="margin-top: 10px;" class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> se souvenir de moi
                    </label>
                </div>

            </div>
        </div>

        <a href="#" class="">mot de passe oublié</a>

        <a href="${pageContext.request.contextPath}/WEB-INF/inscription.jsp" style="color:white;" class="btn btn-lg btn-primary btn-block margintop marginbottom">Créer un compte</a>

    </form>

<%@include file="fragments/Bottom.jsp"%>

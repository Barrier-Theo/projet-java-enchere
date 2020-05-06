<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/Head.jsp"%>
<%@include file="fragments/Menu.jsp"%>

<div class="contenu-page text-center">
<div class="profil">

        <h1 class="h3 mb-3 font-weight-normal marginbottom">Votre Profil</h1>

        <table class="table table-striped">
            <tbody>
                
            <c:forEach items="${listeUtilisateur}" var="u">			
            
                <tr>
                    <td><b>Prenom :</b></td>
                    <td>${u.prenom}</td>
                </tr>
                <tr>
                    <td><b>Nom :</b></td>
                    <td>${u.nom}</td>
                </tr>
                <tr>
                    <td><b>Pseudo :</b></td>
                    <td>${u.pseudo}</td>
                </tr>
                <tr>
                    <td><b>Email :</b></td>
                    <td>${u.email}</td>
                </tr>
                <tr>
                    <td><b>Telephone :</b></td>
                    <td>${u.telephone}</td>
                </tr>
                <tr>
                    <td><b>Rue :</b></td>
                    <td>${u.rue}</td>
                </tr>
                <tr>
                    <td><b>Code Postal :</b></td>
                    <td>${u.codePostal}</td>
                </tr>
                <tr>
                    <td><b>Ville :</b></td>
                    <td>${u.ville}</td>
                </tr>
                
             </c:forEach>
                
            </tbody>
        </table>

        
            
                <c:forEach items="${listeUtilisateur}" var="u">		
                
	                <form class="form-signin" action="${pageContext.request.contextPath}/ServletModifierProfil" method="POST">
	                
	                	<div class="row">
	                
	                		<div class="col-6">
	                			<!-- TODO  A définir sur page d'accueil liste d'enchere -->
                				<a href="<%=request.getContextPath() %>/ServletAccueil"  class="btn btn-lg btn-primary btn-block">Retour</a>
            				</div>  
				        
							<div class="col-6">
				      			<button class="btn btn-lg btn-warning btn-block" type="submit">Modifier</button>
				  			</div>
	                
	              
	                  
				    	</div>
				    
				    </form>	
			    
            	</c:forEach>
            	
        

    </div>
</div>

<%@include file="fragments/Bottom.jsp"%>

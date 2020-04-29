<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<body class="text-center">

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

        <div class="row">
            <div class="offset-1 col-4">
                <a href="#"  class="btn btn-lg btn-primary btn-block margintop">Retour</a>
            </div>
            <div class="offset-2 col-4">
                <c:forEach items="${listeUtilisateur}" var="u">			
                	<a href="<%=request.getContextPath() %>/ServletModifierProfil?idUser=${u.id}" class="btn btn-lg btn-warning btn-block margintop">Modifier</a>
            	</c:forEach>
            </div>
        </div>

    </div>

</body>

</html>
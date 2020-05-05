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
    <link rel="stylesheet" type="text/css" href="theme/css/style.css">

</head>

<body class="">

    <div class="container-fluid">
        <div class="row">
		<c:forEach items="${listeArticle}" var="a">
			
            <div class="col-4">
                <div class="">
                    <img src="https://via.placeholder.com/350" align="right" class="img-fluid" alt="Responsive image">
                </div>
            </div>

            <div class="col-8">
                <div class="profil">

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
                                <td>${a.noCategorie}</td>
                            </tr>
                            <tr>
                                <td><b>Meilleur offre :</b></td>
                                <td>210 pts par Bob</td>
                            </tr>
                            <tr>
                                <td><b>Mise a prix :</b></td>
                                <td>185 points</td>
                            </tr>
                            <tr>
                                <td><b>Fin de l'enchére :</b></td>
                                <td>09/10/2018</td>
                            </tr>
                            <tr>
                                <td><b>Retrait :</b></td>
                                <td>10 allée des Alouettes<br>44800 Saint Herblain</td>
                            </tr>
                            <tr>
                                <td><b>Vendeur :</b></td>
                                <td>jojo44</td>
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

</body>

</html>
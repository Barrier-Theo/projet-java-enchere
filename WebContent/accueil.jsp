<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="fr">

<head>
  <meta charset="UTF-8">

    <title>Accueil</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function(){
		var bool = 0;
		$("input.group2").attr("disabled", true);
		$("input.group1").removeAttr("disabled");
		
        $('input[type="radio"]').click(function(){
          if(bool == 0){
			$("input.group1").attr("disabled", true);
			$("input.group2").removeAttr("disabled");
			$("input.group1").removeAttr("checked");
			bool = 1;
		  }
		  else{
			$("input.group2").attr("disabled", true);
			$("input.group1").removeAttr("disabled");
			$("input.group2").removeAttr("checked");
			bool = 0;
		  }
        });
      });
    </script>
</head>


<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">ENI-Enchères</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="#">Enchères <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath() %>/ServletNouvelArticleVendu">Vendre un article</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath() %>/ServletProfil?idUser=${id}">Mon profil</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Déconnexion</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container">
	  <div class="row">
		<div class="col" style="text-align:center">
			<h3>Liste des enchères</h3>
		</div>
	  </div>
	  <div class="row">
		<div class="col">
			<b>Filtres :</b>
			<input type="text" class="form-control" id="nomArticle" placeholder="Le nom de l'article contient">
			<br/>
			<b>Catégorie :</b>
			<select class="form-control">
				<option>...</option>
			</select>
			<br/>
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

			<br/> 
			<button type="button" class="btn btn-primary btn-lg">Rechercher</button>
		</div>
		<div class="col-6">
	
		</div>
		<c:forEach items="${listeArticle}" var="unArticle">
			
			<div class="col-6">
				<br/>
				<u><h5>${unArticle.nomArticle}</h5></u>
				<br/>
				<p>Prix : ${unArticle.miseAPrix} </p>
				<p> Fin de l'enchère : ${unArticle.dateFinEncheres} </p>
				<p> Vendeur : ${unArticle.noUtilisateur} </p>
			</div>
		</c:forEach>
		
	  </div>
	  
	</div>
</body>

</html>



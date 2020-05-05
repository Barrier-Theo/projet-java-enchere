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
        <link rel="stylesheet" type="text/css" href="theme/css/accueil.css">
    
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
		<a class="navbar-brand" href="#">ENI-Ench�res</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="#">Ench�res <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath() %>/ServletNouvelArticleVendu">Vendre un article</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath() %>/ServletProfil">Mon profil</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath() %>/deconnexion">D�connexion</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container">
	  <div class="row">
		<div class="col" style="text-align:center">
			<h3>Liste des ench�res</h3>
		</div>
	  </div>
	  <div class="row">
		<div class="col">
			<b>Filtres :</b>
			<input type="text" class="form-control" id="nomArticle" placeholder="Le nom de l'article contient">
			<br/>
			<b>Cat�gorie :</b>
			<select class="form-control" name="idCategorie">
				<c:forEach items="${listeCategories}" var="uneCategorie">
					<option value="${uneCategorie.noCategorie}">${uneCategorie.libelle}</option>
				</c:forEach>
			</select>
			<br/>
			<div class="radio">
				<div class="row">
					<div class="col">
						<label><input type="radio" class="achat" name="optradio" checked>Achats</label>
						<div class="checkedAchat">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input group1" id="checkedEncheresOuvertes">
								<label class="custom-control-label" for="checkedEncheresOuvertes">Ench�res ouvertes</label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input group1" id="checkedEncheresEnCours">
								<label class="custom-control-label" for="checkedEncheresEnCours">Mes ench�res en cours </label>
							</div>
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input group1" id="checkedEncheresRemportees">
								<label class="custom-control-label" for="checkedEncheresRemportees">Mes ench�res remport�es</label>
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
								<label class="custom-control-label" for="checkedVentesNondebutees">Ventes non d�but�es</label>
							</div>
							<div class="custom-control custom-checkbox">							
								<input type="checkbox" class="custom-control-input group2" id="checkedVentesTerminees">
								<label class="custom-control-label" for="checkedVentesTerminees">Ventes termin�es</label>
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
			
			<div class="col-6 card">
			  <div class="card-body">
				<br/>
				<u><a href="${pageContext.request.contextPath}/enchere?id=${unArticle.noArticle}"><h5> ${unArticle.nomArticle}</h5></u></a>
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
				
				<p> Fin de l'ench�re : ${unArticle.dateFinEncheres} </p>
				<c:forEach items="${listeUtilisateur}" var="unUtilisateur">
					<c:if test="${unUtilisateur.id == unArticle.noUtilisateur}">
						<p> Vendeur : ${unUtilisateur.nom} </p>
                    </c:if>
                </c:forEach>
				</div>
			</div>
		</c:forEach>
		
	  </div>
	  
	</div>
</body>

</html>


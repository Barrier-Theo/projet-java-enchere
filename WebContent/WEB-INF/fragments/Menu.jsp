
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand"
			href="<%=request.getContextPath() %>/ServletRedirectForm"> <img
			src="theme/images/logo2.png" width="80" height="80" alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">

			<c:if test="${id != null}">

				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="<%=request.getContextPath() %>/ServletRedirectForm">Enchères
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath() %>/ServletNouvelArticleVendu">Vendre
							un article</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath() %>/ServletProfil">Mon profil</a>
					</li>
				</ul>

				<form class="form-inline">
					<a href="<%=request.getContextPath() %>/deconnexion"
						class="btn btn-sm btn-outline-danger" type="button">Déconnexion</a>
				</form>

			</c:if>

			<c:if test="${id == null}">

				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath() %>/ServletConnexionUtilisateur">Connexion</a>
					</li>
				</ul>

			</c:if>

		</div>
	</div>
</nav>


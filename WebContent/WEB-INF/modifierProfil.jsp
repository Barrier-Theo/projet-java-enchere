<%@include file="fragments/Head.jsp"%>
<%@include file="fragments/Menu.jsp"%>


    <form class="form-modif text-center" action="${pageContext.request.contextPath}/ServletModificationProfil" method="POST">

        <h1 class="h3 mb-3 font-weight-normal marginbottom">Mon Profil</h1>

        <c:if test="${!empty listeCodesErreur}">
				<div class="alert alert-danger"  role="alert">
				  <strong>Erreur!</strong>
				  <ul>
				  	<c:forEach var="code" items="${listeCodesErreur}">
				  		<li>${LecteurMessage.getMessageErreur(code)}</li>
				  	</c:forEach>
				  	<c:if test="${!empty erreurMdps}">
				  		<li>${erreurMdps}</li>
				  	</c:if>
				</div>
	
				  </ul>
				</div>
			</c:if>

        <div class="container">
            <div class="row">

				<c:forEach items="${listeUtilisateur}" var="u">
				
                <div class="col-6">

                    <div class="form-group row">
                        <label for="inputPseudo" class="col-sm-5 col-form-label">Pseudo :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputPseudo" name="pseudo" value="${u.pseudo}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputPrenom" class="col-sm-5 col-form-label">Prenom :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputPrenom" name="prenom" value="${u.prenom}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputTelephone" class="col-sm-5 col-form-label">Telephone :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputTelephone" name="telephone" value="${u.telephone}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputCp" class="col-sm-5 col-form-label">Code Postal :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputCp" name="codePostal" value="${u.codePostal}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputMdp" class="col-sm-5 col-form-label">Mot de passe actuel :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputMdp" name="mdpactuel" value="${u.motDePasse}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputNmdp" class="col-sm-5 col-form-label">Nouveau mot de passe :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputNmdp" name="nouveaumdp">
                        </div>
                    </div>

                </div>

                <div class="col-6">
                    <div class="form-group row">
                        <label for="inputNom" class="col-sm-3 col-form-label">Nom :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputNom" name="nom" value="${u.nom}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEmail" class="col-sm-3 col-form-label">Email :</label>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" id="inputEmail" name="email" value="${u.email}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputRue" class="col-sm-3 col-form-label">Rue :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputRue" name="rue" value="${u.rue}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputVille" class="col-sm-3 col-form-label">Ville :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputVille" name="ville" value="${u.ville}" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-7">
                            <input class="form-control invisible">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputConfirm" class="col-sm-3 col-form-label">Confirmation :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputConfirm" name="confirmation" >
                        </div>
                    </div>

                </div>
                
                </c:forEach>

                <div class="offset-3 col-3">
                    <button class="btn btn-lg btn-primary btn-block margintop" type="submit">Enregistrer</button>
                </div>
                <div class="col-4">
                    <a class="btn btn-lg btn-danger btn-block margintop" href="${pageContext.request.contextPath}/ServletSupprimer">Supprimer mon compte</a>
                </div>

            </div>
        </div>




    </form>

<%@include file="fragments/Bottom.jsp"%>

<%@include file="fragments/Head.jsp"%>

			
    <form class="form-modif" action="${pageContext.request.contextPath}/ServletInscription" method="POST">

        <h1 class="h3 mb-3 font-weight-normal marginbottom">Créer votre profil</h1>

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

                <div class="col-6">

                    <div class="form-group row">
                        <label for="inputPseudo" class="col-sm-5 col-form-label">Pseudo :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputPseudo" name="pseudo">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputPrenom" class="col-sm-5 col-form-label">Prénom :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputPrenom" name="prenom">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputTelephone" class="col-sm-5 col-form-label">Téléphone :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputTelephone" name="telephone">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputCp" class="col-sm-5 col-form-label">Code Postal :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputCp" name="codePostal">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputMdp" class="col-sm-5 col-form-label">Mot de passe :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputMdp" name="password">
                        </div>
                    </div>

                </div>

                <div class="col-6">
                    <div class="form-group row">
                        <label for="inputNom" class="col-sm-3 col-form-label">Nom :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputNom" name="nom">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEmail" class="col-sm-3 col-form-label">Email :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputEmail" name="email">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputRue" class="col-sm-3 col-form-label">Rue :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputRue" name="rue">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputVille" class="col-sm-3 col-form-label">Ville :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputVille" name="ville">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputConfirm" class="col-sm-3 col-form-label">Confirmation :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputConfirm" name="confirmation">
                        </div>
                    </div>

                </div>

                <div class="offset-3 col-3">
                    <button class="btn btn-lg btn-success btn-block margintop" type="submit">Créer</button>
                </div>
                <div class=" col-4">
                    <a href ="${pageContext.request.contextPath}" style="color: white;" class="btn btn-lg btn-danger btn-block margintop">Annuler</a>
                </div>

            </div>
        </div>




    </form>
    
<%@include file="fragments/Bottom.jsp"%>

<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>eni-encheres</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="index.css" rel="stylesheet">

</head>

<body class="text-center">

    <form class="form-modif" action="" method="POST">

        <h1 class="h3 mb-3 font-weight-normal marginbottom">Mon Profil</h1>

        <div class="alert alert-danger" role="alert">
            A simple danger alert—check it out!
        </div> 
 
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
                            <input type="text" class="form-control" id="inputCp" name="codeP">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputMdp" class="col-sm-5 col-form-label">Mot de passe actuel :</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputMdp" name="mdpactuel">
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
                        <div class="col-sm-7">
                            <input class="form-control invisible">
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
                    <button class="btn btn-lg btn-primary btn-block margintop" type="submit">Enregistrer</button>
                </div>
                <div class=" col-4">
                    <button class="btn btn-lg btn-danger btn-block margintop" type="submit">Supprimer mon compte</button>
                </div>

            </div>
        </div>




    </form>
</body>

</html>
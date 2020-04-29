<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Vos Listes</title>

    <!-- Bootstrap core CSS -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"><!--[if gte mso 9]><xml>
<mso:CustomDocumentProperties>
<mso:_dlc_DocId msdt:dt="string">Z5HNVW24N33T-678105430-3966</mso:_dlc_DocId>
<mso:_dlc_DocIdItemGuid msdt:dt="string">029a8575-4aad-4138-b05d-5f86afb8c356</mso:_dlc_DocIdItemGuid>
<mso:_dlc_DocIdUrl msdt:dt="string">http://inet/eni-transverse/ecole-numï¿½rique/_layouts/15/DocIdRedir.aspx?ID=Z5HNVW24N33T-678105430-3966, Z5HNVW24N33T-678105430-3966</mso:_dlc_DocIdUrl>
</mso:CustomDocumentProperties>
</xml><![endif]-->
</head>

<body>

	<div class="container">
		<div class="row">
			<ul class="list-group col-12">
					<li class="list-group-item d-flex justify-content-between align-items-center" >${id}</li>
					<li class="list-group-item d-flex justify-content-between align-items-center"> ${pseudo}</li>
					<li class="list-group-item d-flex justify-content-between align-items-center"> ${password}</li>
			
			</ul>
		</div>	
	</div>
	
	<a href="<%=request.getContextPath() %>/ServletProfil?idUser=${id}">Mon profil</a>
		    
    <!-- Footer -->
    <footer class="row bg-dark footer-demodule fixed-bottom py-1">
            <div class="col-lg-4 offset-lg-4 text-center">
                <a class="btn" href="${pageContext.request.contextPath}/ServletNouvelleListe" title="Créer une nouvelle liste"><i class="material-icons">add</i></a>
            </div>
        <!-- /.container -->
    </footer>
</body>

</html>

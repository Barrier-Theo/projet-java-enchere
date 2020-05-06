<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="fr">

<head>
  <meta charset="UTF-8">

    <title>Eni-Encheres</title>

    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="theme/css/style.css">
    
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


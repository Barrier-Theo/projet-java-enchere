package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UtilisateurManager;
import bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Lecture des parametres après soumission du formulaire
		
		Map params = request.getParameterMap();
		List<Integer> listeCodesErreur=new ArrayList<>();
			
		
		
		String pseudo =  request.getParameter("pseudo");
		String password= request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom   = request.getParameter("prenom");
		String email =  request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal =  request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		Utilisateur nouvelUtilisateur = new Utilisateur(pseudo,nom,prenom,email,telephone, rue,codePostal,ville,password, 500, false);
		UtilisateurManager utilisateurManager = new UtilisateurManager();	
		try {
			utilisateurManager.ajouterUtilisateur(nouvelUtilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
		//TODO A définir la page d'accueil enchere 
		RequestDispatcher rd = request.getRequestDispatcher("page accueil enchere ");
		rd.forward(request, response);		
		
	}

}

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
@WebServlet("/ServletModificationProfil")
public class ServletModificationProfil extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Lecture des parametres apr�s soumission du formulaire
		RequestDispatcher rd = null;
		String id = request.getParameter("idUser");
		Map params = request.getParameterMap();
			
		String pseudo =  request.getParameter("pseudo");
		String password= request.getParameter("mdpactuel");
		String nom = request.getParameter("nom");
		String prenom   = request.getParameter("prenom");
		String email =  request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal =  request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String newpassword = request.getParameter("nouveaumdp");
		String newpasswordCfm = request.getParameter("confirmation");
		
		if(!newpassword.equals(newpasswordCfm)) {
			request.setAttribute("erreurMdps","Les mots de passes ne sont pas identiques");
		}
		
		Utilisateur utilisateur = new Utilisateur(pseudo,nom,prenom,email,telephone,rue,codePostal,ville,password, 500, false);
		UtilisateurManager utilisateurManager = new UtilisateurManager();	
		try {
			utilisateurManager.modifierUtilisateur(utilisateur);
			//TODO definir sur page d'accueil.
			rd = request.getRequestDispatcher("/liste.jsp");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			rd = request.getRequestDispatcher("/modifierProfil.jsp");
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		
		rd.forward(request, response);				
	}

}

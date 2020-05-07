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
import javax.servlet.http.HttpSession;

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
		
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		Map params = request.getParameterMap();
			
		String pseudo =  request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom   = request.getParameter("prenom");
		String email =  request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal =  request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String newpassword = request.getParameter("nouveaumdp");
		String newpasswordCfm = request.getParameter("confirmation");
		
		String mdpCourant = null; 
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = new Utilisateur(id, pseudo,nom,prenom,email,telephone,rue,codePostal,ville, 500, false,false);
		BusinessException businessException = new BusinessException();
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		try {
			
			Utilisateur utilisateurConnecte = utilisateurManager.selectUser(id);
			mdpCourant = utilisateurConnecte.getMotDePasse();
			utilisateur.setMotDePasse(mdpCourant);

			if(newpassword.trim().isEmpty() == false || newpasswordCfm.trim().isEmpty() == false) {
				if(!newpassword.equals(newpasswordCfm)) {
					request.setAttribute("erreurMdps","Les mots de passes ne sont pas identiques");
					throw businessException;
				}else {
					utilisateur.setMotDePasse(newpassword);
				}
				
			}
			
			utilisateurManager.modifierUtilisateur(utilisateur);
			listeUtilisateur.add(utilisateur);
			//TODO definir sur page d'accueil.
			rd = request.getRequestDispatcher("/ServletProfil");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.setAttribute("listeUtilisateur", listeUtilisateur);
			rd = request.getRequestDispatcher("/ServletModifierProfil");

		}
		
		rd.forward(request, response);				
	}

}

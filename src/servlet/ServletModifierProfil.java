package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.Utilisateur;
import bll.UtilisateurManager;
import dal.UtilisateurDAOJdbcImpl;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ServletModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierProfil() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String id = request.getParameter("idUser");
		UtilisateurManager utilisateurManager;
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		
		utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = utilisateurManager.selectUser(id);
		listeUtilisateur.add(utilisateur);
		request.setAttribute("listeUtilisateur", listeUtilisateur);
		
		if(id == null) {
			rd = request.getRequestDispatcher("/connexion.jsp");
		}else {
			rd = request.getRequestDispatcher("/modifierProfil.jsp");
		}
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Integer idUtilisateur = null ;
		try {
			idUtilisateur= utilisateurManager.findIdByPseudoPassword(pseudo, password);
			request.setAttribute("id", idUtilisateur);
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("password", password);
		
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		if(idUtilisateur == null) {
			rd = request.getRequestDispatcher("/connexion.jsp");
		}else {
			rd = request.getRequestDispatcher("/accesProfil.jsp");
		}
		rd.forward(request, response);

	}

}

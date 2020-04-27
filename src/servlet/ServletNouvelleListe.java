package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UtilisateurManager;
import bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleListe
 */
@WebServlet("/ServletNouvelleListe")
public class ServletNouvelleListe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/WEB-INF/nouvelle_list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreur=new ArrayList<>();
		try
		{
			String nomListe = request.getParameter("nomListe");
			String nomArticle =request.getParameter("nomArticle");
			
			if(nomListe==null || nomListe.trim().isEmpty())
			{
				listeCodesErreur.add(CodesResultatServlets.FORMAT_NOM_LISTE_ERREUR);
			}
						
			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelle_list.jsp");
				rd.forward(request, response);
			}
			else
			{
				UtilisateurManager listeManager = new UtilisateurManager();
				try {
					listeManager.ajouterListeEtArticle(nomListe,nomArticle);
					RequestDispatcher rd = request.getRequestDispatcher("/ServletListAccueil");
					rd.forward(request, response);
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelle_list.jsp");
					rd.forward(request, response);
				}
			}

		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_NOM_LISTE_ERREUR);
		}
	}

}

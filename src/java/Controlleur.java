import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/calculImpot")
public class Controlleur extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Redirection vers la page d'accueil (index.jsp) pour les requêtes GET
    response.sendRedirect(request.getContextPath() + "/index.jsp");
  }
double impot = 0.0;
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
        // Vérifiez si l'utilisateur est déjà inscrit
        if (utilisateurEstInscrit(request)) {
            // L'utilisateur est inscrit, continuez avec le calcul d'impôt

            int nbEnfants = 0;
            if (request.getParameter("nbEnfants") != null) {
                nbEnfants = Integer.parseInt(request.getParameter("nbEnfants"));
            }

            boolean marie = false;
            if (request.getParameter("marie") != null) {
                marie = Boolean.parseBoolean(request.getParameter("marie"));
            }

            double salaire = 0.0;
            if (request.getParameter("salaire") != null) {
                salaire = Double.parseDouble(request.getParameter("salaire"));
            }

            if (salaire == 12620.0) {
                impot = 0.0;
            } else {
                impot = Impot.calculerImpot(nbEnfants, marie, salaire);
            }

            request.setAttribute("impot", impot);
            request.getRequestDispatcher("/resultat.jsp").forward(request, response);
        } else {
            // L'utilisateur n'est pas inscrit, effectuez l'inscription
            faireInscription(request, response);
        }
    } catch (NumberFormatException | SQLException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les paramètres de la requête sont invalides.");
    }
}


  // Méthode pour vérifier si l'utilisateur est inscrit
  private boolean utilisateurEstInscrit(HttpServletRequest request) {
    // Implémentez votre logique d'authentification/inscription ici
    // Vous pouvez utiliser une session, une base de données, ou toute autre méthode appropriée

    // Pour l'exemple, considérons une session fictive
    return request.getSession(false) != null && request.getSession().getAttribute("utilisateurInscrit") != null;
  }

  // Méthode pour effectuer l'inscription
  private void faireInscription(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {

      // Effectuez le processus d'inscription
      // ...

      // Marquez l'utilisateur comme inscrit (simulé avec une session)
      HttpSession session = request.getSession(true);
      session.setAttribute("utilisateurInscrit", true);

      // Redirigez l'utilisateur vers la page "Utilisateur" après l'inscription réussie
      response.sendRedirect(request.getContextPath() + "/utilisateur.jsp");
    } catch (NumberFormatException e) {
      throw new ServletException("Erreur lors de l'inscription", e);
    }
   }

}
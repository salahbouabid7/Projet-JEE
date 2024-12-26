import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Impot {

    public static double calculerImpot(int nbEnfants, boolean marie, double salaire) throws SQLException {

        // Calcul du revenu fiscal de référence
        double revenuFiscalDeReference = salaire - (nbEnfants * 5544.0);

        // Calcul du taux d'imposition
        double tauxImposition ;
        if (revenuFiscalDeReference <= 12620.0) {
          tauxImposition = 0.0;
        } else if (revenuFiscalDeReference <= 13190.0) {
          tauxImposition = 0.05;
        } else if (revenuFiscalDeReference <= 15640.0) {
          tauxImposition = 0.1;
        } else if (revenuFiscalDeReference <= 24740.0) {
          tauxImposition = 0.15;
        } else if (revenuFiscalDeReference <= 31810.0) {
          tauxImposition = 0.2;
        } else if (revenuFiscalDeReference <= 48360.0) {
          tauxImposition = 0.25;
        } else if (revenuFiscalDeReference <= 55790.0) {
          tauxImposition = 0.3;
        } else if (revenuFiscalDeReference <= 92970.0) {
          tauxImposition = 0.35;
        } else if (revenuFiscalDeReference <= 127860.0) {
          tauxImposition = 0.4;
        } else if (revenuFiscalDeReference <= 151250.0) {
          tauxImposition = 0.45;
        } else if (revenuFiscalDeReference <= 172040.0) {
          tauxImposition = 0.5;
        } else if (revenuFiscalDeReference <= 195000.0) {
          tauxImposition = 0.55;
        } else {
          tauxImposition = 0.6;
        }

        // Calcul de l'impôt
        double impot = revenuFiscalDeReference * tauxImposition;
        double abattementEnfants = (nbEnfants * 1547.25);
        // Calcul de l'abattement pour les enfants
        if (nbEnfants >= 1) {
          abattementEnfants = (nbEnfants * 1547.25);
          if (abattementEnfants > 1547.25 * nbEnfants) {
            abattementEnfants = 1547.25 * nbEnfants;
          }
        }
        double abattementCouple = 1581.25;
        // Calcul de l'abattement pour les couples mariés
        if (marie) {
          abattementCouple = 1581.25;
        }

        // Calcul de l'impôt net
        double impotNet = Math.max(0.0, impot - abattementEnfants - abattementCouple);
        if (impotNet == 0.0 && revenuFiscalDeReference > 0.0) {
          impotNet = 0.1 * revenuFiscalDeReference;
        }

        return impotNet;
      }

    public static boolean authentifierUtilisateur(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String dbUsername = "root";
            String dbPassword = "root";

            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                String query = "SELECT * FROM utilisateurs WHERE username = ? AND password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        return resultSet.next(); // Si une ligne est trouvée, l'authentification est réussie
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Gérer les exceptions ici
            return false;
        }
    }

    public static void enregistrerUtilisateur(String username, String password, String situationFamiliale, int nbEnfants, boolean marie, double salaire) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String dbUsername = "root";
            String dbPassword = "root";

            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
                String query = "INSERT INTO utilisateurs (username, password, situationFamiliale, nbEnfants, marie, salaire) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, situationFamiliale);
                    preparedStatement.setInt(4, nbEnfants);
                    preparedStatement.setBoolean(5, marie);
                    preparedStatement.setDouble(6, salaire);

                    preparedStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Gérer les exceptions ici
        }
    }
}

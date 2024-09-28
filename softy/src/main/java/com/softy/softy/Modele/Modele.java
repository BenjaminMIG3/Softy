package com.softy.softy.Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.softy.softy.Entite.Utilisateur;

public class Modele {
    private static final String URL = "jdbc:mysql://localhost:8889/softy_caisse"; 
    private static final String LOGIN = "root";
    private static final String MDP = "root";

    private Connection maConnexion;
    private ResultSet tabResSql;
    private PreparedStatement preparedStatement;

    public Modele(){
        this.setConnexion();
    }

    /**
     * Fonction qui retourne true ou false si l'user existe dans la BDD
     * @param login
     * @param password
     * @return bool 
     */
    public boolean checkUserConnexion(String login, String password){
        try {
            this.setConnexion();
            String requete = "SELECT COUNT(id) FROM T_SOFTY_UTILISATEUR WHERE login = ? AND password = ?;";
            this.preparedStatement = this.maConnexion.prepareStatement(requete);
            this.preparedStatement.setString(1, login);
            this.preparedStatement.setString(2, password);
            this.tabResSql = this.preparedStatement.executeQuery();
            if(this.tabResSql.next()){
                return this.tabResSql.getInt(1) > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeRessources();
        }
    }

    /**
     * Fonction qui retourne un utilisateur en fonction de son login / password
     * @param login
     * @param password
     * @return Utilisateur OR null
     */
    public Utilisateur getUser(String login, String password) {
        try {
            this.setConnexion();
            String requete = "SELECT * FROM T_SOFTY_UTILISATEUR WHERE login = ? AND password = ?;";
            this.preparedStatement = this.maConnexion.prepareStatement(requete);
            this.preparedStatement.setString(1, login);
            this.preparedStatement.setString(2, password);
            this.tabResSql = this.preparedStatement.executeQuery();
            if(this.tabResSql.next()){
                return new Utilisateur(
                    tabResSql.getInt("id"), 
                    tabResSql.getString("nom"), 
                    tabResSql.getString("prenom"), 
                    tabResSql.getString("login"), 
                    tabResSql.getString("password"), 
                    tabResSql.getString("email"),
                    tabResSql.getString("role")
                    );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            this.closeRessources();
        }
    }

    /**
     * Fonction qui tente d'insérer un utilisateur dans la BDD
     * @param user
     * @return true / false
     */
    public boolean insertIntoUser(Utilisateur user){
        try {
            this.setConnexion();
            String requete = "INSERT INTO T_SOFTY_UTILISATEUR(nom,prenom,login,password,email,role) VALUES(?,?,?,?,?,?);";
            this.preparedStatement = this.maConnexion.prepareStatement(requete);
            this.preparedStatement.setString(1, user.getNom());
            this.preparedStatement.setString(2, user.getPrenom());
            this.preparedStatement.setString(3, user.getLogin());
            this.preparedStatement.setString(4, user.getPassword());
            this.preparedStatement.setString(5, user.getEmail());
            this.preparedStatement.setString(6, user.getRole());
            return this.preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeRessources();
        }
    }
    /**
     * Fonction qui se connecte à la BDD
     * @return
     */
    private boolean setConnexion() {
        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            this.maConnexion = DriverManager.getConnection(URL, LOGIN, MDP);

            return true;
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Driver JDBC non trouvé " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
        return false;
    }

    /**
     * Fonction qui se déconnecte de la BDD
     */
    private void closeConnexion() {
        try {
            if (this.maConnexion != null && !this.maConnexion.isClosed()) {
                this.maConnexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }

    private void closeRessources() {
        try {
            if (tabResSql != null) {
                tabResSql.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture du ResultSet : " + e.getMessage());
        }
    
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture du PreparedStatement : " + e.getMessage());
        }
    
        this.closeConnexion(); // Appelle la méthode pour fermer la connexion
    }
}

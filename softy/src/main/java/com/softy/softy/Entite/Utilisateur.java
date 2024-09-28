package com.softy.softy.Entite;

import java.io.Serializable;

public class Utilisateur implements Serializable{

    private static final long serialVersionUID = 1L;
    private transient String password,email;
    private String nom, prenom, login, role;
    private int id;

    /**
     * Constructeur pour récupération depuis la BDD
     * @param id
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param email
     * @param role 
     */
    public Utilisateur(int id, String nom, String prenom, String login, String password, String email, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.id = id;
    }

    /**
     * Constructeur pour insertion depuis la BDD
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param email
     * @param role
     */
    public Utilisateur(String nom, String prenom, String login, String password, String email, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password=" + password
                + ", email=" + email + ", role=" + role + ", id=" + id + "]";
    }
}

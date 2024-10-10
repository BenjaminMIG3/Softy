package com.softy.softy.Entite;

import java.io.Serializable;

public class Role implements Serializable{
    

    private int id;
    private String nom;
    private int points;

    /**
     * Constructeur pour création et insertion dans la BDD
     * @param nom
     * @param points
     */
    public Role(String nom, int points) {
        this.nom = nom;
        this.points = points;
    }

    /**
     * Constructeur pour récupération depuis la BDD
     * @param id
     * @param nom
     * @param points
     */
    public Role(int id, String nom, int points) {
        this.id = id;
        this.nom = nom;
        this.points = points;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getPoints() {
        return this.points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Role [id=" + this.id + ", nom=" + this.nom + ", points=" + this.points + "]";
    }
}

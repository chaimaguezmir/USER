package entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Livraison {
   private int id_livraison;
   private String adresse_depart;
   private String adresse_arrive;
   private String photo_produit;
   private int prix;
   private String etat;
   private int id_user;
   private String date_reception;

    public Livraison() {
    }

    public Livraison(int id_livraison, String photo_produit, String etat, String date_reception) {
        this.id_livraison = id_livraison;
        this.photo_produit = photo_produit;
        this.etat= etat;
        this.date_reception = date_reception;
    }

    public Livraison(String adresse_depart, String adresse_arrive, String photo_produit, int prix, String etat, int id_user, String date_reception) {
        this.adresse_depart = adresse_depart;
        this.adresse_arrive = adresse_arrive;
        this.photo_produit = photo_produit;
        this.prix = prix;
        this.etat = etat;
        this.id_user = id_user;
        this.date_reception = date_reception;
    }

    public Livraison(int id_livraison, String adresse_depart, String adresse_arrive, String photo_produit, int prix, String etat, int id_user, String date_reception) {
        this.id_livraison = id_livraison;
        this.adresse_depart = adresse_depart;
        this.adresse_arrive = adresse_arrive;
        this.photo_produit = photo_produit;
        this.prix = prix;
        this.etat = etat;
        this.id_user = id_user;
        this.date_reception = date_reception ;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public String getDate_reception() {
        return date_reception;
    }

    public void setDate_reception(String date_reception) {
        this.date_reception = date_reception;
    }

    public String getAdresse_depart() {
        return adresse_depart;
    }

    public void setAdresse_depart(String adresse_depart) {
        this.adresse_depart = adresse_depart;
    }

    public String getAdresse_arrive() {
        return adresse_arrive;
    }

    public void setAdresse_arrive(String adresse_arrive) {
        this.adresse_arrive = adresse_arrive;
    }

    public String getPhoto_produit() {
        return photo_produit;
    }

    public void setPhoto_produit(String photo_produit) {
        this.photo_produit = photo_produit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", adresse_depart=" + adresse_depart + ", adresse_arrive=" + adresse_arrive + ", photo_produit=" + photo_produit + ", prix=" + prix + ", etat=" + etat + ", id_user=" + id_user + '}';
    }

  

  
}

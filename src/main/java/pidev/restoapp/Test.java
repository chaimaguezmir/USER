package pidev.restoapp;


import entity.Commande;
import entity.Livraison;
import service.*;
import utils.ConnectionDB;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        try {
            // Initialize the database connection
            ConnectionDB.getInstance().getConn();

            // Initialize services

            LivraisonService livraisonService = new LivraisonService();
            CommandeService commandeService = new CommandeService();

            //objet liv :
            Livraison livraison = new Livraison(
                    "123 Ariana",
                    "456 Aouina",
                    "product_photo.jpg",
                    50,
                    "en_cours",
                    1,
                    "2024-02-16"
            );

            Scanner scanner = new Scanner(System.in);

            //Date now :
            LocalDateTime now = LocalDateTime.now(); //exemple date

            int choice;
            do {

                System.out.println("1. Livraison Operations");
                System.out.println("2. Commande Operations");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                  //  Add liv:

                      //  livraisonService.create(livraison); // ok
                    //Update liv:
                        // 20 ==> id liv
                      /*  livraisonService.update(new  Livraison(1,

                            "123 NAbeul",
                            "456 Tunis",
                            "product_photo.jpg",
                            500,
                            "rejeté",
                            1,
                            "2024-02-10"
                    ));*/ //OK

                    //get all
                   // System.out.println(livraisonService.getAll()); // ok

                    //delet:
                 //   livraisonService.delete(1);

                        break;
                    case 2:
                        //Commande
                        //Add
                        commandeService.create(new Commande(new Date(),"Tunis",500));
                       commandeService.create(new Commande(new Date(),"Tozeur",100));
//id  hia id ta3 user ely3mal commande
                  // commandeService.update(new Commande(2,1,new Date(),"Nabeul",20));

                        //delete
                        commandeService.delete(2);

                        //get all
                        System.out.println(commandeService);

                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

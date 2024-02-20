package pidev.restoapp;


import entity.Reservation;
import entity.restaurantTable;
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

            RestaurantTableService restaurantTableService=new RestaurantTableService(ConnectionDB.getInstance().getConn());
            ReservationService reservationService = new ReservationService();
            Scanner scanner = new Scanner(System.in);

            //Date now :
            LocalDateTime now = LocalDateTime.now(); //exemple date

            int choice;
            do {

                System.out.println("1. Reservation Operations");
                System.out.println("2. Tabl Operations");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:

                   //     reservationService.create(new Reservation(1,"Chaima",now,6));add
                   // reservationService.update(new Reservation(1,"Chaaaaa",now,2));edit
                        reservationService.delete(1);

                        //cbon crud reservation chaima kif tesna3 project jdida thama config ff intellig zedtha bch 9ra db
                        //manual




                        break;
                    case 2:
                        // Table Restaurant operations

                     //   restaurantTableService.create(new restaurantTable(1,50,true));
                 //       restaurantTableService.update(new restaurantTable(1,10,false));// false ==0 / true =1

                       //affichage
                     //   System.out.println("data restautrant tables"+restaurantTableService.getAll());
                    //delete mazlt kaho chaima
                        restaurantTableService.delete(1); // 5ater 5 unexist
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

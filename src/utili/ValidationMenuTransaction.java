package utili;

import models.enums.CryptoType;
import models.enums.FeeLevel;

import java.util.Scanner;

public class ValidationMenuTransaction {

    private static Scanner sc = new Scanner(System.in);


    public static double validateAmount() {
        double amount = 0;
        boolean valid = false;

        do {
            System.out.print("Entre amount: ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Amount ne peut pas être vide. Réessayez.");
                continue;
            }

            try {
                amount = Double.parseDouble(input);
                if (amount == 0) {
                    return -1;
                }
                if (amount < 0) {
                    System.out.println("Amount doit être supérieur à 0. Réessayez.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez entrer un nombre valide.");
            }

        } while (!valid);

        return amount;
    }



    public static FeeLevel validatorFeeLevel() {
        int choice = 0;
        do{
            System.out.println("1 : ECONOMIQUE");
            System.out.println("2 : STANDARD");
            System.out.println("3 : RAPIDE");
            System.out.print("Choice: ");
            if(sc.hasNextInt()){
                choice = sc.nextInt();
                if(choice != 1 && choice != 2 && choice != 3){
                    System.out.println("Choix invalide, veuillez choisir 1 , 2 ou 3.");
                }
            }else{
                System.out.println("Choix invalide, veuillez entrer un nombre.");
                sc.next();
            }
        }while(choice != 1 && choice != 2 && choice != 3);

        if (choice == 1) return FeeLevel.ECONOMIQUE;
        else if (choice == 2) return FeeLevel.STANDARD;
        else return FeeLevel.RAPIDE;

    }


}

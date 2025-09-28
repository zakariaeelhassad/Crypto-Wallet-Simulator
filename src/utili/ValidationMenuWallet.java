package utili;

import models.enums.CryptoType;

import java.util.Scanner;

public class ValidationMenuWallet {

    private static Scanner sc = new Scanner(System.in);

    public static String validateOwner() {
        String owner;
        do {
            System.out.print("Entre owner : ");
            owner = sc.nextLine();
            if (owner.isEmpty()) {
                System.out.println("Owner ne peut pas être vide. Réessayez.");
            }
        } while (owner.isEmpty());
        return owner;
    }

    public static CryptoType valdiateCryptoType() {
        int choice = 0;
        do {
            System.out.println("1 : BITCOIN");
            System.out.println("2 : ETHEREUM");
            System.out.print("Choice: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Choix invalide, veuillez choisir 1 ou 2.");
                }
            } else {
                System.out.println("Choix invalide, veuillez entrer un nombre.");
                sc.next();
            }
        }while (choice != 1 && choice != 2);

        return (choice == 1) ? CryptoType.BITCOIN : CryptoType.ETHEREUM;
    }
}

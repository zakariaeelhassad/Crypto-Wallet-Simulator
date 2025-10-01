package presentation.menus;

import presentation.MainMenu;
import presentation.TransactionUi;
import utili.ValidationChoix;

import java.util.Scanner;

public class TransactionMenu {

    private final TransactionUi transactionUi;
    private MainMenu mainMenu;
    private final Scanner sc = new Scanner(System.in);

    public TransactionMenu(TransactionUi transactionUi) {
        this.transactionUi = transactionUi;
    }

    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    public void transactionPresentation(){
        int choice;
        ValidationChoix validator = new ValidationChoix();
        do{
            System.out.println("\n=== TRANSACTION MENU ===");
            System.out.println("1. Ajouter une transaction");
            System.out.println("4. Rechercher une transaction par ID");
            System.out.println("5. Afficher toutes les transactions");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choice = validator.validationChoix();

            switch (choice) {
                case 1 : transactionUi.create();
                    break;
                case 0 : mainMenu.showMenu();
                    break;
                default: System.out.println("WChoix invalide !");
            }
        }while(choice != 0);
    }
}

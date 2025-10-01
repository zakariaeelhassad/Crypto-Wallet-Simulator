package presentation.menus;

import presentation.MainMenu;
import presentation.WalletUi;
import utili.ValidationChoix;

import java.util.Scanner;

public class WalletMenu {

    private final WalletUi walletUi;
    private MainMenu mainMenu;
    private final Scanner sc = new Scanner(System.in);

    public WalletMenu(WalletUi walletUi) {
        this.walletUi = walletUi;
    }

    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    public void walletPresentation() {
        int choice;
        ValidationChoix validator = new ValidationChoix();
        do{
            System.out.println("\n=== WALLET MENU ===");
            System.out.println("1. Ajouter un Wallet");
            System.out.println("2. Supprimer un Wallet");
            System.out.println("3. Mettre à jour un Wallet");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix : ");
            choice = validator.validationChoix();

            switch (choice) {
                case 1 : walletUi.create();
                    break;
                case 2 : walletUi.delete();
                    break;
                case 3 : walletUi.update();
                    break;
                case 0 : mainMenu.showMenu();
                    break;
                default: System.out.println("Choix invalide !");
            }
        }while(choice != 0);
    }
}

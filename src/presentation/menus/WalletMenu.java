package presentation.menus;

import presentation.WalletUi;
import utili.ValidationChoix;

import java.util.Scanner;

public class WalletMenu {

    private final WalletUi walletUi;
    private final Scanner sc = new Scanner(System.in);

    public WalletMenu(WalletUi walletUi) {
        this.walletUi = walletUi;
    }

    public void walletPresentation() {
        int choice;
        ValidationChoix validator = new ValidationChoix();
        do{
            System.out.println("1 ^_^ Ajouter Wallet ");
            System.out.println("2 ^_^ delete Wallet ");
            System.out.println("3 ^_^ update Wallet ");
            System.out.println("4 ^_^ find by id Wallet ");
            System.out.println("5 ^_^ find all Wallet ");

            System.out.println("0 ^_^ GO BACK TO THE MAIN MENU");
            System.out.print("Enter your choice");
            choice = validator.validationChoix();

            switch (choice) {
                case 1 : walletUi.create();
                    break;
                case 2 : walletUi.delete();
                    break;
                case 3 : walletUi.update();
                    break;
                default: System.out.println("Wrong choice");
            }
        }while(choice != 0);
    }
}

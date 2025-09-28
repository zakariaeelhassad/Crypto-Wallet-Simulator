package presentation;

import presentation.menus.TransactionMenu;
import presentation.menus.WalletMenu;
import utili.ValidationChoix;

import java.util.Scanner;

public class MainMenu {

    public final Scanner sc = new Scanner(System.in);
    private final WalletMenu walletMenu;
    private final TransactionMenu transactionMenu;

    public MainMenu(WalletMenu walletMenu , TransactionMenu transactionMenu) {
        this.walletMenu = walletMenu;
        this.transactionMenu = transactionMenu;
    }

    public void showMenu() {
        int choice ;
        ValidationChoix validator = new ValidationChoix();

        do{
            System.out.println("1 : Wallet ");
            System.out.println("2 : transaction ");
            System.out.print("choice : ");
            choice = validator.validationChoix();

            switch (choice) {
                case 1: walletMenu.walletPresentation();
                    break;
                case 2:transactionMenu.transactionPresentation();
                    break;
                case 0 : System.exit(0);
                    break;
                default:System.out.println("Wrong choice");
                    break;
            };
        }while(choice != 0);
    }
}

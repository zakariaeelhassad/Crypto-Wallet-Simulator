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
            System.out.println("1 ^_^ Ajouter transaction ");
            System.out.println("4 ^_^ find by id transaction ");
            System.out.println("5 ^_^ find all transaction ");

            System.out.println("0 ^_^ GO BACK TO THE MAIN MENU");
            System.out.print("Enter your choice");
            choice = validator.validationChoix();

            switch (choice) {
                case 1 : transactionUi.create();
                    break;
                case 0 : mainMenu.showMenu();
                    break;
                default: System.out.println("Wrong choice");
            }
        }while(choice != 0);
    }
}

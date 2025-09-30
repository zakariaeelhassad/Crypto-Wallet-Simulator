package presentation.menus;

import presentation.MainMenu;
import presentation.MempoolUi;
import utili.ValidationChoix;

import java.util.Scanner;


public class MempoolMenu {
    private final MempoolUi mempoolUi;
    private MainMenu mainMenu;
    private final Scanner scanner = new Scanner(System.in);

    public MempoolMenu(MempoolUi mempoolUi) {
        this.mempoolUi = mempoolUi;
    }

    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    public void MempoolPresentation() {
        int choice;
        ValidationChoix validator = new ValidationChoix();
        do{
            System.out.println("1 ^_^ list trasaction dans mempool ");

            System.out.println("0 ^_^ GO BACK TO THE MAIN MENU");
            System.out.print("Enter your choice");
            choice = validator.validationChoix();

            switch (choice) {
                case 1 : mempoolUi.displayAllTransactionsInMempool();
                    break;
                case 0: mainMenu.showMenu();
                    break;
                default: System.out.println("Wrong choice");
            }
        }while(choice != 0);
    }


}

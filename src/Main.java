//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import presentation.MainMenu;
import presentation.MempoolUi;
import presentation.TransactionUi;
import presentation.WalletUi;
import presentation.menus.MempoolMenu;
import presentation.menus.TransactionMenu;
import presentation.menus.WalletMenu;
import repositories.IMempoolRepository;
import repositories.ITransactionRepository;
import repositories.IWalletRepository;
import repositories.impl.MempoolRepository;
import repositories.impl.TransactionRepository;
import repositories.impl.WalletRepository;
import services.IMempoolService;
import services.ITransactionService;
import services.IWalletService;
import services.impl.MempoolService;
import services.impl.WalletService;
import services.impl.TransactionService;


public class Main {
    public static void main(String[] args) {
        IWalletRepository walletRepository = new WalletRepository();
        IWalletService walletService = new WalletService(walletRepository);
        WalletUi walletUi = new WalletUi(walletService);
        WalletMenu walletMenu = new WalletMenu(walletUi);

        IMempoolRepository mempoolRepository = new MempoolRepository();
        IMempoolService mempoolService = new MempoolService(mempoolRepository);
        MempoolUi mempoolUi = new MempoolUi(mempoolService);
        MempoolMenu mempolMenu = new MempoolMenu(mempoolUi);

        ITransactionRepository transactionRepository = new TransactionRepository();
        ITransactionService transactionService = new TransactionService(transactionRepository);
        TransactionUi transactionUi = new TransactionUi(transactionService , mempoolService);
        TransactionMenu transactionMenu = new TransactionMenu(transactionUi);

        MainMenu  mainMenu = new MainMenu(walletMenu , transactionMenu , mempolMenu);
        walletMenu.setMainMenu(mainMenu);
        transactionMenu.setMainMenu(mainMenu);
        mempolMenu.setMainMenu(mainMenu);
        mainMenu.showMenu();
    }
}
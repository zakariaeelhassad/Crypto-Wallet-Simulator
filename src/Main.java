//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import config.JdbcConnection;
import presentation.MainMenu;
import presentation.TransactionUi;
import presentation.WalletUi;
import presentation.menus.TransactionMenu;
import presentation.menus.WalletMenu;
import repositories.ITransactionRepository;
import repositories.IWalletRepository;
import repositories.impl.TransactionRepository;
import repositories.impl.WalletRepository;
import services.ITransactionService;
import services.IWalletService;
import services.impl.WalletService;
import services.impl.TransactionService;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        IWalletRepository walletRepository = new WalletRepository();
        IWalletService walletService = new WalletService(walletRepository);
        WalletUi walletUi = new WalletUi(walletService);
        WalletMenu walletMenu = new WalletMenu(walletUi);

        ITransactionRepository transactionRepository = new TransactionRepository();
        ITransactionService transactionService = new TransactionService(transactionRepository);
        TransactionUi transactionUi = new TransactionUi(transactionService);
        TransactionMenu transactionMenu = new TransactionMenu(transactionUi);

        MainMenu  mainMenu = new MainMenu(walletMenu , transactionMenu);

        mainMenu.showMenu();
    }
}
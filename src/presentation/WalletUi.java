package presentation;

import models.entities.Wallet;
import models.enums.CryptoType;
import services.IWalletService;
import utili.ValidationMenuWallet;

import java.util.Scanner;
import java.util.UUID;


public class WalletUi {

    private final IWalletService service;
    private final Scanner sc = new Scanner(System.in);

    public WalletUi(IWalletService service) {
        this.service = service;
    }

    public Wallet create(){

        String owner = ValidationMenuWallet.validateOwner();

        double balance = 0 ;

        CryptoType type = ValidationMenuWallet.valdiateCryptoType();

        String id = UUID.randomUUID().toString();

        String address = service.generateAddress(type);

        Wallet wallet = new Wallet(id, address, balance, owner, type);

        Wallet createWallet = service.create(wallet);

        System.out.println("Wallet created successfully!");
        System.out.println("ID: " + createWallet.getId());
        System.out.println("Address: " + createWallet.getAddress());
        System.out.println("Owner: " + createWallet.getOwner());
        System.out.println("Balance: " + createWallet.getBalance());
        System.out.println("type : " + createWallet.getType());

        return createWallet;
    }

    public void update(){

        System.out.print("Entre id : ");
        String idStr = sc.nextLine();

        try{
            UUID id = UUID.fromString(idStr);

            System.out.print("modifier owner : ");
            String owner = sc.nextLine();
            System.out.print("modifier balance");
            double balance = sc.nextDouble();
            sc.nextLine();

            Wallet wallet = new Wallet(id.toString(), null, balance, owner, null);
            service.update(wallet);
        }catch(IllegalArgumentException  e){
            System.out.println("Invalid input");
        }
    }

    public void delete(){
        System.out.print("Entre id : ");
        String idStr = sc.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            service.delete(id);
            System.out.println("Wallet deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("ID invalide !");
        }
    }
}

package presentation;

import models.entities.Mempool;
import models.entities.Transaction;
import services.IMempoolService;

import java.util.List;
import java.util.Scanner;

public class MempoolUi {

    private final IMempoolService service;
    private final Scanner sc = new Scanner(System.in);


    public MempoolUi(IMempoolService service) {
        this.service = service;
    }

    public void addTransactionToMempool(Transaction transaction) {
        if (transaction == null) {
            System.out.println("Aucune transaction à ajouter au mempool.");
            return;
        }

        boolean added = service.addTransactionToMempool(transaction);
        if (added) {
            System.out.println("Transaction ajoutée au mempool avec succès !");
        } else {
            System.out.println("Erreur : impossible d'ajouter la transaction au mempool.");

        }
    }

    public void displayAllTransactionsInMempool() {
        List<Mempool> mempoolList = service.getAllTransactionsInMempool();
        if (mempoolList.isEmpty()) {
            System.out.println("Le mempool est vide.");
            return;
        }

        System.out.println("=== Transactions dans le mempool ===");
        for (Mempool m : mempoolList) {
            Transaction t = m.getTransaction();
            System.out.println("Transaction ID: " + t.getId());
            System.out.println("Source Address: " + t.getSourceAddress());
            System.out.println("Destination Address: " + t.getDestinationAddress());
            System.out.println("Amount: " + t.getAmount());
            System.out.println("Fees: " + t.getFees());
            System.out.println("Fee Level: " + t.getFeeLevel());
            System.out.println("Status: " + t.getStatus());
            System.out.println("Position: " + m.getPosition());
            System.out.println("Created At: " + m.getCreatedAt());
            System.out.println("---------------------------");
        }
    }
}

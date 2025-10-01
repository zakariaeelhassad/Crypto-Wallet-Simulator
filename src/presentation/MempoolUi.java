package presentation;

import models.entities.Mempool;
import services.IMempoolService;

import java.util.List;

public class MempoolUi {

    private final IMempoolService service;

    public MempoolUi(IMempoolService service) {
        this.service = service;
    }

    public void afficherMempool() {
        List<Mempool> mempool = service.getMempool();
        if (mempool.isEmpty()) {
            System.out.println("Mempool vide !");
        } else {
            System.out.printf("%-40s | %-10s | %-10s | %-20s%n",
                    "TxID", "FeeLevel", "Position", "CreatedAt");
            System.out.println(new String(new char[90]).replace("\0", "="));

            for (Mempool m : mempool) {
                System.out.printf("%-40s | %-10s | %-10d | %-20s%n",
                        m.getTransactionId(),
                        m.getFeeLevel(),
                        m.getPosition(),
                        m.getCreatedAt());
            }
        }
    }


}

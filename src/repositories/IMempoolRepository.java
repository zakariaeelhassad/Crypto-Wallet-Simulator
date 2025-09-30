package repositories;

import models.entities.Mempool;
import models.entities.Transaction;

import java.util.List;

public interface IMempoolRepository {

    boolean addTransactionToMempool(Transaction transaction);
    List<Mempool> getAllTransactionsInMempool();
}

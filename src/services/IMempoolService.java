package services;

import models.entities.Mempool;
import models.entities.Transaction;

import java.util.List;

public interface IMempoolService {

    boolean addTransactionToMempool(Transaction transaction);
    List<Mempool> getAllTransactionsInMempool();
}

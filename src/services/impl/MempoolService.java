package services.impl;

import models.entities.Mempool;
import models.entities.Transaction;
import repositories.IMempoolRepository;
import services.IMempoolService;

import java.util.List;

public class MempoolService implements IMempoolService {

    private final IMempoolRepository repository ;

    public MempoolService(IMempoolRepository repository) {
        this.repository = repository;
    }

    public boolean addTransaction(Transaction transaction) {
        return repository.addTransactionToMempool(transaction);
    }

    public List<Mempool> getMempool() {
        return repository.getAllTransactionsInMempool();
    }
}

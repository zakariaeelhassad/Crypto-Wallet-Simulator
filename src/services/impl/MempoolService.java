package services.impl;

import models.entities.Mempool;
import models.entities.Transaction;
import repositories.IMempoolRepository;
import services.IMempoolService;

import java.util.List;

public class MempoolService implements IMempoolService {
    private IMempoolRepository repository;

    public MempoolService(IMempoolRepository repository) {
        this.repository = repository;
    }

    public boolean addTransactionToMempool(Transaction transaction) {
        return repository.addTransactionToMempool(transaction);
    }

    public List<Mempool> getAllTransactionsInMempool() {
        return repository.getAllTransactionsInMempool();
    }
}

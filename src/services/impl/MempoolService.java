package services.impl;

import models.entities.Mempool;
import models.entities.Transaction;
import repositories.IMempoolRepository;
import services.IMempoolService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MempoolService implements IMempoolService {

    private final IMempoolRepository repository ;

    public MempoolService(IMempoolRepository repository) {
        this.repository = repository;
    }

    public boolean addTransaction(Transaction transaction) {
        return repository.addTransactionToMempool(transaction);
    }

    public List<Mempool> getMempool() {
        return repository.getAllTransactionsInMempool()
                .stream()
                .filter(mempool -> mempool.getTransaction().getFees() > 0) // غير اللي عندهم fees > 0
                .sorted(Comparator.comparing(
                        (Mempool m) -> m.getTransaction().getFees()
                ).reversed()) // ترتيب تنازلي حسب fees
                .collect(Collectors.toList());
    }
}

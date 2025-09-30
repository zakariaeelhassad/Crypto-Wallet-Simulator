package services;

import models.entities.Transaction;
import models.entities.Wallet;

import java.util.List;
import java.util.UUID;

public interface ITransactionService {

    List<Transaction> findAllTransaction(UUID id);
    Transaction findById(UUID id);
    Transaction create(Transaction transaction);
    Wallet getWalletByAddress(String sourceAddress);


}

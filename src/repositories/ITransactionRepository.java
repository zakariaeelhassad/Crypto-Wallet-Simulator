package repositories;

import models.entities.Transaction;
import models.entities.Wallet;

import java.util.UUID;

public interface ITransactionRepository {

    Wallet getWalletByAddress(String sourceAddress);
    Transaction createTransaction(Transaction transaction);
}

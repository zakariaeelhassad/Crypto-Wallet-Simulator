package services.impl;

import models.entities.Transaction;
import models.entities.Wallet;
import models.enums.CryptoType;
import models.enums.FeeLevel;
import repositories.ITransactionRepository;
import services.ITransactionService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ITransactionService {

    private final ITransactionRepository repository;

    public TransactionService(ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction create(Transaction transaction) {
        Wallet sourceWallet = repository.getWalletByAddress(transaction.getSourceAddress());
        Wallet destinationWallet = repository.getWalletByAddress(transaction.getDestinationAddress());

        if (sourceWallet == null || destinationWallet == null) {
            System.out.println("Wallet source ou destination introuvable");
            return null;
        }

        double fee = calculateFee(transaction.getCryptoType(), transaction.getFeeLevel(), transaction.getAmount());
        transaction.setFees(fee);

        double totalDebit = transaction.getAmount() + fee;
        if (sourceWallet.getBalance() < totalDebit) {
            System.out.println("Solde insuffisant pour l'adresse source");
            return null;
        }

        sourceWallet.setBalance(sourceWallet.getBalance() - totalDebit);
        destinationWallet.setBalance(destinationWallet.getBalance() + transaction.getAmount());

        return repository.createTransaction(transaction, sourceWallet, destinationWallet);
    }

    @Override
    public List<Transaction> findAllTransaction(UUID id) {
        return Collections.emptyList();
    }

    @Override
    public Transaction findById(UUID id) {
        return null;
    }

    public Wallet getWalletByAddress(String sourceAddress) {
        return repository.getWalletByAddress(sourceAddress);
    }

    private double calculateFee(CryptoType type, FeeLevel level, double amount) {
        switch (type) {
            case BITCOIN:
                return calculateBtcFee(level);
            case ETHEREUM:
                return calculateEthFee(level);
            default:
                return 0.0;
        }
    }

    private double calculateBtcFee(FeeLevel level) {
        int txSize = 250;
        int pricePerByte;

        switch (level) {
            case ECONOMIQUE:
                pricePerByte = 10;
                break;
            case STANDARD:
                pricePerByte = 50;
                break;
            case RAPIDE:
                pricePerByte = 100;
                break;
            default:
                pricePerByte = 50;
        }

        return (txSize * pricePerByte) * 1e-8;
    }

    private double calculateEthFee(FeeLevel level) {
        int gasLimit = 21000;
        int gasPrice;

        switch (level) {
            case ECONOMIQUE:
                gasPrice = 20;
                break;
            case STANDARD:
                gasPrice = 50;
                break;
            case RAPIDE:
                gasPrice = 100;
                break;
            default:
                gasPrice = 50;
        }

        return gasLimit * gasPrice * 1e-9;
    }
}

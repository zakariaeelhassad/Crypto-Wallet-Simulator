package models.entities;

import models.enums.CryptoType;
import models.enums.FeeLevel;
import models.enums.TransactionStatus;

public class Transaction {

    private String id;
    private String walletId;
    private String sourceAddress;
    private String destinationAddress;
    private double amount;
    private double fees;
    private CryptoType cryptoType;
    private FeeLevel feeLevel;
    private TransactionStatus status;

    public Transaction(String id, String walletId, String sourceAddress, String destinationAddress,
                       double amount, CryptoType cryptoType, FeeLevel feeLevel, TransactionStatus status) {
        this.id = id;
        this.walletId = walletId;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.amount = amount;
        this.cryptoType = cryptoType;
        this.feeLevel = feeLevel;
        this.status = status;
        this.fees = 0.0;
    }


    public String getId() {
        return id;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public double getAmount() {
        return amount;
    }

    public double getFees() {
        return fees;
    }

    public CryptoType getCryptoType() {
        return cryptoType;
    }

    public FeeLevel getFeeLevel() {
        return feeLevel;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public void setCryptoType(CryptoType cryptoType) {
        this.cryptoType = cryptoType;
    }

    public void setFeeLevel(FeeLevel feeLevel) {
        this.feeLevel = feeLevel;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

}

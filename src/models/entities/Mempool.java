package models.entities;

import models.entities.Transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mempool {

    private UUID id;
    private UUID transactionId;
    private Transaction transaction;
    private String feeLevel;
    private int position;
    private LocalDateTime createdAt;

    public Mempool(UUID id, UUID transactionId, String feeLevel, int position, LocalDateTime createdAt) {
        this.id = id;
        this.transactionId = transactionId;
        this.feeLevel = feeLevel;
        this.position = position;
        this.createdAt = createdAt;
    }

    public Mempool( UUID id ,UUID transactionId, String feeLevel, int position) {
        this.id = id;
        this.transactionId = transactionId;
        this.feeLevel = feeLevel;
        this.position = position;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(String feeLevel) {
        this.feeLevel = feeLevel;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

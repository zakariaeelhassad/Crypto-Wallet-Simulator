package models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mempool {
    private UUID id;
    private UUID transactionId;
    private String feeLevel;
    private int position;
    private LocalDateTime createdAt;
    private Transaction transaction;

    public Mempool(UUID id, UUID transactionId, String feeLevel, int position, LocalDateTime createdAt) {
        this.id = id;
        this.transactionId = transactionId;
        this.feeLevel = feeLevel;
        this.position = position;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getFeeLevel() {
        return feeLevel;
    }

    public int getPosition() {
        return position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void show(){
        System.out.println("ID: " + id);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Fee Level: " + feeLevel);
        System.out.println("Position: " + position);

    }
}

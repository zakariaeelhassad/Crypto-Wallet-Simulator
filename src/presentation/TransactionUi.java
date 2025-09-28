package presentation;


import models.entities.Transaction;
import models.entities.Wallet;
import models.enums.CryptoType;
import models.enums.FeeLevel;
import models.enums.TransactionStatus;
import services.ITransactionService;
import utili.ValidationAddress;
import utili.ValidationMenuTransaction;

import java.security.CryptoPrimitive;
import java.util.Scanner;
import java.util.UUID;

public class TransactionUi {

    private final ITransactionService service;
    private final Scanner sc = new Scanner(System.in);

    public TransactionUi(ITransactionService service) {
        this.service = service;
    }

    public Transaction create() {

        String sourceAddress;
        Wallet wallet;
        String walletId;
        CryptoType cryptoType;

        while (true) {
            System.out.print("Entre source Address: ");
            sourceAddress = sc.nextLine().trim();

            cryptoType = ValidationAddress.getCryptoType(sourceAddress);
            if (cryptoType == null) {
                System.out.println("Source address format unsupported. Use BTC (0x...) or ETH (bc1...)");
                continue;
            }
            wallet = service.getWalletByAddress(sourceAddress);
            walletId = wallet.getId();
            if (wallet.getId() == null) {
                System.out.println("Source address n'existe pas dans la base de données");
                continue;
            }

            break;
        }

        String destinationAddress;
        while (true) {
            System.out.print("Entre destination Address: ");
            destinationAddress = sc.nextLine().trim();

            CryptoType destType = ValidationAddress.getCryptoType(destinationAddress);
            if (destType == null || destType != cryptoType) {
                System.out.println("Destination address must be the same type as source: " + cryptoType);
                continue;
            }
            break;
        }

        double amount;
        while (true) {
            amount = ValidationMenuTransaction.validateAmount();

            if (amount == -1) {
                System.out.println("Retour au menu principal...");
                return null;
            }

            if (amount > wallet.getBalance()) {
                System.out.println("Solde insuffisant. Votre solde est: " + wallet.getBalance());
                continue;
            }

            break;
        }


        FeeLevel feeLevel = ValidationMenuTransaction.validatorFeeLevel();

        String id = UUID.randomUUID().toString();

        Transaction transaction = new Transaction(id, walletId, sourceAddress, destinationAddress, amount, cryptoType, feeLevel, TransactionStatus.PENDING);

        Transaction createdTransaction = service.create(transaction);

        if (createdTransaction != null) {
            System.out.println("Transaction created successfully");
            System.out.println("ID: " + createdTransaction.getId());
            System.out.println("Source Address: " + createdTransaction.getSourceAddress());
            System.out.println("Destination Address: " + createdTransaction.getDestinationAddress());
            System.out.println("Amount: " + createdTransaction.getAmount());
            System.out.println("Fees: " + createdTransaction.getFees());
            System.out.println("Fee level: " + createdTransaction.getFeeLevel());
            System.out.println("Status: " + createdTransaction.getStatus());
        }

        return createdTransaction;
    }


}

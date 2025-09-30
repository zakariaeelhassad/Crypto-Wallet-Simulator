package repositories.impl;

import config.JdbcConnection;
import models.entities.Transaction;
import models.entities.Wallet;
import models.enums.CryptoType;
import repositories.ITransactionRepository;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionRepository implements ITransactionRepository {

    private static final Logger logger = Logger.getLogger(TransactionRepository.class.getName());
    Connection connection = JdbcConnection.getInstance().getConnection();

    public Wallet getWalletByAddress(String sourceAddress) {
        Wallet wallet = null;
        String sql = "SELECT id, address, owner, balance, type FROM wallets WHERE address = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sourceAddress);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    wallet = new Wallet(
                            rs.getString("id"),
                            rs.getString("address"),
                            rs.getDouble("balance"),
                            rs.getString("owner"),
                            CryptoType.valueOf(rs.getString("type"))  // تحويل النص لـ Enum
                    );
                    logger.info("Wallet trouvé pour l'adresse: " + sourceAddress + " -> ID: " + wallet.getId());
                } else {
                    logger.warning("Aucun wallet trouvé pour l'adresse: " + sourceAddress);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la recherche du wallet par adresse: " + sourceAddress, e);
        }

        return wallet;
    }


    public Transaction createTransaction(Transaction transaction , Wallet sourceWallet, Wallet destinationWallet) {
        Wallet wallet = getWalletByAddress(transaction.getSourceAddress());
        if (wallet == null) {
            logger.warning("Transaction annulée: wallet source introuvable pour l'adresse " + transaction.getSourceAddress());
            return null;
        }

        String sqlUpdateWallet  = "UPDATE wallets SET balance = ? WHERE id = ?";
        String sql = "INSERT INTO transactions " +
                "(id, wallet_id, from_address, to_address, amount, fee, fee_level, status) " +
                "VALUES (?, ?::uuid, ?, ?, ?, ?, ?::fee_level, ?::tx_status)";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(sqlUpdateWallet)) {
                stmt.setDouble(1, sourceWallet.getBalance());
                stmt.setObject(2, UUID.fromString(sourceWallet.getId()));
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = connection.prepareStatement(sqlUpdateWallet)) {
                stmt.setDouble(1, destinationWallet.getBalance());
                stmt.setObject(2, UUID.fromString(destinationWallet.getId()));
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setObject(1, UUID.fromString(transaction.getId()));
                stmt.setObject(2, UUID.fromString(sourceWallet.getId()));
                stmt.setString(3, transaction.getSourceAddress());
                stmt.setString(4, transaction.getDestinationAddress());
                stmt.setDouble(5, transaction.getAmount());
                stmt.setDouble(6, transaction.getFees());
                stmt.setString(7, transaction.getFeeLevel().toString());
                stmt.setString(8, transaction.getStatus().toString());
                stmt.executeUpdate();
            }

            connection.commit();
            return transaction;

        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return null;
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}

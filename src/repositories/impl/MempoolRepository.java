package repositories.impl;

import config.JdbcConnection;
import models.entities.Mempool;
import models.entities.Transaction;
import models.enums.CryptoType;
import models.enums.FeeLevel;
import models.enums.TransactionStatus;
import repositories.IMempoolRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MempoolRepository implements IMempoolRepository {

    private final Connection connection = JdbcConnection.getInstance().getConnection();
    private static final Logger logger = Logger.getLogger(MempoolRepository.class.getName());

    public boolean addTransactionToMempool(Transaction transaction) {
        String sql = "INSERT INTO mempool (transaction_id, fee_level, position) VALUES (?, ?::fee_level, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, UUID.fromString(transaction.getId()));
            stmt.setString(2, transaction.getFeeLevel().toString());
            stmt.setInt(3, 0); // position sera calculée plus tard
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Mempool> getAllTransactionsInMempool() {
        List<Mempool> mempoolList = new ArrayList<>();
        String sql = "SELECT m.id, m.transaction_id, m.fee_level, m.position, m.created_at, " +
                "t.from_address, t.to_address, t.amount, t.fee, t.status, t.wallet_id, " +
                "w.type AS crypto_type, t.fee_level as tx_fee_level, t.status as tx_status " +
                "FROM mempool m " +
                "JOIN transactions t ON m.transaction_id = t.id " +
                "JOIN wallets w ON t.wallet_id = w.id " +
                "ORDER BY m.position ASC";


        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Transaction transaction = new Transaction(
                        rs.getObject("transaction_id").toString(),
                        rs.getObject("wallet_id").toString(),
                        rs.getString("from_address"),
                        rs.getString("to_address"),
                        rs.getDouble("amount"),
                        rs.getString("crypto_type") != null ? CryptoType.valueOf(rs.getString("crypto_type")) : null,
                        rs.getString("tx_fee_level") != null ? FeeLevel.valueOf(rs.getString("tx_fee_level")) : null,
                        rs.getString("tx_status") != null ? TransactionStatus.valueOf(rs.getString("tx_status")) : null
                );
                transaction.setFees(rs.getDouble("fee"));

                // Création dyal Mempool object
                Mempool mempool = new Mempool(
                        (UUID) rs.getObject("id"),
                        (UUID) rs.getObject("transaction_id"),
                        rs.getString("fee_level"),
                        rs.getInt("position"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );

                mempool.setTransaction(transaction); // Connecter transaction m3a mempool
                mempoolList.add(mempool);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la récupération du mempool", e);
        }

        return mempoolList;
    }

}

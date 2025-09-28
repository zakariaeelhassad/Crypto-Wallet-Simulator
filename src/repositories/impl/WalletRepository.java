package repositories.impl;

import config.JdbcConnection;
import models.entities.Wallet;
import models.enums.CryptoType;
import repositories.IWalletRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WalletRepository implements IWalletRepository {

    private static final Logger logger = Logger.getLogger(WalletRepository.class.getName());
    Connection connection = JdbcConnection.getInstance().getConnection();

    @Override
    public Wallet createWallet(Wallet wallet) {
        String sql = "INSERT INTO wallets (id , address , type , owner , balance ) VALUES (? , ? , ?::crypto_type , ? , ? )";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1 , UUID.fromString(wallet.getId()));
            stmt.setString(2 , wallet.getAddress());
            stmt.setString(3 , wallet.getType().toString());
            stmt.setString(4 , wallet.getOwner());
            stmt.setDouble(5 , wallet.getBalance());

            int rows = stmt.executeUpdate();

            if ( rows > 0 ) {
                logger.info("Wallet has been created: " + wallet.getId());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur création wallet: " + wallet.getId(), e);
        }

        return wallet;
    }

    @Override
    public void updateWallet(Wallet wallet) {
        String sql = "UPDATE wallets SET owner = ?, balance = ? WHERE id = ?::uuid";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, wallet.getOwner());
            stmt.setDouble(2, wallet.getBalance());
            stmt.setObject(3, UUID.fromString(wallet.getId()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("Wallet mis à jour avec succès: " + wallet.getId());
            } else {
                logger.warning("Aucun wallet trouvé avec l'id: " + wallet.getId());
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur update wallet: " + wallet.getId(), e);
        }
    }

    @Override
    public List<Wallet> findAllWallets() {
        return null;
    }

    @Override
    public Wallet findWalletById(UUID id) {
        return null;
    }

    @Override
    public void deleteWallet(UUID id) {
        String sql = "DELETE FROM wallets WHERE id = ?::uuid";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1,id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("Wallet supprimé: " + id);
            } else {
                logger.warning("Aucun wallet trouvé pour suppression avec l'id: " + id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur suppression wallet: " + id, e);
        }
    }

}

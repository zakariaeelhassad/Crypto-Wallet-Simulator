package repositories;

import models.entities.Wallet;

import java.util.List;
import java.util.UUID;

public interface IWalletRepository {

    List<Wallet> findAllWallets();
    Wallet findWalletById(UUID id);
    Wallet createWallet(Wallet wallet);
    void updateWallet(Wallet wallet);
    void deleteWallet(UUID id);
}

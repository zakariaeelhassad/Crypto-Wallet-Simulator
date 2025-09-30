package services;

import models.entities.Wallet;
import models.enums.CryptoType;

import java.util.List;
import java.util.UUID;

public interface IWalletService {

    List<Wallet> findAllWallet();
    Wallet findById(UUID id);
    Wallet create(Wallet wallet);
    void update(Wallet wallet);
    void delete(UUID id);
    String generateAddress(CryptoType type);
}

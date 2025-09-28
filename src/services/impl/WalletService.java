package services.impl;

import models.entities.Wallet;
import models.enums.CryptoType;
import repositories.IWalletRepository;
import services.IWalletService;

import java.util.*;

public class WalletService implements IWalletService {

    private final IWalletRepository repository;
    private Map<String , Wallet> wallets = new HashMap<>();

    public WalletService(IWalletRepository repository) {
        this.repository = repository;
    }

    public Wallet create(Wallet wallet) {
        return repository.createWallet(wallet);
    }

    public void update(Wallet wallet){
        repository.updateWallet(wallet);
    }

    public List<Wallet> findAllWallet(){
        return null;
    }

    public Wallet findById(UUID id){
        return null;
    }

    public void delete(UUID id){
        repository.deleteWallet(id);
    }

    public String generateAddress(CryptoType type) {
        Random random = new Random();
        String caracteres = "0123456789abcdef";
        String bech32Chars = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";
        String address;

        do {
            if (type == CryptoType.ETHEREUM) {
                StringBuilder sb = new StringBuilder("0x");
                for (int i = 0; i < 40; i++) {
                    sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
                }
                address = sb.toString();
            } else {
                StringBuilder sb = new StringBuilder("bc1");
                for (int i = 0; i < 39; i++) {
                    sb.append(bech32Chars.charAt(random.nextInt(bech32Chars.length())));
                }
                address = sb.toString();
            }
        } while (wallets.containsKey(address));

        return address;
    }


}

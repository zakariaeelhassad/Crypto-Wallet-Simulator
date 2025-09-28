package models.enums;

public enum CryptoType {
    BITCOIN, ETHEREUM;

    public static CryptoType fromInt(int number) {
        switch (number) {
            case 1:
                return CryptoType.BITCOIN;
            case 2:
                return CryptoType.ETHEREUM;
            default:
                return CryptoType.BITCOIN;
        }
    }
}

package models.enums;

public enum TransactionStatus {
    PENDING, CONFIRMED, REJECTED;

    public static TransactionStatus fromInt(int number) {
        switch (number) {
            case 1:
                return TransactionStatus.PENDING;
            case 2:
                return TransactionStatus.CONFIRMED;
            case 3:
                return TransactionStatus.REJECTED;
            default:
                return TransactionStatus.PENDING;
        }
    }

}

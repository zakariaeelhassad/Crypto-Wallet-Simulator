package models.enums;

public enum FeeLevel {
    ECONOMIQUE, STANDARD, RAPIDE;

    public static FeeLevel valueOf(int number) {
        switch (number) {
            case 1:
                return FeeLevel.ECONOMIQUE;
            case 2:
                return FeeLevel.STANDARD;
            case 3:
                return FeeLevel.RAPIDE;
            default:
                return FeeLevel.RAPIDE;
        }
    }
}

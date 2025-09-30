    package utili;

    import models.enums.CryptoType;

    public final class ValidationAddress {

        private ValidationAddress() {}

        public static boolean isBtcAddress(String address){
            if(address==null){
                return false;
            }else{
                return address.startsWith("0x");
            }
        }

        public static boolean isEthAddress(String address){
            if(address==null){
                return false;
            }else{
                return address.startsWith("bc1");
            }
        }

        public static CryptoType getCryptoType(String address){
            if (isBtcAddress(address)) {
                return CryptoType.BITCOIN;
            }else if (isEthAddress(address)) {
                return CryptoType.ETHEREUM;
            }else {
                return null ;
            }
        }
    }

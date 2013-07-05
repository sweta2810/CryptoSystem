package keyGen;

/**
 * 
 * @author Nishant
 *
 */

public class KeyTuple {

        private double[][] privateKey;
        private double[][] publicKey;
        
        public KeyTuple(double[][] pub, double[][] priv){
                
                publicKey = pub;
                privateKey = priv;
                
        }
        
        public double[][] getPrivateKey() {
                return privateKey;
        }
        public void setPrivateKey(double[][] privateKey) {
                this.privateKey = privateKey;
        }
        public double[][] getPublicKey() {
                return publicKey;
        }
        public void setPublicKey(double[][] publicKey) {
                this.publicKey = publicKey;
        }
        
        
        
}
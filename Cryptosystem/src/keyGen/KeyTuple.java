package keyGen;


import Jama.Matrix;

public class KeyTuple {

        private Matrix privateKey;
        private Matrix publicKey;
        
        public KeyTuple(double[][] pub, double[][] priv){
                
                publicKey = new Matrix(pub);
                privateKey = new Matrix(priv);
                
        }
        
        public Matrix getPrivateKey() {
                return privateKey;
        }
        public void setPrivateKey(Matrix privateKey) {
                this.privateKey = privateKey;
        }
        public Matrix getPublicKey() {
                return publicKey;
        }
        public void setPublicKey(Matrix publicKey) {
                this.publicKey = publicKey;
        }
        
        
        
}
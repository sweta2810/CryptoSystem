package keyGen;

import java.math.BigInteger;

public class KeyTuple {

<<<<<<< HEAD
        private BigInteger[][] privateKey;
        private BigInteger[][] publicKey;
        
        public KeyTuple(BigInteger[][] pub, BigInteger[][] priv){
                
                publicKey = pub;
                privateKey = priv;
                
        }
        
        public BigInteger[][] getPrivateKey() {
                return privateKey;
        }
        public void setPrivateKey(BigInteger[][] privateKey) {
                this.privateKey = privateKey;
        }
        public BigInteger[][] getPublicKey() {
                return publicKey;
        }
        public void setPublicKey(BigInteger[][] publicKey) {
                this.publicKey = publicKey;
        }
        
        
        
}
=======
	private BigInteger[][] privateKey;
	private BigInteger[][] publicKey;
	
	public KeyTuple(BigInteger[][] pub, BigInteger[][] priv){
		
		publicKey = pub;
		privateKey = priv;
		
	}
	
	public BigInteger[][] getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(BigInteger[][] privateKey) {
		this.privateKey = privateKey;
	}
	public BigInteger[][] getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(BigInteger[][] publicKey) {
		this.publicKey = publicKey;
	}
	
	
	
}
>>>>>>> fedb4c6c5e92a6f01298b3fcc43692001391676d

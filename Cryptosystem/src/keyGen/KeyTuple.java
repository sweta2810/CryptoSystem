package keyGen;

public class KeyTuple {

	private long[][] privateKey;
	private long[][] publicKey;
	
	public KeyTuple(long[][] pub, long[][] priv){
		
		publicKey = pub;
		privateKey = priv;
		
	}
	
	public long[][] getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(long[][] privateKey) {
		this.privateKey = privateKey;
	}
	public long[][] getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(long[][] publicKey) {
		this.publicKey = publicKey;
	}
	
	
	
}

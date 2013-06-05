package test;

import decrypt.CiphertextDecrypter;
import encrypt.PlaintextEncrypter;
import keyGen.KeyTuple;
import keyGen.TrapdoorSampler;
import homomorphicOperations.HomomorphicOps;

public class CryptosystemTest {

	public static void main(String[] args) {
		overallTest();
	}
	
	private static void overallTest(){
		long testSecurityParameter = 5000;
		long testModulus = 0;
		
		KeyTuple outputTuple = TrapdoorSampler.trapSamp(testSecurityParameter, testModulus);

		long[][] privateKey = outputTuple.getPrivateKey();
		long[][] publicKey = outputTuple.getPublicKey();
		
		//Initialise plaintext matrix. Should make random eventually, so that we don't keep using the same test values
		long[][] plainText = new long[10][10];
		for (int x=0; x<10;x++){
			for (int y=0;y<10;y++){
				plainText[x][y] = x+y;
			}			
		}
		
		System.out.println("Plaintext:");
		System.out.println(plainText);
		System.out.println();
		
		PlaintextEncrypter pe = new PlaintextEncrypter();
		long[][] cipherText = pe.encrypt(plainText, publicKey);
		
		System.out.println("Ciphertext:");
		System.out.println(cipherText);
		System.out.println();
		
		HomomorphicOps ho = new HomomorphicOps();
		
		long[][] addedCipherText = ho.add(cipherText, cipherText);
		
		System.out.println("Added Ciphertext:");
		System.out.println(addedCipherText);
		System.out.println();
		
		long[][] multipliedCipherText = ho.multiply(cipherText, cipherText);
		
		System.out.println("Multiplied Ciphertext:");
		System.out.println(multipliedCipherText);
		System.out.println();
		
		CiphertextDecrypter cd = new CiphertextDecrypter();
		
		long[][] decryptedCiphertext = cd.decrypt(cipherText, privateKey);
		long[][] decryptedAddedCiphertext = cd.decrypt(addedCipherText, privateKey);
		long[][] decryptedMultipliedCiphertext = cd.decrypt(multipliedCipherText, privateKey);
		
		System.out.println("Decrypted Plaintext:");
		System.out.println(decryptedCiphertext);
		System.out.println();
		
		System.out.println("Decrypted Multiplied Plaintext:");
		System.out.println(decryptedAddedCiphertext);
		System.out.println();
		
		System.out.println("Decrypted Added Plaintext:");
		System.out.println(decryptedMultipliedCiphertext);
		System.out.println();
	}

}

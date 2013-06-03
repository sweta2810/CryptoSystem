package test;

import java.math.BigInteger;

import decrypt.CiphertextDecrypter;
import encrypt.PlaintextEncrypter;
import keyGen.KeyTuple;
import keyGen.TrapdoorSampler;
import homomorphicOperations.HomomorphicOps;

/**
 * 
 * @author Aaron
 * Created to informally test the entire system before more formal tests are performed
 * This can be modularised to allow for tests of individual subsystems.
 * 
 */

public class CryptosystemTest {

	public static void main(String[] args) {
		overallTest();
	}
	
	private static void overallTest(){
		BigInteger testSecurityParameter = BigInteger.valueOf(5000);
		BigInteger testModulus = BigInteger.valueOf(7);
		
		//needs to me modified to fit trapsamp...
		KeyTuple outputTuple = TrapdoorSampler.trapSamp(testSecurityParameter, testModulus);

		BigInteger[][] privateKey = outputTuple.getPrivateKey();
		BigInteger[][] publicKey = outputTuple.getPublicKey();
		
		//Initialise plaintext matrix. Should make random eventually, so that we don't keep using the same test values
		BigInteger[][] plainText = new BigInteger[10][10];
		for (int x=0; x<10;x++){
			for (int y=0;y<10;y++){
				plainText[x][y] = BigInteger.valueOf(x+y);
			}			
		}
		
		System.out.println("Plaintext:");
		System.out.println(plainText);
		System.out.println();
		
		PlaintextEncrypter pe = new PlaintextEncrypter();
		BigInteger[][] cipherText = pe.encrypt(plainText, publicKey);
		
		System.out.println("Ciphertext:");
		System.out.println(cipherText);
		System.out.println();
		
		HomomorphicOps ho = new HomomorphicOps();
		
		BigInteger[][] addedCipherText = ho.add(cipherText, cipherText);
		
		System.out.println("Added Ciphertext:");
		System.out.println(addedCipherText);
		System.out.println();
		
		BigInteger[][] multipliedCipherText = ho.multiply(cipherText, cipherText);
		
		System.out.println("Multiplied Ciphertext:");
		System.out.println(multipliedCipherText);
		System.out.println();
		
		CiphertextDecrypter cd = new CiphertextDecrypter();
		
		BigInteger[][] decryptedCiphertext = cd.decrypt(cipherText, privateKey);
		BigInteger[][] decryptedAddedCiphertext = cd.decrypt(addedCipherText, privateKey);
		BigInteger[][] decryptedMultipliedCiphertext = cd.decrypt(multipliedCipherText, privateKey);
		
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

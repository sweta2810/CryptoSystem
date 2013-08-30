package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class tests the entire system.
 * @author Nishant 
 *
 */
public class CryptoSystemTest {

	 static double[][] privateKey;
	 static double[][] publicKey;
	 static double[][] encryptText;
	 static double[][] plainText;
	
	 @BeforeClass
	 /**
	  * This method performs all the necessary computation prior testing the system 
	  */
	 public static void testSetup() {

		 keyGen.ParamGen paramGen = new keyGen.ParamGen();
		 newKeygen.TrapdoorSampler tramp = new newKeygen.TrapdoorSampler();
		 encrypt.PlaintextEncrypter pte = new encrypt.PlaintextEncrypter();
		 keyGen.KeyTuple keys = tramp.genKeys();
		 privateKey = keys.getPrivateKey();
		 publicKey = keys.getPrivateKey();
		 plainText = new double[10][10];
			for (int x=0; x<10;x++){
				for (int y=0;y<10;y++){
					plainText[x][y] = Integer.parseInt(Integer.toBinaryString(x+y));
				}			
			}
		encryptText = pte.encrypt(plainText,publicKey);	
		
     }

     @AfterClass
     /**
      * Reseting the values to null
      */
     public static void testCleanup() {
     // Teardown for data used by the unit tests
    	plainText = null;
    	privateKey = null;
    	publicKey = null;
    	encryptText = null;
     }


	@Test
	/**
	 * Actual test of the system
	 */
	public void testSystem() {
		fail("Not yet implemented");
		assertTrue((plainText==new decrypt.CiphertextDecrypter().decrypt(encryptText,privateKey)));
	}


}

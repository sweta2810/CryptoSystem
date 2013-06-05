package keyGen;


import java.util.Random;
import java.math.BigInteger;
import java.math.BigDecimal;

/**
 *@author Nishant Rathore
 *
 *This class sets the paramater values that are used to form matrices. 
 */



public class ParamGen {


	private final BigInteger MODULUS;
	private final long SECURITY_PARAMETER;
	private final long ANY_C;
	private final long DIMENSION_M;
	private ParamGen paramGenInstance;

	private ParamGen(long securityParameter){
	   	
		SECURITY_PARAMETER = securityParameter;
		ANY_C = generateAnyC();	
		MODULUS = generateModulus();
		DIMENSION_M = generateDimentionality();
	
	}
	
	public long generateDimentionality(){
		
		//there's potential for loss of accuracy here. Watch out
		//Now it won't be. 
		double almostM = 8*SECURITY_PARAMETER*Math.log(MODULUS.doubleValue());		
		long m = (long) (almostM+1);
		
		return m;
		
	}

	public BigInteger generateModulus(){
		BigDecimal power20 = new BigDecimal((Math.pow(2,20)));
		BigDecimal logValue = new BigDecimal(Math.pow(Math.log10(SECURITY_PARAMETER),5));
		BigDecimal anyC = new BigDecimal((Math.pow(ANY_C+4,3)));
		BigDecimal qValue = (new BigDecimal(Math.pow(SECURITY_PARAMETER,(3*(ANY_C))+4)));
		BigDecimal almostQ = power20.multiply(logValue).multiply(anyC).multiply(qValue);
		BigInteger q = (almostQ.toBigInteger());				

		return q;
	} 

	public BigInteger nextPrime(BigInteger q){
		return q.nextProbablePrime();
							
	}


	public int generateAnyC(){
		
		return (new Random().nextInt(3) + 1);

	}
		
	public ParamGen getParamGenInstance(long securityParameter){

	    	if(paramGenInstance==null){
		
			paramGenInstance = new ParamGen(securityParameter);
		}	
		
		return paramGenInstance;

	}


	public static void main(String[] args){

	    ParamGen pg = new ParamGen(6);
	    System.out.println("C: "+ pg.ANY_C);
            System.out.println("Q:"+ pg.MODULUS);			

	}	

	//may need to add a method to generate beta, too...
	//Also, deal with c somehow...
	
}
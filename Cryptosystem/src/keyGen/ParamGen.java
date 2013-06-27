package keyGen;



import java.util.Random;
import java.math.BigInteger;
import java.math.BigDecimal;

/**
 *@author Nishant Rathore
 *
 *This class sets the parameter values that are used to form matrices. 
 */



public class ParamGen {


	private final double MODULUS;
	private final long SECURITY_PARAMETER;
	private final long ANY_C;
	private final long DIMENSION_M;
	private ParamGen paramGenInstance;

	public ParamGen(){
	   	
		SECURITY_PARAMETER = setSecurityParameter();
		ANY_C = generateAnyC();	
		MODULUS = generateModulus();
		DIMENSION_M = generateDimentionality();
	
	}
	
	public long setSecurityParameter() {
		
		return (70+new Random().nextLong());
	}
	
	public long getSecurityParameter() {
		
		return SECURITY_PARAMETER;
	}
	
	public long getAnyC() {
		
		return ANY_C;
	}
	
	public double getModulus() {
		
		return MODULUS;
	}
	
	public long getDimension_M() {
		
		return DIMENSION_M;
	}
	
	public long generateDimentionality(){
		
		//there's potential for loss of accuracy here. Watch out
		//Now it won't be. 
		double almostM = 8*SECURITY_PARAMETER*Math.log(MODULUS);		
		long m = (long) (almostM+1);
		
		return m;
		
	}

	public double generateModulus(){
		BigDecimal power20 = new BigDecimal((Math.pow(2,20)));
		BigDecimal logValue = new BigDecimal(Math.pow(Math.log10(SECURITY_PARAMETER),5));
		BigDecimal anyC = new BigDecimal((Math.pow(ANY_C+4,3)));
		BigDecimal qValue = (new BigDecimal(Math.pow(SECURITY_PARAMETER,(3*(ANY_C))+4)));
		BigDecimal almostQ = power20.multiply(logValue).multiply(anyC).multiply(qValue);
		BigInteger q = (almostQ.toBigInteger());				

		return q.doubleValue();
	} 

	public double nextPrime(BigInteger q){
		return q.nextProbablePrime().doubleValue();
							
	}


	public int generateAnyC(){
		
		return (new Random().nextInt(3) + 1);

	}
		
	public ParamGen getParamGenInstance(){

	    	if(paramGenInstance==null){
		
			paramGenInstance = new ParamGen();
		}	
		
		return paramGenInstance;

	}


	public static void main(String[] args){

	    ParamGen pg = new ParamGen();
	    System.out.println("C: "+ pg.ANY_C);
            System.out.println("Q:"+ pg.MODULUS);
            System.out.println("Q:"+ pg.DIMENSION_M);

	}	

	//may need to add a method to generate beta, too...
	//Also, deal with c somehow...
	
}
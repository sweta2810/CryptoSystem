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
	private final int SECURITY_PARAMETER;
	private final long ANY_C;
	private final long DIMENSION_M;
	private ParamGen paramGenInstance;
	private final double BETA;
	
	public ParamGen(){
	   	
		SECURITY_PARAMETER = setSecurityParameter();
		ANY_C = generateAnyC();	
		MODULUS = generateModulus();
		DIMENSION_M = generateDimentionality();
		BETA = generateBeta();
	}
	
	/**
	 * This method returns the security parameter. 
	 * @return 	the security parameter n
	 */
	public int setSecurityParameter() {
		
		return 10+(Math.abs(new Random().nextInt(10)));
	}
	
	/**
	 * This method returns the security parameter. 
	 * @return 	the security parameter n
	 */
	
	public int getSecurityParameter() {
		
		return SECURITY_PARAMETER;
	}
	
	/**
	 * This method returns the parameter C. 
	 * @return 	the parameter C
	 */
	
	public long getAnyC() {
		
		return ANY_C;
	}
	
	/**
	 * This method returns the modulus Q. 
	 * @return 	the modulus
	 */
	
	public double getModulus() {
		
		return MODULUS;
	}
	
	/**
	 * This method returns the parameter Beta. 
	 * @return 	the parameter Beta
	 */
	
	public double getBeta(){
		
		return BETA;
	}
	
	/**
	 * This method returns the dimension M. 
	 * @return 	the dimension M.
	 */
	
	public long getDimension_M() {
		
		return DIMENSION_M;
	}
	
	/**
	 * This method sets the dimension M. 
	 * @return 	the dimension M
	 */
	
	public long generateDimentionality(){
		
	
		double almostM = 8*SECURITY_PARAMETER*Math.log10(MODULUS);		
		long m = (long) (almostM+1);
		
		return Math.abs(m);
		
	}

	/**
	 * This method sets the Modulus. 
	 * @return 	the modulus Q.
	 */
	
	public double generateModulus(){
	
		BigDecimal power20 = new BigDecimal((Math.pow(2,20)));
	
		BigDecimal logValue = new BigDecimal(Math.pow(Math.log10(SECURITY_PARAMETER),5));
		
		BigDecimal anyC = new BigDecimal((Math.pow(ANY_C+4,3)));
		BigDecimal qValue = (new BigDecimal(Math.pow(SECURITY_PARAMETER,(3*(ANY_C))+4)));
		BigDecimal almostQ = power20.multiply(logValue).multiply(anyC).multiply(qValue);
		BigInteger q = (almostQ.toBigInteger());				
		BigInteger q1=nextPrime(q);
		return (q1.intValue());
	} 

	/**
	 * This method returns the next prime number after the given number. 
	 * @return 	the prime number
	 */
	
	public BigInteger nextPrime(BigInteger q){
		return q.nextProbablePrime();
							
	}

	/**
	 * This method generates the parameter C. 
	 * @return 	the parameter C
	 */
	
	public int generateAnyC(){

		return (new Random().nextInt(3) + 1);

	}
		
	/**
	 * This method makes sure only one instance of this class is created. 
	 * @return 	the instance of ParamGen
	 */
	
	public ParamGen getParamGenInstance(){

	    	if(paramGenInstance==null){
		
			paramGenInstance = new ParamGen();
		}	
		
		return paramGenInstance;

	}

	/**
	 * This method sets the value of Beta. 
	 * @return 	the Beta value.
	 */
	
	public double generateBeta(){
	
		double beta = (27*Math.pow(getSecurityParameter(), 1+(1.5*getAnyC()))
				*Math.log10(getSecurityParameter())*Math.log10(getModulus())*Math.sqrt(getModulus()*getDimension_M()));
		
		return (1/beta)+1;
	}
	
}
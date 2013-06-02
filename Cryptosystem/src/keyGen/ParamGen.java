

import java.util.Random;


public class ParamGen {


	private final long MODULUS;
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
		double almostM = 8*SECURITY_PARAMETER*Math.log(MODULUS);		
		long m = (long) (almostM+1);
		
		return m;
		
	}

	public long generateModulus(){

		double almostQ = (Math.pow(2,20)*Math.pow(Math.log10(SECURITY_PARAMETER),5));
		System.out.println("AlmostQ1: "+almostQ);
			almostQ	=  (long) (almostQ*(Math.pow(ANY_C+4,3)));
				System.out.println("AlmostQ2: "+almostQ);
			almostQ = (long)(almostQ*(Math.pow(SECURITY_PARAMETER,(3*(ANY_C))+4)));
			System.out.println("AlmostQ3: "+almostQ);
		long q = (long)Math.floor(almostQ);
				System.out.println("q: "+new Double(almostQ).longValue());
		System.out.println("q is Prime: "+isPrime(q));
		while(true){
		    if(isPrime(q)){
			break;
		    }
		    else{
			q=q+1;
		    }			
		}
		return q;
	} 

	public boolean isPrime(long q){
		int i=2;
		while(i<q){
		
		    if(q%i==0){	
			
			return false;
		    }		
		    i++;    	
		}	
		return true;					
	}


	public long generateAnyC(){
		
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

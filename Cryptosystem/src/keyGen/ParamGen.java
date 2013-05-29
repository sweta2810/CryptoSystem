package keyGen;

public class ParamGen {
	
	public static long generateDimentionality(long modulus, long securityParameter){
		
		//there's potential for loss of accuracy here. Watch out.
		long almostm = (long) (8*securityParameter*Math.log(modulus));		
		long m = (long) Math.floor(almostm);
		
		return m;
		
	}

	//may need to add a method to generate beta, too...
	//Also, deal with c somehow...
	
}

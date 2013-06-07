package keyGen;
import org.jblas.DoubleMatrix;
import java.util.Random;

/**
 * This class implements the trapdoor sampling algorithm and generates two Key matrices.
 * @author Nishant Rathore
 *
 */

public class TrapdoorSampler {

	
	
	
	public TrapdoorSampler(){
		
		
		
	}
	
	
	/**
	 *This method generates the key matrix A
	 *
	 */
	public DoubleMatrix generateRandom_A(){
		ParamGen paramGen = new ParamGen();
		Random rand = new Random();
		Long dimensionMLong = new Long(paramGen.getDimension_M());
		Long securityParameterLong = new Long(paramGen.getSecurityParameter());
		int dimensionN = securityParameterLong.intValue();
		int dimensionM = dimensionMLong.intValue();
		double[][] matrixArray = new double[dimensionM][dimensionN];
		for(int i=0;i<dimensionN;i++){
			for(int j=0;j<dimensionM;j++){
				matrixArray[i][j]= rand.nextInt(paramGen.generateModulus().intValue());
			}
		}
		DoubleMatrix matrixA = new DoubleMatrix(matrixArray);
		
		return matrixA;
	}
	
	public DoubleMatrix generateMatrixT(){
		
		
		
		return null;
	}
	
}

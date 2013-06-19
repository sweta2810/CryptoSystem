package keyGen;
import jama.Matrix;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements the trapdoor sampling algorithm and generates two Key matrices.
 * @author Nishant Rathore
 *
 */

public class TrapdoorSampler {

	double[][] matrix;
	ParamGen paramGen = new ParamGen();
	Matrix matrixA;
	Matrix matrixT;

	public TrapdoorSampler(){
		
		matrixA = generateRandomA();
		matrixT = generateMatrixT();
	}
	
	
	/**
	 *This method generates the key matrix A
	 *
	 */
	public Matrix generateRandomA(){
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
		Matrix matrixA = new Matrix(matrixArray);
		
		return matrixA;
	}
	
	public Matrix generateMatrixT(){
		
		Long dimensionMLong = new Long(paramGen.getDimension_M());
		Long securityParameterLong = new Long(paramGen.getSecurityParameter());
		int dimensionM = dimensionMLong.intValue();
		
		Matrix matrixT = new Matrix(matrix);
		
		return null;
	}
	
	public void createMatrix(int dimension){
		double[][] matrixArray = new double[dimension][dimension];
		Random rand = new Random();
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				matrixArray[i][j]= rand.nextInt();
			}
		}
		Matrix testM = new Matrix(matrixArray);
		if(testM.det()==0&&!testMatrix(matrixArray,dimension)){
			createMatrix(dimension);
		}
		
		matrix = matrixArray;
		
	}
	//Haven't tested it yet
	public boolean testMatrix(double[][] matrixArray, int dimension){
		Random rand = new Random();
		boolean b = false;
		double[][] randomMatrix = matrixArray;
		double[][] columnVector = new double[dimension][1]; 
		for(int i=0;i<dimension;i++){
			for(int j=0;j<1;j++){
				columnVector[i][j]= rand.nextInt(paramGen.generateModulus().intValue());
			}
		}
		Matrix columnMatrix = new Matrix(columnVector);
		Matrix randomT = new Matrix(randomMatrix);
		Matrix matrixMult = columnMatrix.arrayTimes(randomT.transpose());
		
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				b = matrixMult.get(i, j)==matrixA.get(i, j);
			}
			if(b){
				return true;
			}
		}
		return false;
		
	}
}

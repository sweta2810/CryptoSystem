package newKeygen;
import java.util.ArrayList;

import java.util.Random;

import keyGen.KeyTuple;
import keyGen.ParamGen;

import Jama.Matrix;

/**
* All matrix generation methods are as per Joël Alwen and Chris Peikert 2010.
* Generating Shorter Bases for Hard Random Lattices,
* Theory of Computer Systems (2011) 48:535-553
* The letters used to name matrices correspond to the same-named matrices in that paper.
*
* @author Aaron and Nishant 
*
*/
public class TrapdoorSampler {

	private static int m1;
	private static int l;
	private static int r;
	private static int m2;
	private static int m;
	private double[][] A1;
	private static int n;
	private static double qmod;
	private double[][] arrayH;
	
	/**
	 * This method generate the public and private keys and returns the keyTuple object
	 * 
	 * @return 		keyTuple object that stores public and private keys 
	 **/
	
    public KeyTuple genKeys(){
    	setm1();
    	setl();
    	setr();
    	setm2();
    	setm();
    	ParamGen p = new ParamGen();
    	n = p.getSecurityParameter();
    	qmod = p.getModulus();
    	
    	double[][] U = genU();
    	A1 = genA1();
    	double[][] G = genG();
    	double[][] R = genR();
    	double[][] C = genC();
    	double[][] P = genP();
    
    	double[][] A2 = genA2(A1, R, G);
    	double[][] A = genA(A1, A2);
    	double[][] S = genS(U,G,P,R,C);
    	
    	KeyTuple kt = new KeyTuple(A, S);
                      
    	return kt;
                       
    }
    /**
     * This method creates matrix U 
     * @return 	        Matrix U 
     */
    private double[][] genU(){
    	//generate T (using method below)
    	//U is m2xm2 (once complete)
    	//U is a little hard to describe in other words than the paper. 
    	//The diagonal is T repeated, corner-to-corner again and again for the diagonal length, 
    	//and all of the other entries are set to zero.
    	//concatenate the m2-m1*l identity matrix to the end of U, to form the final U
    	//return U
    	
    	int sizel = l;
    	int size = m2;
    	double[][] tempArray= new double[size][size];
    	double[][] arrayT = genT();
    	for(int temp=0;temp<size;temp++){
    		for(int temp2=0;temp2<size;temp2++){
    			tempArray[temp][temp2]=0;
    		}
    	}
  
    	
    
    	for(int s=(m2-(m1*l));s<size;s++){
    		for(int r=(m2-(m1*l));r<size;r++){
    			if(r==s){
    				tempArray[r][s]=1;
    			}
    		}
    	}
    	
    	for(int k=0;k<(m2-(m1*l));k=k+sizel){
    		for(int j=0;j<sizel;j=j+1){
    			for(int i=0;i<sizel;i=i+1){
    				tempArray[k+j][k+i]=arrayT[j][i];
    			}
    		}
 
    	}
  
    	return tempArray;
    }
                   
    /**
     * This method creates matrix T that is used by Matrix U  
     * @return 	        Matrix T 
     */
    private double[][] genT(){
    	int size = l;
    
    	double[][] tempArray = new double[size][size];
    	for(int i=0;i<size;i++){
    		for(int j=0;j<size;j++){
    			if(i==j){
    				tempArray[i][j]=1;
    			}
    			else if(i>j){
    				tempArray[i][j]=-r;
    			}
    			else{
    				tempArray[i][j]=0;
    			}
    		}
    	}
  
    	return tempArray;
    }
    
    /**
     * This method creates matrix G 
     * @return 	        Matrix G 
     */
    
    private double[][] genG(){
    	
    	arrayH = getHermiteNormalForm(A1);
    	double[][] arrayHdash = new double[m1][m1];
    	for(int i=0;i<m1;i++){
    		for(int j=0;j<arrayH[i].length;j++){
    			if(i==j){
    				arrayHdash[i][j]=arrayH[i][j]-1;
    			}
    			else{
    				arrayHdash[i][j]=arrayH[i][j];
    			}
    		}
    	}
    	double[][] arrayG = new double[m1][m2];
    	for(int j=0;j<m1;j++){
    		for(int i=0;i<m1;i++){
    			for(int s=0;s<l;s++){
    				arrayG[j][i]=arrayH[j][i]/(Math.pow(r, l-s));
    			}
        	}
    		if(m2-(m1*l)>0){
    			for(int i=m1; i<m2; i++){
    				arrayG[j][i]=0;
    			}
    		}
    	}
    	return arrayG;
    }
    
    /**
     * This method creates matrix P 
     * @return 	        Matrix P 
     */
    
    private double[][] genP(){
    	
    	double[][] arrayHdash = new double[m1][m1];
    	for(int i=0;i<m1;i++){
    		for(int j=0;j<arrayH[i].length;j++){
    			if(i==j){
    				arrayHdash[i][j]=arrayH[i][j]-1;
    			}
    			else{
    				arrayHdash[i][j]=arrayH[i][j];
    			}
    		}
    	}
    	Matrix g = new Matrix(genG());
    	Matrix p = new Matrix(arrayHdash);
    	
    	return p.getArray();
    }
    
    /**
     * This method creates matrix R 
     * @return 	        Matrix R 
     */
    
    private double[][] genR(){
    	
    	int d = m1-1;
    	double[][] arrayR = new double[m1][m2];
    	for(int i=0;i<d;i++){
    		for(int j=0;j<d/2;j++){
    			arrayR[i][j]=0;
    		}
    		for(int j=(d/2)+1;j<(0.75*d);j++){
    			arrayR[i][j]=1;
    		}
    		for(int j=(int)(0.75*d)+1;j<d;j++){
    			arrayR[i][j]=-1;
    		}
    		for(int j=d;j<d+1;j++){
    			arrayR[i][j]=0;
    		}
    	}
    	return arrayR;
    }
    
    /**
     * This method creates matrix C 
     * @return 	        Matrix C 
     */
    
    private double[][] genC(){
    	
    	double[][] tempArray = new double[m1][m1];
    	for(int i=0;i<m1;i++){
    		for(int j=0;j<m1;j++){
    			if(i==j){
    				tempArray[i][j]=1;
    			}
    			else{
    				tempArray[i][j]=0;
    			}
    		}
    	}
    	return tempArray;
    }
    
    /**
     * This method creates matrix A1 
     * @return 	        Matrix A1 
     */
    
    private double[][] genA1(){
    	
    	int dimM = n;
    	int dimN = m1;
    	double[][] tempArray = new double[dimM][dimN];
    	for(int i=0;i<dimM;i++){
    		for(int j=0;j<dimN;j++){
    			tempArray[i][j]=new Random().nextInt();
    		}
    	}
    	return tempArray;
    }
    
    /**
     * This method creates matrix A2
     * 
     * @param			Matrix A1, Matrix R and Matrix G 
     * @return 	        Matrix A2 
     */
    
    private double[][] genA2(double[][] A1, double[][] R, double[][] G){
    
    	Matrix a1 = new Matrix(A1);
    	Matrix r = new Matrix(R);
    	Matrix g = new Matrix(G);
    	a1=a1.times(-1);
    	Matrix a2 = r.plus(g).times(a1);
    	return a2.getArray();
    }
    
    /**
     * This method creates matrix A 
     * @param 	        Matrix A1 and Matrix A2
     * @return			Matrix A 
     */
    
    private double[][] genA(double[][] A1, double[][] A2){
    
    	int a1ColumnLength = A1[0].length;
    	int a2ColumnLength = A2[0].length;
    	int newLength = a1ColumnLength + a2ColumnLength;
    	double[][] arrayA = new double[A1.length][newLength]; 
    	for(int i=0;i<A1.length;i++){
    		for(int j=0;j<A1[i].length;j++){
    			arrayA[i][j]=A1[i][j];
    		}
    		for(int j=A1[i].length;j<newLength;j++){
    			arrayA[i][j]=A2[i][j-A1[i].length];
    		}
    	}
    	return arrayA;
    }
    
    /**
     * This method creates matrix S
     * 
     * @param 			Matrix U, Matrix G, Matrix P, Matrix R and Matrix C
     * @return 	        Matrix S 
     */
    
    private double[][] genS(double[][] U,double[][] G,double[][] P,double[][] R,double[][] C){
    	
    	Matrix g = new Matrix(G);
    	Matrix u = new Matrix(U);
    	Matrix p = new Matrix(P);
    	Matrix r = new Matrix(R);
    	Matrix c = new Matrix(C);
    	Matrix t1 = (g.plus(r)).times(u);
    	Matrix t2 = (g.times(p)).minus(c);
    	double[][] arrayS = new double[m][m];
    	for(int i=0;i<t1.getArray().length;i++){
    		for(int j=0;j<t1.getArray()[i].length;j++){
    			arrayS[i][j] = t1.getArray()[i][j];
    		}
    		for(int j=t1.getArray()[i].length;j<m;j++){
    			arrayS[i][j] = t2.getArray()[i][j-t1.getArray()[i].length];
    		}
    	}
    	for(int i=t1.getArray().length;i<m;i++){
    		for(int j=0;j<p.getArray()[i-t1.getArray().length].length;j++){
    			arrayS[i][j]=p.getArray()[i-t1.getArray().length][j];
    		}
    		for(int j=p.getArray()[i-t1.getArray().length].length;j<m;j++){
    			arrayS[i][j]=u.getArray()[i-t1.getArray().length][j-p.getArray()[i-t1.getArray().length].length];
    		}
    	}
    	return arrayS;
    }
    
    /**
     * This method computes the Hermite normal form of the given matrix and return it.
     * 
     * @param 			A1
     * @return 			Hermite Normal form of Matrix A1
     */
    private double[][] getHermiteNormalForm(double[][] A1){
    	int k = 0;
    	
    	double[][] matrixA = A1;
    	
    	for(int j=0;j<m1;j++){
    		int pivot = getPivot(k,A1);
    		if(pivot>k){
    			double[][] temp = new double[1][m1];
    			for(int i=0;i<m1;i++){
    				temp[0][i]=matrixA[k][i];
    				matrixA[k][i]=matrixA[pivot][i];
    				matrixA[pivot][i]=temp[0][i];
    			}
    			if(matrixA[k][j]<0){
    				for(int i=0;i<m1;i++){
    					matrixA[k][i]=-matrixA[k][i];
    				}
    			}
    		}
    
    		if(k<n-2){
    		double b = matrixA[k][j];
    		for(int i=k;i<n;i++){
    			int q = (int)Math.round((float)(matrixA[i][j]/b));
    			for(int s=0;s<m1;s++){
    				matrixA[i][s]=matrixA[i][s]-q*(matrixA[k][s]);
    			}
    		}
    		k=k+1;
    		}
    	}
    	
    	k=0;
    	for(int j=0;j<m1;j++){
    		if(matrixA[k][j]<0){
    			matrixA[k][j]=-matrixA[k][j];	
    		}
    		double b = matrixA[k][j];
    		if(b==0.0){
    			continue;
    		}
    		else{
    			for(int i=0;i<k;i++){
    				int q = (int)Math.round(matrixA[i][j]/b);
    				for(int s=0;s<m1;s++){
    					matrixA[i][s]=matrixA[i][s]-q*(matrixA[k][s]);
    				}
    			}
    		}
    		k=k+1;
    	}
    	
    	return matrixA;
    	
    }
    
    /**
     * This method returns the pivot element in the matrix array. 
     * It is used to compute the Hermite Normal Form of the matrix 
     * @param column
     * @param array
     * @return	pivot element of the given column in an array
     */
    
    private int getPivot(int column, double[][] array){
    
    	ArrayList<Double> list = new ArrayList<Double>();
    	for(int i=0;i<array.length;i++){
    		list.add(Math.abs(array[i][column]));
    		}
    	double smallest = Double.MAX_VALUE;
    	for(Double num:list){
    		smallest = Math.min(smallest, num);
    	}
    	int index=0;
    	for(int i=0;i<array.length;i++){
    		if(array[i][column]==smallest||array[i][column]==(-smallest)){
    			index=i;
    		}
    		}
    	
    	return index;
    }
    
    /**
     * It sets the value of variable l
     */
    private void setl(){
    	
    	l= (int) Math.round(Math.ceil( Math.log(qmod)/Math.log(r)))+1;
    
    	
    }
    
    /**
     * It sets the value of variable r
     */
    
    private void setr(){
    	//this is inefficient. See page 9 of paper for a more efficient construction.
    	 r = Math.abs(new Random().nextInt())+2;
    
    }
    
    /**
     * It sets the value of variable m1
     */
    
    private static void setm1(){
    
    
    	m1 = 10+(int) Math.round(Math.abs(((1+new Random().nextInt(2))*n*Math.log10(new ParamGen().getModulus()))));
    
    }
    
    /**
     * It sets the value of variable m2
     */
    private void setm2(){
    
    	m2 = Math.abs((m1*l))+1;
    
    }
    
    /**
     * It sets the value of variable m
     */
    
    private void setm(){
    	m=m1+m2;
    
    }
    
}
	


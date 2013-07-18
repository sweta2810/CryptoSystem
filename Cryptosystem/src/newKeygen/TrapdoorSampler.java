package newKeygen;
import java.util.ArrayList;

import java.util.Random;

import keyGen.KeyTuple;
import keyGen.ParamGen;

import jama.Matrix;

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

	static int m1;
	static int l;
	static int r;
	static int m2;
	static int m;
    public KeyTuple genKeys(){
    	setm1();
    	setl();
    	setr();
    	setm2();
    	setm();
    	double[][] U = genU();
    	double[][] G = genG();
    	double[][] P = genP();
    	double[][] R = genR();
    	double[][] C = genC();
    	double[][] A1 = genA1();
    	double[][] A2 = genA2(A1, R, G);
    	double[][] A = genA(A1, A2);
    	double[][] S = genS(U,G,P,R,C);
    	
    	KeyTuple kt = new KeyTuple(A, S);
                      
    	return kt;
                       
    }
    
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
    	for(int k=0;k<(m2-(m1*l));k=k+sizel){
    		for(int j=0;j<sizel;j=j+1){
    			for(int i=0;i<sizel;i=i+1){
    				tempArray[k+j][k+i]=arrayT[j][i];
    			}
    		}
    	}
    	for(int s=(m2-(m1*l));s<size;s++){
    		for(int r=(m2-(m1*l));r<size;r++){
    			if(r==s){
    				tempArray[r][s]=1;
    			}
    		}
    	}
    	return null;
    }
                   
    //T is used in the genU method:
    private double[][] genT(){
    	//t is lxl size
    	//all diagonal entries are equal to 1
    	//upper diagonal entries are equal to -r
    	//all other entries are 0
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
    private double[][] genG(){
    	//get the hermite normal from of A1, call it H
    	//create component matrices 1-m1 as follows:
    	//for each i of m1 matrix blocks with l columns each
    	//the column of G^i = the column of H^i/r^(l-j), with the floor function applied to each element.
    	//where j is the column of the current matrix block.
    	//we concatenate all of these from left to right, and then concatenate a block with all zero entries to the end (if needed) to make the width m2-m1*l
    	double[][] arrayH = getHermiteNormalForm(genA1());
    	double[][] arrayG = new double[m1][m2];
    	for(int j=0;j<m1;j++){
    		for(int i=0;i<m2;i++){
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
    private double[][] genP(){
    	// for each j from 1 to m1, the jth column of P is equal to ... what is vector e? 
    	//I'm not sure excatly what jlth means, but it's the jlth standard basis vector.
    	return null;
    }
    private double[][] genR(){
    	//let d = (1+delta+*n*lg(g))
    	//the first d rows of r are have random entries with the following probabilities:
    	//0 with a probability of 1/2
    	//1 with probability of 1/4
    	//-1 with a probability of 1/4
    	//all of the rest of the entries (below d rows, I guess) are 0
    	//return R
    	//I'm not sure about the size of R...
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
    private double[][] genC(){
    	//This is probably optional. Let's look at it last. 
    	//Should be fine to set it to the identity? (the m1xm1 identity)
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
    private double[][] genA1(){
    	//generate a uniformly random nxm1 matrix   
    	//return it
    	int dimM = new Long (new ParamGen().getSecurityParameter()).intValue();
    	int dimN = m1;
    	double[][] tempArray = new double[dimM][dimN];
    	for(int i=0;i<dimM;i++){
    		for(int j=0;j<dimN;j++){
    			tempArray[dimN][dimM]=new Random().nextInt();
    		}
    	}
    	return tempArray;
    }
    private double[][] genA2(double[][] A1, double[][] R, double[][] G){
    	//A2 = -A1*(G+R)
    	//return A2
    	Matrix a1 = new Matrix(A1);
    	Matrix r = new Matrix(R);
    	Matrix g = new Matrix(G);
    	a1=a1.times(-1);
    	Matrix a2 = r.plus(g).times(a1);
    	return a2.getArray();
    }
    private double[][] genA(double[][] A1, double[][] A2){
    	//Concatenate the columns of A2 to the right of A1
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
    private double[][] genS(double[][] U,double[][] G,double[][] P,double[][] R,double[][] C){
    	//Calculate T1 = (G + R)U
    	//Calculate T2 = RP - C
    	//T3 = the rows of P concatenated to the right of the rows of U
    	//T4 = the rows of T2 concatenated to the right of T1
    	//T5 = The columns of T3 concatenated to the bottom of T4
    	//return T5
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
    
    private double[][] getHermiteNormalForm(double[][] A1){
    	int k = 0;
    	int n = (int)new ParamGen().getSecurityParameter();
    	double[][] matrixA = new double[(int)new ParamGen().getSecurityParameter()][m1];
    	matrixA=A1;
    	for(int j=0;j<n;j++){
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
    		double b = matrixA[k][j];
    		for(int i=k+1;i<n;i++){
    			int q = (int)Math.round((float)matrixA[i][j]/b);
    			for(int s=0;s<m1;s++){
    				matrixA[i][s]=matrixA[i][s]-q*(matrixA[k][s]);
    			}
    		}
    		k=k+1;
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
    
  
    private void setl(){
    	
    	//TODO it's log base r of q
    	l= (int) Math.ceil( Math.log(new ParamGen().getModulus())/Math.log(r));
    	
    }
    private void setr(){
    	//this is inefficient. See page 9 of paper for a more efficient construction.
    	 r = new Random().nextInt()+2;
    	
    }
    
    private static void setm1(){
    	//this is an example, I think m1 can be whatever we want as long as it's large enough
    	m1 = (int)(2+new Random().nextInt()*new ParamGen().getSecurityParameter()*Math.log(new ParamGen().getModulus()));
    	
    }
    private void setm2(){
    	//this is an example, I think m2 can be whatever we want as long as it's large enough
    	m2 = (m1*l)+new Random().nextInt(3)+1;
    	
    }
    private void setm(){
    	m=m1+m2;
    	
    }
    
}
	


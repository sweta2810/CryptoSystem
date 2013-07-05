package adapters;



import jama.Matrix;


public class JAMAAdapter implements MatrixAdapter{

	public double[][] add(double[][] m1, double[][] m2) {
		Matrix M1 = new Matrix(m1);
		Matrix M2 = new Matrix(m2);
		Matrix M3 = M1.plus(M2);
		double[][] m3 = M3.getArray();
		
		return m3;
	}

	
	public double[][] multiply(double[][] m1, double[][] m2) {
		Matrix M1 = new Matrix(m1);
		Matrix M2 = new Matrix(m2);
		Matrix M3 = M1.times(M2);
		double[][] m3 = M3.getArray();
		
		return m3;
	}

	
	public double[][] multiply(double[][] m, double b) {
		Matrix M1 = new Matrix(m);
		Matrix M2 = M1.times(b);
		double[][] m2 = M2.getArray();
		
		return m2;
	}

	
	public double[][] transpose(double[][] m) {
		Matrix M = new Matrix(m);
		Matrix M2 = M.transpose();
		double[][] m2 = M2.getArray();
		return m2;
	}

	
	public double[][] inverse(double[][] m) {
		Matrix M = new Matrix(m);
		Matrix M2 = M.inverse();
		double[][] m2 = M2.getArray();
		return m2;
	}

	
	public double[][] mod(double[][] m, double mod) {

		double[][] m2 = new double[m.length][m[0].length];
		for (int x=0;x<m.length;x++){
			for(int y=0;y<m[0].length;y++){
				m2[x][y] = m[x][y]%mod;
			}
		}
		return m2;
		
	}

}

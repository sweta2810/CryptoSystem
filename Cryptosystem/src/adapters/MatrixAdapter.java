package adapters;

public interface MatrixAdapter {
	public long[][] add(long[][] m1, long[][] m2);
	public long[][] multiply(long[][] m1, long[][] m2);
	public long[][] transpose(long[][] m);
}

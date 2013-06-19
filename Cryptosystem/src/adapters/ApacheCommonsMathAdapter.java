package adapters;

import java.math.BigInteger;

import keyGen.ParamGen;

import org.apache.commons.math3.distribution.NormalDistribution;

public class ApacheCommonsMathAdapter implements StatisticsAdapter {

	NormalDistribution nd = null;
	@Override
	public double sample(){
		if (nd == null){
			nd = new NormalDistribution();
		}
		
		Double notQuiteSample = nd.sample();
		
		
		ParamGen pGen = new ParamGen();
		double q = pGen.getModulus();
		double sample = Math.round(notQuiteSample*q);
		
		
		return sample;
	}

	@Override
	public double[][] generateErrorMatrix() {
		//potential issue with casting here
		ParamGen pGen = new ParamGen();
		double dimentionality = pGen.generateDimentionality();
		double[][] errorMatrix = new double[(int) dimentionality][(int) dimentionality];
		for (int x=0;x<(int)dimentionality;x++){
			for (int y=0;y<(int)dimentionality;y++){
				errorMatrix[x][y] = this.sample();
			}
		}
		return errorMatrix;
	}

}

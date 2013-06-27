package adapters;

import java.math.BigInteger;

import keyGen.ParamGen;
import keyGen.TrapdoorSampler;

import apache.commons.math3.distribution.NormalDistribution;

import jama.Matrix;

/**
 * 
 * @author Aaron Curtis and Nishant Rathore
 *
 */

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
	public Matrix generateErrorMatrix() {
		//potential issue with casting here
		ParamGen pGen = new ParamGen();
		double dimentionality = pGen.generateDimentionality();
		double[][] errorMatrix = new double[(int) dimentionality][(int) dimentionality];
		for (int x=0;x<(int)dimentionality;x++){
			for (int y=0;y<(int)dimentionality;y++){
				errorMatrix[x][y] = this.sample();
			}
		}
		return new Matrix(errorMatrix);
	}
	
	public Matrix generateRandomMatrix(){
		
		TrapdoorSampler trap = new TrapdoorSampler();
		
		return trap.generateRandomA();
	}
	

}

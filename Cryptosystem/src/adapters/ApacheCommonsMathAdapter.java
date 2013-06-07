package adapters;

import java.math.BigInteger;

import keyGen.ParamGen;

import org.apache.commons.math3.distribution.NormalDistribution;

public class ApacheCommonsMathAdapter implements StatisticsAdapter {

	NormalDistribution nd = null;
	@Override
	public BigInteger sample(){
		if (nd == null){
			nd = new NormalDistribution();
		}
		
		Double notQuiteSample = nd.sample();
		
		BigInteger sample = //multiply the modulus q by notQuiteSample and round...
		
		
		return sample;
	}

	@Override
	public BigInteger[][] generateErrorMatrix() {
		//potential issue with casting here
		long dimentionality = ParamGen.generateDimentionality();
		BigInteger[][] errorMatrix = new BigInteger[(int) dimentionality][(int) dimentionality];
		for (int x=0;x<dimentionality;x++){
			for (int y=0;y<dimentionality;y++){
				errorMatrix[x][y] = this.sample();
			}
		}
		return errorMatrix;
	}

}

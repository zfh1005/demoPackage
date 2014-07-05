package functionDemo;

/**
 * @author ZFH
 *
 */

import java.math.BigInteger;
import java.util.Arrays;

public class MathFunctionDemo {
	public int OneDimensionalArrayDemo(int numberLength, int resultLength){
		int iResult = 0;
		int[] number = new int[numberLength];
		for(int i:number){
			number[i] = i + 1;
		}
		int[] result = new int[resultLength];
		for(int i:result){
			int random = (int)Math.random() * numberLength;
			result[i] = number[random];
			number[random] = number[numberLength - 1];
			random --;
		}
		Arrays.sort(result);
		return iResult;
	}
	
	
	public int TwoDimensionalArrayDemo(){
		int iResult = 0;
		final int iStartRate = 10;
		final int iRates = 6;
		final int iYears = 10;
		
		//set interest rates to 10% ... 15%
		double[] interestRate = new double[iStartRate];
		for(int j = 0; j < interestRate.length; j++){
			interestRate[j] = (iStartRate + j) / 100.0;			
		}
		
		double[][] balances = new double[iYears][iRates];
		
		//set interest balance to 10000
		for(int j = 0; j < balances[0].length; j++){
			balances[0][j] = 10000;
		}
		
		//compute interest for future years
		for(int i = 0; i < balances.length; i++){
			for(int j = 0; j < balances[i].length; j++){
				//get last year's balances from previous row 
				double oldBalance = balances[i-1][j];
				
				//computer interest
				double interest = oldBalance * interestRate[j];
				
				//computer this year's balances
				balances[i][j] = oldBalance + interest;
			}
		}
		
		//print one row of interest rates
		for(int j = 0; j < interestRate.length; j++ ){
			System.out.printf("%9.0f%%", 100 * interestRate[j]);
		}
		System.out.println();
		
		//print balances table
		for(double[] row : balances){
			//print table row
			for(double b : row){
				System.out.printf("%10.2f", b);
			}
			System.out.println();
		}		
		return iResult;
		
	}
	
	public BigInteger ConvertStringToBigInteger(String toConvertString){
		//length
		final int iConvertStringLength = toConvertString.length();
		if(toConvertString.trim().length() != iConvertStringLength){
			//show error information
			System.out.println("MAC length error!");
		}
		char[] cConvertString = toConvertString.toUpperCase().toCharArray();
		int[] iConvertString = new int[iConvertStringLength];
		//将每一位上面的字符转换为10进制数字
		for(int i = 0; i < iConvertStringLength; i++){
			if((cConvertString[i] >= '0') &&(cConvertString[i] <= '9')){
				iConvertString[i] = cConvertString[i] - '0';				
			}
			if((cConvertString[i] >= 'A') &&(cConvertString[i] <= 'Z')){
				iConvertString[i] = cConvertString[i] - 'A' + 10;
			}
		}		
		
		BigInteger bigResult = BigInteger.valueOf(0);			
		final BigInteger bigTempDex = BigInteger.valueOf(10);
		
		for(int i = 0; i < iConvertStringLength; i++){
			//每一位的10数值
			BigInteger bigTempMAC = BigInteger.valueOf(iConvertString[i]);
			
			//获取10的(11 - i)次幂							
			BigInteger bigTempPow = bigTempDex.pow(iConvertStringLength -1 - i);
			
			//获取每一位上的值实际对应的10进制值
			BigInteger bigTempSum = bigTempMAC.multiply(bigTempPow);
			
			//将每一位的值与之前的结果相加
			bigResult = bigResult.add(bigTempSum);
		}
		return bigResult;
	}

	public static void main(String[] args) {
		MathFunctionDemo javaMathDemo = new MathFunctionDemo();
		BigInteger bigResult = javaMathDemo.ConvertStringToBigInteger("FFFFFFffff");
		bigResult.floatValue();
		System.out.println(bigResult);
		
	}
}

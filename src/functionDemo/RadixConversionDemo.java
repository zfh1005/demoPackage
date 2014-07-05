package functionDemo;

public class RadixConversionDemo {
	private long iConversionResult = 0;
	private String sConversionResult = null;
	
	/*
	 * 10进制转换为2/8/16进制
	 * */
	public long DecimalToRadixLong(String sDecimal, int iRadix){
		iConversionResult = Long.parseLong(sDecimal, iRadix);
		return iConversionResult;		
	}
	public String DecimalToRadixString(String sDecimal, int iRadix){
		if(iRadix == 2){
			sConversionResult = Long.toBinaryString(Long.parseLong(sDecimal));
		}
		else if(iRadix == 8){
			sConversionResult = Long.toOctalString(Long.parseLong(sDecimal));
		}
		else if(iRadix == 16){
			sConversionResult = Long.toHexString(Long.parseLong(sDecimal));
		}
		else{
			
		}
		
		return sConversionResult;		
	}	
	
	
	/*
	 * 2进制转换为2/8/16的进制
	 * */
	public long BinaryToRadixLong(String sBinary, int iRadix){		
		iConversionResult = Long.valueOf(sBinary, iRadix);
		return iConversionResult;
	}
	public String BinaryToRadixString(String sBinary, int iRadix){		
		sConversionResult = Long.toString(Long.parseLong(sBinary, iRadix));
		
		return sConversionResult;
	}
	

	public static void main(String[] args) {
		RadixConversionDemo rcd = new RadixConversionDemo();
		rcd.BinaryToRadixString("01010101010101", 15);
	}

}

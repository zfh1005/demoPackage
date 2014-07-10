package functionDemo;


public class MacAddressOperationDemo {
	private static int MAC_LENGTH = 12;
	private String resultMac = null;
	private final String MAC_ADDRESS_CHARACTER = "1234567890abcdefABCDEF:";
	
	/*
	 * {@code String} 类型的MAC合法性检查
	 * @param	beforeMac {@code String}需要检查的MAC address
	 * @return	{@code String} 检查后的结果,满足条件是true;不满足是false.
	 * 
	 * */
	public boolean MacAddressFormatCheck(String beforeMac){
		char[] cMacArray = beforeMac.toCharArray();
		for(int i = 0; i < cMacArray.length; i++ ){
			if((MAC_ADDRESS_CHARACTER.indexOf(cMacArray[i])) == -1){
				return false;
			}			
		}			
		return true;
	}
	
	
	/*
	 * String 类型的MAC与任意数字的加法运算
	 * @param	beforeMac {@code String}需要运算的MAC address
	 * @param	iStep {@code Integer}需要相加的数值
	 * @return	{@code String} 运算后的MAC地址大写格式
	 * 
	 * */
	public String MacAddressAdditive(String beforeMac, int iStep){		
			
		long lMac = Long.parseLong(beforeMac, 16);
		long iMacValue = lMac + iStep;		
		resultMac = Long.toHexString(iMacValue).toUpperCase();
		
		/*
		 * 确认MAC长度是12位
		 * 多余的部分取前12位
		 * 不足的部分在前面补“0”		
		 * 
		 * */		
		if(resultMac.length() > MAC_LENGTH){
			resultMac = resultMac.substring(0, MAC_LENGTH);
		}
		else if(resultMac.length() < MAC_LENGTH){
			String ts = null;
			for(int i = 0; i < (MAC_LENGTH -resultMac.length()); i++){
				ts = ts + "0";
			}
			resultMac = ts.concat(resultMac);
		}
		else{
			
		}
		
		return resultMac;
	}
	
	/*
	 * {@code String}类型的MAC与任意 {@code Integer}数字的减法运算
	 * @param	beforeMac {@code String}需要运算的MAC address
	 * @param	iStep {@code Integer}需要相加的数值
	 * @return	{@code String} 运算后的MAC地址大写格式
	 * 
	 * */
	public String MacAddressSubtraction(String beforeMac, int iStep){		
		
		long lMac = Long.parseLong(beforeMac, 16);
		long iMacValue = lMac - iStep;		
		resultMac = Long.toHexString(iMacValue).toUpperCase();
		
		/*
		 * 确认MAC长度是12位
		 * 多余的部分取前12位
		 * 不足的部分在前面补“0”		
		 * 
		 * */		
		if(resultMac.length() > MAC_LENGTH){
			resultMac = resultMac.substring(0, MAC_LENGTH);
		}
		else if(resultMac.length() < MAC_LENGTH){
			String ts = null;
			for(int i = 0; i < (MAC_LENGTH -resultMac.length()); i++){
				ts = ts + "0";
			}
			resultMac = ts.concat(resultMac);
		}
		else{
			
		}
		
		return resultMac;
	}
	
	/*
	 * {@code String}类型的MAC地址转换成带":"的格式.
	 * @param	beforeMac {@code String}需要运算的MAC address
	 * @return	{@code String} 运算后带":"格式的MAC地址大写格式
	 * 
	 * */
	public String MacAddressAddColon(String beforeMAC){
		/*
		 * 确认MAC长度是12位
		 * 多余的部分取前12位
		 * 不足的部分在前面补“0”		
		 * 
		 * */	
		if(beforeMAC.length() > MAC_LENGTH){
			beforeMAC = beforeMAC.substring(0, MAC_LENGTH);
		}
		else if(beforeMAC.length() < MAC_LENGTH){
			String ts = null;
			for(int i = 0; i < (MAC_LENGTH -beforeMAC.length()); i++){
				ts = ts + "0";
			}
			beforeMAC = ts.concat(beforeMAC);
		}
		else{
			
		}
		String[] macArray = {null};
		
		int i = 0;
		for(; i < MAC_LENGTH; i += 2){			
			macArray[i] = beforeMAC.substring(i, i+2);
		}
		
		i = 0;
		for(; i < (macArray.length - 1); i ++){
			resultMac = macArray[i] + ":";
		}
		
		resultMac = resultMac + macArray[macArray.length - 1];
		
		
		return resultMac.toUpperCase();
	}
	
	/*
	 * {@code String}类型带":"的格式的MAC地址转换成不带":"的格式.
	 * @param	beforeMac {@code String}需要运算的带":"格式得MAC address
	 * @return	{@code String} 运算后不带":"格式的MAC地址大写格式
	 * 
	 * */
	public String MacAddressDeleteColon(String beforeMAC){			
		//resultMac = beforeMAC.replace(":", "");
		String[] macArray = beforeMAC.split(":");
		for(int i = 0; i < macArray.length; i++){
			resultMac = resultMac + macArray[i].toString();
		}		
		return resultMac;
	}
	
	public String MacAddressChangeToASSIC(String beforeMAC){
		final int ASSIC_INDEX_0 = 30;
		final int ASSIC_INDEX_a = 61;
		final int ASSIC_INDEX_A = 97;
		final int ASSIC_INDEX_COLON = 0x3a;
				
		char[] cMacArray = beforeMAC.toCharArray();
		int[] iMacArray ={0};
		for(int i = 0; i < cMacArray.length; i++){
			if((cMacArray[i] >= '0') && (cMacArray[i] <= '9')){
				iMacArray[i] = cMacArray[i] - '0' + ASSIC_INDEX_0;
			}
			else if((cMacArray[i] >= 'a') && (cMacArray[i] <= 'z')){
				iMacArray[i] = cMacArray[i] - 'a' + ASSIC_INDEX_a;
			}
			else if((cMacArray[i] >= 'A') && (cMacArray[i] <= 'Z')){
				iMacArray[i] = cMacArray[i] - 'A' + ASSIC_INDEX_A;
			}
			else if(cMacArray[i] == ':'){
				iMacArray[i] = ASSIC_INDEX_COLON;
			}	
			else{
				//format error
			}
		}
		resultMac = iMacArray.toString();
			
		return resultMac;
	}
	
	public static void main(String[] args) {
		MacAddressOperationDemo maod = new MacAddressOperationDemo();
		String resultMAC = maod.MacAddressAdditive("23456789ABC", 0x9000);
		System.out.println(resultMAC);
		System.exit(0);

	}

}

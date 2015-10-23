package br.com.wgengenharia.manager.test;




public class Test {
	
	
	private static final String ZERO_FILL = "%04d";
	private static final String COUNTRY_CODE = "789";
	

	public static void main (String args[]){
		int id = 1;
		System.out.println(COUNTRY_CODE+String.format(ZERO_FILL,id)+codeResult(COUNTRY_CODE + String.format(ZERO_FILL,id)));
	}
	
	public static String codeResult(String code){
		String[] a = code.split("(?!^)");
		int par = 0;
		int impar = 0;
		int result_code = 0;
		
		par = Integer.parseInt(a[1]) + Integer.parseInt(a[3]) + Integer.parseInt(a[5]);
		impar = Integer.parseInt(a[0]) + Integer.parseInt(a[2]) + Integer.parseInt(a[4]) + Integer.parseInt(a[6]);
		
		result_code = impar * 3 + par;
		
		result_code = 10 - (result_code % 10);
		 
		System.out.println(result_code);
		return String.valueOf(result_code);
	}
	
}
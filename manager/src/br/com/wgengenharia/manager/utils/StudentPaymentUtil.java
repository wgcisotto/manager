package br.com.wgengenharia.manager.utils;

import br.com.wgengenharia.manager.model.StudentPayments;

public class StudentPaymentUtil {

	public static String generateStudentPaymentBarcode(StudentPayments studentPayment){
		String branchCode = "";
		String studentPaymentCode = "";
		
		for (int i = String.valueOf(studentPayment.getBranch().getId_branch()).length(); i < 4; i++) {
			branchCode += "0";
		}

		branchCode += String.valueOf(studentPayment.getBranch().getId_branch());
		
		for (int i = String.valueOf(studentPayment.getId_student_payments()).length(); i < 4; i++) {
			studentPaymentCode += "0";
		}
		
		studentPaymentCode += String.valueOf(studentPayment.getId_student_payments());
		
		
		return (branchCode + studentPaymentCode);
		
	}
	
}

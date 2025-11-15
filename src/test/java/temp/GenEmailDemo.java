package temp;

import java.util.Date;

public class GenEmailDemo {
	
	public static void main(String[] args) {
		
		Date date=new Date();
		String dayString=date.toString();
		String noSpace=dayString.replaceAll(" ", "");
		String no1Space=noSpace.replaceAll("\\:", "");

		String emailWithTime=no1Space+"@gmail.com";
		System.out.println(emailWithTime);
		
		
		
	}

}


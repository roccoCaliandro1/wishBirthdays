package com.rocco.birthdays;

public class WishBirthdays {
	public static void main(String[] args) {
		System.out.println("Happy Birthday Rocco!");


		MailSender.sendEmail("wish.birthday@libero.it", 
				"17sMauzJeJJYp2pC", 
				new String[] {"rockdesires@gmail.com"}, 
				"Subject",  
				"I would like to wish you an happy birthday");

	}
}

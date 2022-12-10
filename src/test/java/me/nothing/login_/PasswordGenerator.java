package me.nothing.login_;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "nothing";
		String encodedPassword = passwordEncoder.encode(plainPassword);
		


		System.out.println(encodedPassword);
      

	}

}

package me.nothing.login_;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "staff";
		String encodedPassword = passwordEncoder.encode(plainPassword);
		System.out.println();
		System.out.println(encodedPassword);

	
	}

}

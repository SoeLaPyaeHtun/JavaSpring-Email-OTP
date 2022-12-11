package me.nothing.login_.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import me.nothing.login_.model._UserDetails;
import me.nothing.login_.model.User;
import me.nothing.login_.repository.UserRepository;
import net.bytebuddy.utility.RandomString ;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new _UserDetails(user);
	}

	public User getUserbyUsername(String username) throws UsernameNotFoundException{
		return userRepo.findByUsername(username);
		
		
	}

    public void generateOneTimePassword(User user) throws UnsupportedEncodingException, MessagingException {
		String OTP = RandomString.make(8);
		System.out.println("OTP is: " + OTP);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encoderOTP = passwordEncoder.encode(OTP);
		user.setOtp(encoderOTP);
		user.setOtptime(new Date());

		userRepo.save(user);
		sendOTPEmail(user, OTP);
    }

	private void sendOTPEmail(User user, String OTP) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();              
    	MimeMessageHelper helper = new MimeMessageHelper(message);
     
    	helper.setFrom("contact@shopme.com", "Shopme Support");
		System.out.println(user.getEmail());
    	helper.setTo(user.getEmail());
     
    	String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
     
    	String content = "<p>Hello " + user.getUsername() + "</p>"
            + "<p>For security reason, you're required to use the following "
            + "One Time Password to login:</p>"
            + "<p><b>" + OTP + "</b></p>"
            + "<br>"
            + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
     
    helper.setSubject(subject);
     
    helper.setText(content, true);
     
    mailSender.send(message);

	System.out.println("email was sent");
	} 


	@Autowired JavaMailSender mailSender;
}

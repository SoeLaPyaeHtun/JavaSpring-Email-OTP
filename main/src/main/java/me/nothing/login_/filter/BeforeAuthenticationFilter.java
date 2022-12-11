package me.nothing.login_.filter;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import me.nothing.login_.model.User;
import me.nothing.login_.service.UserService;


@Component
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
     
    @Autowired
    private UserService userService;

         
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
         
        String username = request.getParameter("username");
        System.out.println(username);
        User user = userService.getUserbyUsername(username);
        System.out.println(user);
        
        if(user != null){
            float spamScore = getGoogleRecaptchaScore(); 
            if(spamScore < 0.5){
                if(user.isOTPRequired()){
                    return super.attemptAuthentication(request, response);
                }
                try{
                userService.generateOneTimePassword(user);
                throw new InsufficientAuthenticationException("OTP");  
                }catch(MessagingException | UnsupportedEncodingException ex){
                    throw new AuthenticationServiceException("Error while sending otp");
                }
            }
        }

        return super.attemptAuthentication(request, response);
    }
     
    private float getGoogleRecaptchaScore() {
        return 0.43f;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
     
    @Autowired
    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }
     
    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }
     
    public BeforeAuthenticationFilter() {
        super.setUsernameParameter("username");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

}
    


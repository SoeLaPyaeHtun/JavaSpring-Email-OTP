package me.nothing.login_.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

                System.out.print(exception.getMessage().contains("OTP"));
            

                System.out.println("Login error:" + exception.getMessage());
            
                
            String  failureUrl = "/login?error";
            if(exception.getMessage().contains("OTP")){
                 failureUrl = "/login?otp=true";
            }
        super.setDefaultFailureUrl(failureUrl);
        super.onAuthenticationFailure(request, response, exception);
    }

   

    
}

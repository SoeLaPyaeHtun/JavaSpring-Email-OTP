package me.nothing.login_.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import me.nothing.login_.model.Staff;
import me.nothing.login_.service.StaffService;





@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {



@Autowired
private StaffService staffService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

                System.out.print(exception.getMessage().contains("OTP"));
            

                System.out.println("Login error:" + exception.getMessage());

                String username = request.getParameter("username");
                Staff staff = staffService.getUserbyUsername(username);
          

            
            if(staff != null){
            
                    if(staff.getFailedAttempt() < 5 ){
                        staffService.increaseFailedAttempt(staff);
                    }else{
                        exception = new LockedException("Your account is locked , rquest otp code");
                    }
                
                
            }else{
                System.out.println("User does not exit");
            }
                
            String  failureUrl = "/login?error";
            if(exception.getMessage().contains("OTP")){
                 failureUrl = "/login?otp=true";
            }else{
            
                if(staff != null && staff.isOTPRequired()){
                    failureUrl = "/login?otp=true";
                }

            }
        super.setDefaultFailureUrl(failureUrl);
        super.onAuthenticationFailure(request, response, exception);
    }

   

    
}

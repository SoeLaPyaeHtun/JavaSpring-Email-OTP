package me.nothing.login_.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import me.nothing.login_.model.Staff;
import me.nothing.login_.model._StaffDetails;
import me.nothing.login_.service.StaffService;



@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private StaffService staffService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                System.out.println("success");


                _StaffDetails staffDetails = (_StaffDetails) authentication.getPrincipal();
                System.out.println(staffDetails.getPassword());
                Staff staff = staffDetails.getStaff();
                if(staff.isOTPRequired()){
                    staffService.clearOTP(staff);
                }


        super.onAuthenticationSuccess(request, response, authentication);
    }

    
}

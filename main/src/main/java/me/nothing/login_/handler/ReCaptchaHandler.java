package me.nothing.login_.handler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import me.nothing.login_.response.RecaptchaResponse;

public class ReCaptchaHandler {

    private String recaptchaSecret = "6Lep2nojAAAAAOGxLQAxgCFUi5vaeO845ddinV-a";
    private String recaptchaServerURL = "https://www.google.com/recaptcha/api/siteverify";

    public float verify(String recaptchaFormResponse){
        System.out.println("recaptcha handler called");
        System.out.println("recaptcha - response " + recaptchaFormResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", recaptchaSecret);
        map.add("response", recaptchaFormResponse);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        RecaptchaResponse response = restTemplate.postForObject(
            recaptchaServerURL, request, RecaptchaResponse.class);
            System.out.println("recaptcha respone \n");
            System.out.println("Success " + response.isSuccess());
            System.out.println("Action " + response.getAction());
            System.out.println("HostName " + response.getHostname());
            System.out.println("Score " + response.getScore());
            System.out.println("Time stamp " + response.getChallenge_ts());

            if(response.getErrorCodes() != null){ 
                System.out.println("error code");
                for(String error : response.getErrorCodes()){
                    System.out.println("\t "+ error);
                }
            }

            return response.getScore();


    }
    
}

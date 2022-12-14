package me.nothing.login_.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class RecaptchaResponse {
 
    private boolean success;
    private float score;
    private String action;
    private String challenge_ts;
    private String hostname;

    @JsonProperty("error-codes")
    private String[] errorCodes;

    
}

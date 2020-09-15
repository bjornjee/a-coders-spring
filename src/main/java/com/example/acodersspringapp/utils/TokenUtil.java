package com.example.acodersspringapp.utils;


import org.springframework.stereotype.Service;

import com.example.acodersspringapp.utils.model.UtilModel;

import lombok.extern.slf4j.Slf4j;


import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class TokenUtil {

    //encrypt the username and password and return token
    public String encoder(String username, String password){
        String strEncode = String.format("%s∑%s", username,password);
        String encryptedPW = Base64.getEncoder().encodeToString(strEncode.getBytes());
        return encryptedPW;
    }

    //decrypt to check if its registered user
    public UtilModel decoder(String encryptedStr) throws IllegalArgumentException {
    	log.info("In decoder()");
    	byte[] decodedBytes;
    	try {
    		decodedBytes = Base64.getMimeDecoder().decode(encryptedStr);
    	} catch (IllegalArgumentException ex ) {
    		throw ex;
    	}
        String decodedStr = new String(decodedBytes);
        log.info("decoded token: {}",decodedStr);
        //Check for string structure
        String regex = "[a-zA-Z0-9 !\"#$%&'()*+,-./:;<=>?@\\^_`{|}~]+∑[a-zA-Z0-9 !\"#$%&'()*+,-./:;<=>?@\\^_`{|}~]+";
        Pattern r = Pattern.compile(regex);
        Matcher matched = r.matcher(decodedStr);
        log.info("Decoded str match regex: {}",String.valueOf(matched.matches()));
        //If does not match, return null
        if (!matched.matches()) {
            return null;
        }
        String[] arr = decodedStr.split("∑");
        String user = arr[0];
        String pass = arr[1];
        log.info("u: {}, p:  {}",user,pass);

        return new UtilModel(user,pass);

    }

}
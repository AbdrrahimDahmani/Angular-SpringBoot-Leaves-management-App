package com.example.Gestion.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErroResponse {

    private HttpStatus statusCode;
    private String errormessage;
    private Object body;
    public  ErroResponse(HttpStatus statusCode,String errormessage){
        this(statusCode,errormessage,errormessage);
    }

    public  int getStatusCodeValue(){
        return statusCode.value();
    }
}
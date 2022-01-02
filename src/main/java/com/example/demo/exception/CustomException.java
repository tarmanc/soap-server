package com.example.demo.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}

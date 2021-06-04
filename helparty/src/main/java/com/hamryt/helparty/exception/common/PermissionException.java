package com.hamryt.helparty.exception.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PermissionException extends ResponseStatusException {
    public PermissionException(){this("you don't have permission ");}
    
    public PermissionException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
    
}

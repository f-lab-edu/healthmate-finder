package com.hamryt.helparty.service.session;

public interface SessionService {
    
    void sessionValidate();
    
    void validateUser(Long id);
    
    String getSessionEmail();
}

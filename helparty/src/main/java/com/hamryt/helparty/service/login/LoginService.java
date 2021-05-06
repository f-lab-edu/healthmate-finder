package com.hamryt.helparty.service.login;

public interface LoginService<T> {
    
    T login(String email, String password);
    
}

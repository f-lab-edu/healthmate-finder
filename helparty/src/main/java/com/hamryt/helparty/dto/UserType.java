package com.hamryt.helparty.dto;

public enum UserType {
    USER("일반사용자"),
    GYM("운동시설"),
    PT("퍼스널트레이너");
    
    private String name;
    
    UserType(String name) {
        this.name = name;
    }
    
}

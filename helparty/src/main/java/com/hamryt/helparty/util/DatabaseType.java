package com.hamryt.helparty.util;

public enum DatabaseType {

    MASTER("master"), SLAVE("slave");

    private String type;

    DatabaseType(String type){this.type = type;}

    public String getType(){return this.type;}
}

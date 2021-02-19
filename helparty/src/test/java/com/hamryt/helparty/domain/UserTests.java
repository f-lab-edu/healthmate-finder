package com.hamryt.helparty.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    public void creation(){
        User user = User.builder()
                .email("tester@example.com")
                .name("tester")
                .password("test")
                .build();

        assertEquals(user.getEmail(), "tester@example.com");
        assertEquals(user.getName(), "tester");
        assertEquals(user.getPassword(), "test");

    }
}

package com.test.demo.token;

import com.test.demo.token.Token;

public class TokenImp implements Token {

    private String id;

    public TokenImp(String id) {
        setId(id);
    }

    protected void setId(String id){
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}

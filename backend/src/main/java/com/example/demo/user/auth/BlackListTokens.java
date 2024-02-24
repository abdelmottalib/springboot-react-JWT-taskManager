package com.example.demo.user.auth;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BlackListTokens {
    private ArrayList<String> blackList = new ArrayList<>();
    public void addToken(String token){
        blackList.add(token);
    }
    public boolean isTokenInBlackList(String token){
        return blackList.contains(token);
    }
    public void printBlackList(){
        
    }
}

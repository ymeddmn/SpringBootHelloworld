package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface UserService {
    String login(String username,String pwd) throws JsonProcessingException;

    String register(String userName, String pwd) throws JsonProcessingException;
}

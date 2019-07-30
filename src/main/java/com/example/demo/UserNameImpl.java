package com.example.demo;

import com.example.demo.bean.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserNameImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String login(String username, String pwd) throws JsonProcessingException {
//        Map<String, Object> map = jdbcTemplate.queryForMap("select * from users where username = ?", 12);
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select username,pwd from users where ");
//        for (Map<String, Object> map1 : maps) {
//            Set<Map.Entry<String, Object>> entries = map1.entrySet();
//            for (Map.Entry<String, Object> entry : entries) {
//                System.out.println(entry.getKey() + "==" + entry.getValue());
//            }
//        }

        BaseResponse response = new BaseResponse();
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from mage.users where username='" + username + "' and pwd= '" + pwd + "' ");
        if (map != null) {
            String qUserName = (String) map.get("username");
            String qPwd = (String) map.get("pwd");
            if (username.equals(qUserName) && pwd.equals(qPwd)) {
                //用户名密码匹配成功，登陆成功
                response.code = 200;
                response.msg = "登陆成功";
            } else {
                //用户名密码不匹配，登陆失败
                response.code = 300;
                response.msg = "登陆失败，用户名或者密码错误";
            }
            //登陆成功
        } else {
            //登陆失败找不到用户
            response.code = 300;
            response.msg = "未找到用户";
        }
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(response);
    }

    /**
     * 注册
     *
     * @param userName
     * @param pwd
     * @return
     */
    @Override
    public String register(String userName, String pwd) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseResponse response = new BaseResponse();
        jdbcTemplate.execute(" insert into users (username,pwd) values ('" + userName + "','" + pwd + "')");
        response.code = 200;
        response.msg = "注册成功";
        return objectMapper.writeValueAsString(response);
    }


}

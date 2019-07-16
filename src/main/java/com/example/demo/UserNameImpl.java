package com.example.demo;

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
    public List<Map<String, Object>> getUserName() {
//        Map<String, Object> map = jdbcTemplate.queryForMap("select * from users where username = ?", 12);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from users");
        for (Map<String,Object> map1:maps){
            Set<Map.Entry<String, Object>> entries = map1.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                System.out.println(entry.getKey() + "==" + entry.getValue());
            }
        }

        return maps;
    }
}

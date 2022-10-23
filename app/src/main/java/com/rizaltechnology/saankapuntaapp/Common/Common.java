package com.rizaltechnology.saankapuntaapp.Common;

import com.rizaltechnology.saankapuntaapp.Models.Users;

import java.util.HashMap;
import java.util.Map;

public class Common {

    public static Map<String,Object> toLoginMaps(Users users){
        Map<String,Object> params = new HashMap<>();
        params.put("email",users.getEmail());
        params.put("password",users.getPassword());
        params.put("userType",users.getUserType());
        return params;
    }
}

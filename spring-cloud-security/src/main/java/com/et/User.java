package com.et;

import lombok.Data;

@Data
public class User {


    private Long id;

    private String username;
    private String password; // 密码应加密存储
    private String role; // 用户角色，例如 "USER", "ADMIN"


}
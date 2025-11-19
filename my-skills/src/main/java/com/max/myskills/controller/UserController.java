package com.max.myskills.controller;

import com.max.myskills.dto.LoginRequest;
import com.max.myskills.dto.RegisterRequest;
import com.max.myskills.entity.User;
import com.max.myskills.security.JwtTokenProvider;
import com.max.myskills.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/health")
    public String health() {
        return "health";
    }

    /**
     * API :用户注册
     * HTTP方法: POST
     * api/user/register
     * @param registerRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try{
            //把DTO对象转化为User实体
            User newUser = new User();
            newUser.setUserName(registerRequest.getUsername());
            newUser.setPassword(registerRequest.getPassword());
            newUser.setPhone(registerRequest.getPhone());
            newUser.setAge(registerRequest.getAge());
            newUser.setRegion(registerRequest.getRegion());
            newUser.setAvatar(registerRequest.getAvatar());
            //调用service注册
            User registerUser = userService.register(newUser);
            //注册成功后返回响应成功
            Map<String,Object> response = new HashMap<>();
            response.put("message","用户注册成功");
            return ResponseEntity.ok(response);
        }catch (RuntimeException e){
            Map<String,Object> errorResponse = new HashMap<>();
            errorResponse.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);//badRequest是status(400)的便捷写法
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{

            User loginUser = userService.login(loginRequest.getUsername(),loginRequest.getPassword());
            String token = jwtTokenProvider.generateToken(loginUser.getUserName());
            Map<String,Object> response = new HashMap<>();
            response.put("message","登陆成功");
            response.put("userId", loginUser.getId());
            response.put("username",loginUser.getUserName());
            response.put("token", token);

            return  ResponseEntity.ok(response);

        }catch (RuntimeException e){
            Map<String,Object> errorResponse = new HashMap<>();
            errorResponse.put("message",e.getMessage());
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
}

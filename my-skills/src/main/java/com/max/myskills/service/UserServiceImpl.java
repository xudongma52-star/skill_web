package com.max.myskills.service;

import com.max.myskills.entity.User;
import com.max.myskills.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;//注入配置的密码加密器

    @Override
    public User register(User user){
        User save = new User();
        try {
            //判断用户名是否存在
            if(userRepository.existsByUserName((user.getUserName()))){
                throw new RuntimeException("用户名已经存在");
            }

            //对密码进行加密
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            save = userRepository.save(user);
            System.out.println(save);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return save;
        }
    }

    @Override
    public User login(String username, String password){
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("用户不存在")));
        //// 第一个参数是明文密码，第二个是加密后的密码
        if(!passwordEncoder.matches(password,user.get().getPassword())){
            throw new RuntimeException("密码错误");
        }
        return user.get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username:"+username));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}

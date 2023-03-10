package com.example.videoblogappsecurityjorge.user;

import com.example.videoblogappsecurityjorge.configuration.JwtProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public String authUser(String username, String pass) throws Exception {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if(userOptional.isEmpty()){
            throw new Exception("The user no exits");
        }
        if (userOptional.get().getPassword().equals(pass)){
            return  jwtProvider.generateToken(username); //
        }else{
            throw new Exception("Password is wrong");
        }
    }
}

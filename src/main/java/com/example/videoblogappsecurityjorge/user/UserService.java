package com.example.videoblogappsecurityjorge.user;

import com.example.videoblogappsecurityjorge.configuration.JwtExceptionPersonal;
import com.example.videoblogappsecurityjorge.configuration.JwtProvider;
import com.example.videoblogappsecurityjorge.secrets.SecretService;
import com.example.videoblogappsecurityjorge.shared.Constants;
import io.jsonwebtoken.ExpiredJwtException;
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
    @Autowired
    private SecretService secretService;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public String authUser(String username, String pass) throws Exception {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if(userOptional.isEmpty()){
            throw new UserException(Constants.HTTP_CODE_NOT_FOUND);
        }
        if (userOptional.get().getPassword().equals(pass)){
            return  jwtProvider.generateToken(username, secretService.getApiKey()); //
        }else{
            throw new Exception(Constants.ERROR_MESSAGE_WHEN_INCORRECT_PASS);
        }
    }

    public String getUsernameFromJWT(String token){
        try{
                return jwtProvider.getUsernameFromToken(token);
          }catch ( ExpiredJwtException e){
            throw new JwtExceptionPersonal(Constants.ERROR_MESSAGE_WHEN_JWT_EXPIRED, Constants.HTTP_CODE_JWT_EXPIRED);
        }
    }

    public void addUser(UserRequest userRequest){
        Optional<User> userToFind = userRepository.findUserByUsername(userRequest.getUsername());

            if (userToFind.isPresent()){
                throw  new UserException(Constants.CODE_WHEN_USER_ALREADY_EXISTS );
            }
            try{
                User user =  new User();
                user.setFullname(userRequest.getFullname());
                user.setUsername(userRequest.getUsername());
                user.setPassword(userRequest.getPassword());

                userRepository.save(user);
            }catch (UserException e){
                throw  new UserException(Constants.CODE_WHEN_EXCEPTION_SAVING_USER);
            }
    }
}

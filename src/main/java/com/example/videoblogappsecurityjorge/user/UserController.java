package com.example.videoblogappsecurityjorge.user;

import com.example.videoblogappsecurityjorge.configuration.JwtExceptionPersonal;
import com.example.videoblogappsecurityjorge.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({"/",""})
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody HashMap<String,String> payload
            ){
        try {
            if( !(payload.containsKey("username") && payload.containsKey("password"))){
                return  ResponseEntity.badRequest().body(Constants.ERROR_MESSAGE_WHEN_INCORRECT_REQUEST);
            }
            //es lo mismo
//            if( (!payload.containsKey("username") || !payload.containsKey("password"))){
//                return  ResponseEntity.badRequest().body("Error in the request");
//            }
            return ResponseEntity.ok().body(userService.authUser(payload.get("username"),payload.get("password")));
        }catch (UserException e){
            return ResponseEntity.status(e.getCode()).body(Constants.ERROR_MESSAGE_WHEN_CLIENTE_DOES_NOT_EXIST);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/validate-token")
    public ResponseEntity<String> validate(
            @RequestParam String token){
        try {
            return  ResponseEntity.ok().body(userService.getUsernameFromJWT(token));
        }catch (JwtExceptionPersonal e){
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UserRequest userRequest
    ){
        try{
            if(userRequest.getUsername().isBlank() || userRequest.getPassword().isBlank()
            || userRequest.getFullname().isBlank()){
                    return  ResponseEntity.badRequest().body(Constants.ERROR_MESSAGE_WHEN_INCORRECT_REQUEST);
            }else{
                userService.addUser(userRequest);
                return ResponseEntity.status(201).body(Constants.SUCCESS_MESSAGE);
            }

        }catch ( UserException e){
            return ResponseEntity.status(e.getCode()).body(Constants.ERROR_MESSAGE_WHEN_CLIENTE_ALREADY_EXISTS);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

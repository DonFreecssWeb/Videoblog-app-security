package com.example.videoblogappsecurityjorge.user;

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
                return  ResponseEntity.badRequest().body("Error in the request");
            }
            //es lo mismo
//            if( (!payload.containsKey("username") || !payload.containsKey("password"))){
//                return  ResponseEntity.badRequest().body("Error in the request");
//            }
            return ResponseEntity.ok().body(userService.authUser(payload.get("username"),payload.get("password")));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

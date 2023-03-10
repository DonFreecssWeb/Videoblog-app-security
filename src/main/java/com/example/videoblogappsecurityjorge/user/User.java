package com.example.videoblogappsecurityjorge.user;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//funciona sin poner @document(collection="user")
public class User {

    //funciona sin poner@Id
    private ObjectId id;

    private String username;
    private String fullname;
    private String password;
}

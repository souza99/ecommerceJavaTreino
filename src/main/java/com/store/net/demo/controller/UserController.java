package com.store.net.demo.controller;

import com.store.net.demo.DTO.UserDTO;
import com.store.net.demo.entity.User;
import com.store.net.demo.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/user")
@Tag(name = "User", description ="API para consultas a usuario")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.logger(this.getClass());

    @PostMapping(value = "/userAdd")
    public ResponseEntity<UserDTO> saveUser(
            @RequestBody User user) throws URISyntaxException
    {
        try{
        UserDTO novoUser = userService.save(user);
        return ResponseEntity.created(new URI("/user/userAdd" + novoUser.getId())).body(novoUser);
        }catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}

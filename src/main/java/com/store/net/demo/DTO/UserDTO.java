package com.store.net.demo.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private Date birfday;
    private String password;

}

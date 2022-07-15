package com.store.net.demo.Service;

import com.store.net.demo.repository.UserRepository;
import com.store.net.demo.DTO.UserDTO;
import com.store.net.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    public boolean existsById(Long id){
        return userRepository.existsById(id);
    }

    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        UserDTO userDTO = mapper.map(userOptional, UserDTO.class);
        return userDTO;
    }

    public UserDTO save(User user){
        UserDTO userDTO = new UserDTO();
        try{
            User userSave = new User();
            if(user.getPassword() !=null){
                throw new IllegalArgumentException("Senha não pode ser nula");
            }
            userSave.setName(user.getName());
            userSave.setPassword(user.getPassword());
            userSave.setBirfday(user.getBirfday());
            userRepository.save(userSave);

            userDTO = mapper.map(user, UserDTO.class);

        }catch (Exception e){

        }
        return userDTO;
    }


}


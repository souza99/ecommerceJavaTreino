package com.store.net.demo.Service;

import com.store.net.demo.Excption.BadResourceException;
import com.store.net.demo.Excption.ResourceNotFoundException;
import com.store.net.demo.repository.UserRepository;
import com.store.net.demo.DTO.UserDTO;
import com.store.net.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public void edit(User user) throws ResourceNotFoundException, BadResourceException {
        if(!StringUtils.isEmpty(user.getId())){
            if(!existsById(user.getId())){
                throw new ResourceNotFoundException("Usuario não encontrado com o id: " + user.getId());
            }
            userRepository.save(user);
        }else {
            BadResourceException exc = new BadResourceException("Falha ao salvar o usuario");
            exc.addErrorMessage("Usuario está nulo ou em branco");
            throw exc;
        }
    }

    public void deletedById(Long id) throws ResourceNotFoundException{
        if(!existsById(id)){
            throw new ResourceNotFoundException("Usuario não encotrado com i id: " + id);
        }else {
            userRepository.deleteById(id);
        }
    }


}


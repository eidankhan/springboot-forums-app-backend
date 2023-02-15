package com.sprinboot.jwt.app.transformer;


import com.sprinboot.jwt.app.model.User;
import com.sprinboot.jwt.app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTransformer {

    public User userDTOToUser(UserDTO userDTO){
        User user = new User();
        if(userDTO != null && userDTO.getUsername() != null)
            user.setUsername(userDTO.getUsername());
        if(userDTO != null && userDTO.getPassword() != null)
            user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        if(user != null && user.getUsername() != null)
            userDTO.setUsername(user.getUsername());
        if(user != null && user.getPassword() != null)
            userDTO.setPassword(user.getPassword());
        return userDTO;
    }

}

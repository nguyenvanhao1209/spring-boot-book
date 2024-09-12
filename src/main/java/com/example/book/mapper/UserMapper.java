package com.example.book.mapper;

import com.example.book.dto.UserDTO;
import com.example.book.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public List<User> toEntities(List<UserDTO> userDTOList) {
        return userDTOList.stream().map(this::toEntity).toList();
    }

    public List<UserDTO> toDTOs(List<User> userList) {
        return userList.stream().map(this::toDTO).toList();
    }
}

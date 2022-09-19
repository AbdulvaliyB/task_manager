package com.example.task_manager.service;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.entity.User;
import com.example.task_manager.projection.UserTaskProjection;
import com.example.task_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public ApiResponse add(UserDTO userDTO) {

        User user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setTaskList(userDTO.getTaskList());

        userRepository.save(user);
        return ApiResponse.builder().message("Added").success(true).object(user).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.get();
        return ApiResponse.builder().message("Get This").success(true).object(user).build();
    }

    public ApiResponse getAll() {
        List<User> userList = userRepository.findAll();
        return ApiResponse.builder().message("Get This").success(true).object(userList).build();
    }


    public ApiResponse edit(Long id, UserDTO userDTO) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.get();

        user.setFirstname(userDTO.getFirstname());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setTaskList(userDTO.getTaskList());

        userRepository.save(user);
        return ApiResponse.builder().message("Changed").success(true).object(user).build();
    }

    public ApiResponse delete(Long id) {
        userRepository.deleteById(id);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }
    public ApiResponse getUserTaskProjection(){
       List<UserTaskProjection> userTaskProjectionList =  userRepository.getUserTaskProjection();
        return ApiResponse.builder().message("here").object(userTaskProjectionList).success(true).build();
    }

}

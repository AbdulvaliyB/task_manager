package com.example.task_manager.service;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.entity.Role;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.User;
import com.example.task_manager.entity.enums.RoleEnum;
import com.example.task_manager.repository.RoleRepository;
import com.example.task_manager.repository.TaskRepository;
import com.example.task_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;


    final RoleRepository roleRepository;

    public ApiResponse add(UserDTO userDTO) {
        LOG.debug("User Service >> Create User {}",userDTO);
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        List<Task> taskList =new ArrayList<>();
        for (Long taskId : userDTO.getTaskIdList()) {
            Optional<Task> task = taskRepository.findById(taskId);
            Task task1 = task.get();
            taskList.add(task1);
        }

        user.setTaskList(taskList);

        RoleEnum roleEnum = RoleEnum.valueOf(userDTO.getRole());
        Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
        user.setRoleList(new LinkedHashSet(Collections.singleton(byRoleName.get())));

        userRepository.save(user);
        return ApiResponse.builder().message("Added").success(true).object(user).build();
    }

    public ApiResponse getOne(Long id) {
        LOG.debug("User Service >> Find User By Id{}",id);
        User user;
        try {
            Optional<User> byId = userRepository.findById(id);
            user = byId.get();
        }catch (Exception e){
            return ApiResponse.builder().message("Not Found").success(false).build();
        }

        return ApiResponse.builder().message("Get This").success(true).object(user).build();
    }

    public ApiResponse getAll() {
        List<User> userList = userRepository.findAll();
        LOG.debug("User Service >> Find All Users {}",userList);
        return ApiResponse.builder().message("Get This All").success(true).object(userList).build();
    }


    public ApiResponse edit(Long id, UserDTO userDTO) {
        LOG.debug("User Service >> Find User By Id {} And Editing It {}",id,userDTO);
        User user;
        try {
            Optional<User> byId = userRepository.findById(id);
            user = byId.get();
        }catch (Exception e){
            return ApiResponse.builder().message("Not Found").success(false).build();
        }
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        List<Task> taskList = new ArrayList<>();
        for (Long taskId : userDTO.getTaskIdList()) {
            Optional<Task> task = taskRepository.findById(taskId);
            taskList.add(task.get());
        }

        user.setTaskList(taskList);

        RoleEnum roleEnum = RoleEnum.valueOf(userDTO.getRole());
        Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
        user.setRoleList(new LinkedHashSet(Collections.singleton(byRoleName.get())));

        userRepository.save(user);
        return ApiResponse.builder().message("Changed").success(true).object(user).build();
    }

    public ApiResponse delete(Long id) {
        LOG.debug("User Service >> Delete User By Id{}",id);
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            return ApiResponse.builder().message("Not Found").success(false).build();
        }
        return ApiResponse.builder().message("Deleted").success(true).build();
    }

}

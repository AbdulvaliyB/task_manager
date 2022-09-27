package com.example.task_manager.service;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.UserDTO;
import com.example.task_manager.entity.Role;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.User;
import com.example.task_manager.entity.enums.RoleEnum;
import com.example.task_manager.projection.TaskProjection;
import com.example.task_manager.projection.UserTaskProjection;
import com.example.task_manager.repository.RoleRepository;
import com.example.task_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    final RoleRepository roleRepository;

    public ApiResponse add(UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setTaskList(userDTO.getTaskList());

        RoleEnum roleEnum=RoleEnum.valueOf(userDTO.getRole());
        Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
        user.setRoleList(new LinkedHashSet(Collections.singleton(byRoleName.get())));

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

        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setTaskList(userDTO.getTaskList());


        RoleEnum roleEnum=RoleEnum.valueOf(userDTO.getRole());
        Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
        user.setRoleList(new LinkedHashSet(Collections.singleton(byRoleName.get())));

        userRepository.save(user);
        return ApiResponse.builder().message("Changed").success(true).object(user).build();
    }

    public ApiResponse delete(Long id) {
        userRepository.deleteById(id);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }

    public ApiResponse getUserTaskProjection(Long id) {

        Optional<User> byId = userRepository.findById(id);

        UserTaskProjection userTaskProjection = new UserTaskProjection();
        List<TaskProjection> taskProjectionList = new ArrayList<>();

        for (Task t : byId.get().getTaskList()) {
            TaskProjection taskProjection = new TaskProjection();

            taskProjection.setTitle(t.getTitle());
            taskProjection.setDescription(t.getDescription());

            taskProjectionList.add(taskProjection);
        }


        userTaskProjection.setUsername(byId.get().getUsername());
        userTaskProjection.setPhoneNumber(byId.get().getPhoneNumber());
        userTaskProjection.setTaskProjectionList(taskProjectionList);


        return ApiResponse.builder().message("here").object(userTaskProjection).success(true).build();
    }

    public ApiResponse getUserTaskProjection() {
        List<User> users = userRepository.findAll();

        List<UserTaskProjection> userTaskProjectionList = new ArrayList<>();

        for (User user: users) {
            UserTaskProjection userTaskProjection = new UserTaskProjection();

            List<TaskProjection> taskProjectionList = new ArrayList<>();
            for (Task t :user.getTaskList()) {
                TaskProjection taskProjection = new TaskProjection();
                taskProjection.setTitle(t.getTitle());
                taskProjection.setDescription(t.getDescription());

                taskProjectionList.add(taskProjection);
            }

            userTaskProjection.setUsername(user.getUsername());
            userTaskProjection.setPhoneNumber(user.getPhoneNumber());
            userTaskProjection.setTaskProjectionList(taskProjectionList);

            userTaskProjectionList.add(userTaskProjection);

        }

        return ApiResponse.builder().message("here").object(userTaskProjectionList).success(true).build();
    }
}

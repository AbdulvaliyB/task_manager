package com.example.task_manager.component;


import com.example.task_manager.entity.Role;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.User;
import com.example.task_manager.entity.enums.PermissionEnum;
import com.example.task_manager.entity.enums.RoleEnum;
import com.example.task_manager.repository.RoleRepository;
import com.example.task_manager.repository.TaskRepository;
import com.example.task_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    //field metod
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;
    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            PermissionEnum[] values = PermissionEnum.values();

            Set<Role> roleForMe = new LinkedHashSet<>();
            Role adminRole = roleRepository.save(new Role(RoleEnum.ADMIN, Arrays.asList(values)));
            Role admin2Role=roleRepository.save(new Role(RoleEnum.ADMIN,new ArrayList<>(List.of(PermissionEnum.TASK_ADD))));
            Role userRole = roleRepository.save(new Role(RoleEnum.USER, new ArrayList<>(Arrays.asList(PermissionEnum.TASK_READ, PermissionEnum.TASK_READ_ALL))));

            List<Task> taskList = taskRepository.findAll();

            Task task = new Task();
            task.setName("Math");
            task.setTitle("Science");
            task.setDescription("Limits");

            taskRepository.save(task);

//            Task task1 = new Task();
//            task.setName("Chemistry");
//            task.setTitle("Home Work");
//            task.setDescription("H20");
//
//            taskRepository.save(task1);
//
//            Task task2 = new Task();
//            task.setName("Running");
//            task.setTitle("Sport");
//            task.setDescription("You have to run 3 km");
//
//            taskRepository.save(task2);

            taskList.add(task);
//            taskList.add(task1);
//            taskList.add(task2);


            roleForMe.add(adminRole);
            roleForMe.add(userRole);

            Set<Role> roleForAdmin=new LinkedHashSet<>();
            roleForAdmin.add(admin2Role);
            roleForAdmin.add(userRole);

            Set<Role> roleForUser=new LinkedHashSet<>();
            roleForUser.add(userRole);


//            User abd = userRepository.save(new User("Abdulvaliy", passwordEncoder.encode("123"),"abdulvaliyb@gmail.com", roleList));
            User abd = new User();
            abd.setUsername("Abdulvaliy");
            abd.setEmail("abdulvaliyb@gmail.com");
            abd.setPassword(passwordEncoder.encode("123"));
//            abd.setTaskList(taskList);
            abd.setPhoneNumber("998971670555");
            abd.setRoleList(roleForMe);
//            User jafar=userRepository.save(new User("Ja'far", passwordEncoder.encode("123"), roles));

            User jafar = new User();
            jafar.setUsername("Ja'far");
            jafar.setEmail("jafarbek1997@gmail.com");
            jafar.setPassword(passwordEncoder.encode("123"));
            jafar.setPhoneNumber("998997200855");
//            jafar.setTaskList(taskList);
            jafar.setRoleList(roleForAdmin);


            userRepository.save(abd);
            userRepository.save(jafar);
        }
    }
}

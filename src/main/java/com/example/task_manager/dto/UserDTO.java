package com.example.task_manager.dto;

import com.example.task_manager.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private String firstname;
    private String surname;
    private Long phoneNumber;
    private String address;
    private List<Task> taskList;
}
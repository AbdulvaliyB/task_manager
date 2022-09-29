package com.example.task_manager.dto;

import com.example.task_manager.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username, password, email, phoneNumber;

    private String role; //ROle.User moderator
    private List<Long> taskIdList;

}

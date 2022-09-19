package com.example.task_manager.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTaskProjection {

    private String firstname;
    private String surname;
    private Long phoneNumber;
    private List<TaskProjection> taskProjectionList;
}

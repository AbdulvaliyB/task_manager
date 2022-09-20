package com.example.task_manager;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;


import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Asssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;

//@SpringBootTest
@SpringJUnitConfig(classes = {TestConfig.class})
@RequiredArgsConstructor
class TaskServiceIntergrationTest {

    private final TaskService taskService;

    @Test
    public void whenSavingTask_thenOK() {
        ApiResponse add = ApiResponse.builder().object(taskService.add(new TaskDTO("name", "fghfgh", "fhghg"))).success(true).message("Added").build();
    MatcherAssert.assertThat(add, is(notNullValue()));
    }


}

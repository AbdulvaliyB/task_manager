package com.example.task_manager.service;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.entity.Task;
import com.example.task_manager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public ApiResponse add(TaskDTO taskDTO) {

        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        taskRepository.save(task);
        return ApiResponse.builder().message("Added").success(true).object(task).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        Task task = byId.get();
        return ApiResponse.builder().message("Get This").success(true).object(task).build();
    }

    public ApiResponse getAll() {
        List<Task> taskList = taskRepository.findAll();
        return ApiResponse.builder().message("Get This").success(true).object(taskList).build();
    }


    public ApiResponse edit(Long id, TaskDTO taskDTO) {
        Optional<Task> byId = taskRepository.findById(id);
        Task task = byId.get();

        task.setName(taskDTO.getName());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        taskRepository.save(task);
        return ApiResponse.builder().message("Changed").success(true).object(task).build();
    }

    public ApiResponse delete(Long id) {
        taskRepository.deleteById(id);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }
}

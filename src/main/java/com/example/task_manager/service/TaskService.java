package com.example.task_manager.service;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.entity.Task;
import com.example.task_manager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {
    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;

    public ApiResponse add(TaskDTO taskDTO) {
        LOG.debug("Task Service >> Create Task {}",taskDTO);
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        taskRepository.save(task);
        return ApiResponse.builder().message("Added").success(true).object(task).build();
    }

    public ApiResponse getOne(Long id) {
        LOG.debug("Task Service >> Find Task By Id{}",id);
        Optional<Task> byId = taskRepository.findById(id);
        Task task = byId.get();
        return ApiResponse.builder().message("Get This").success(true).object(task).build();
    }

    public ApiResponse getAll() {
        List<Task> taskList = taskRepository.findAll();
        LOG.debug("Task Service >> Find All Tasks {}",taskList);
        return ApiResponse.builder().message("Get This").success(true).object(taskList).build();
    }


    public ApiResponse edit(Long id, TaskDTO taskDTO) {
        LOG.debug("Task Service >> Find Task By Id {} And Editing It {}",id,taskDTO);
        Optional<Task> byId = taskRepository.findById(id);
        Task task = byId.get();

        task.setName(taskDTO.getName());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        taskRepository.save(task);
        return ApiResponse.builder().message("Changed").success(true).object(task).build();
    }


    public ApiResponse delete(Long id) {
        LOG.debug("Task Service >> Delete Task By Id{}",id);
        taskRepository.deleteById(id);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }
}

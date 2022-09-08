package com.example.task_manager.controller;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.repository.TaskRepository;
import com.example.task_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = taskService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = taskService.getAll();
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = taskService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TaskDTO taskDTO) {
        ApiResponse response = taskService.add(taskDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        ApiResponse response = taskService.edit(id, taskDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}

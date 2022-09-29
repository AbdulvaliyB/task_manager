package com.example.task_manager.controller;

import com.example.task_manager.dto.ApiResponse;
import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.repository.TaskRepository;
import com.example.task_manager.service.TaskService;
import com.example.task_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PreAuthorize("hasAuthority('TASK_READ_ALL')")
    @GetMapping
    public ResponseEntity getAll() {
        ApiResponse response = taskService.getAll();
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('TASK_READ')")
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        ApiResponse response = taskService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('TASK_DELETE')")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ApiResponse response = taskService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAnyAuthority('TASK_ADD')")
    @PostMapping
    public ResponseEntity add(@RequestBody TaskDTO taskDTO) {
        ApiResponse response = taskService.add(taskDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('TASK_EDIT')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        ApiResponse response = taskService.edit(id, taskDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}

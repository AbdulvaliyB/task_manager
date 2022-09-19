package com.example.task_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String firstname;

    @Column(length = 15)
    private String surname;

    @Column(length = 12)
    private Long phoneNumber;

    @Column(length = 10)
    private String address;

    @OneToMany
    private List<Task> taskList;
}

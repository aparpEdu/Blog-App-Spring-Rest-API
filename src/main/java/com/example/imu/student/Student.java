package com.example.imu.student;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students", schema = "mm")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "student_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

}

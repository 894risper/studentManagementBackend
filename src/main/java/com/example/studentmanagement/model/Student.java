package com.example.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "students")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message= "Name is required")
  @Size(max=100, message="Name must be at most 100 characters")
  @Column (nullable = false, length= 100)
    private String name;

  @NotBlank(message=" course is required")
  @Size (max= 100, message= "course must be at most 100 characters")

  @Column (nullable = false, length= 100)
    private String course;

  @Email(message = "Email must be a valid address")
  @Size(max=150, message= "email must be at most 100 characters")

  @Column (unique = true, length= 150)
    private String email;



}

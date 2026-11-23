package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student " + id + " not found"));
    }

    public List<Student> search(String course, String name) {
        if (course != null) {
            return repository.findByCourse(course);
        }
        if (name != null) {
            return repository.findByNameContainingIgnoreCase(name);
        }
        return repository.findAll();
    }

    public Student create(Student student) {
        if (student.getEmail() != null && repository.existsByEmail(student.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "A student with this email already exists");
        }
        student.setId(null);   // the database creates the id
        return repository.save(student);
    }

    public Student update(Long id, Student updated) {
        Student existing = findById(id);   // 404 if missing

        if (updated.getEmail() != null) {
            repository.findByEmail(updated.getEmail())
                    .filter(other -> !other.getId().equals(id))
                    .ifPresent(other -> {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT, "A student with this email already exists");
                    });
        }

        existing.setName(updated.getName());
        existing.setCourse(updated.getCourse());
        existing.setEmail(updated.getEmail());
        return repository.save(existing);
    }

    public void delete(Long id) {
        findById(id);   // 404 if missing
        repository.deleteById(id);
    }
}
package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public  StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw  new IllegalStateException("email taken");
        }
        System.out.println(student);
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
            boolean exists =    studentRepository.existsById(studentId);
            if(!exists){
                throw new IllegalStateException("student with id" + studentId + "does'not exists");
            }studentRepository.deleteById(studentId);
    };

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " does not exist"
                ));

        if (name != null && name.length()>0 && !Objects.equals(student.getName(), name)) {
            // Check if the new name is already taken by another student

            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            // Check if the new email is already taken by another student
            Optional<Student> studentWithNewEmail = studentRepository.findStudentByEmail(email);
            if (studentWithNewEmail.isPresent()) {
                throw new IllegalStateException("Email is already taken");
            }
            student.setEmail(email);
        }
    }



}

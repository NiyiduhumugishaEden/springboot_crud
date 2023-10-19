package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
     CommandLineRunner commandLineRunner(StudentRepository repository){
         return  args -> {
                Student eden= new Student(

                             "Eden",
                             LocalDate.of(2000, Month.JUNE , 5),
                             "niyeden250@gmail.com"

                     );

             Student maritha= new Student(

                     "Eden",
                     LocalDate.of(2000, Month.JUNE , 5),
                     "niyeden250@gmail.com"

             );

             Student Ritah= new Student(
                     1L,
                     "Eden",
                     LocalDate.of(2000, Month.JUNE , 5),
                     "niyeden250@gmail.com"

             );
             repository.saveAll(
                     List.of(eden,maritha)
             );
         };
     }
}

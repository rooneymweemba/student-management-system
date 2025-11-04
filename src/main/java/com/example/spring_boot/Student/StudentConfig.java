package com.example.spring_boot.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.CommandLinePropertySource;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    /* @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student rupert = new Student( "rupert",
                    "1","DONE",
                    LocalDate.now().toString(),
                    LocalDate.now().toString()
            );

            Student mickey = new Student("mickey",
                    "2",
                    "DONE",
                    LocalDate.now().toString(),
                    LocalDate.now().toString()
            );
            repository.saveAll(
                    List.of(rupert, mickey)
            );
        };
    } */

}

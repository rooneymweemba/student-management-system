package com.example.spring_boot.Student.config;

import org.springframework.context.annotation.Configuration;

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

            );
            repository.saveAll(
                    List.of(rupert, mickey)
            );
        };
    } */

}

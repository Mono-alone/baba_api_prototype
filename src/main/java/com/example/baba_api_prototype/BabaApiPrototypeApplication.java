package com.example.baba_api_prototype;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BabaApiPrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabaApiPrototypeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BabaRepository babaRepo) {
        return args -> {
            var baba = new Baba("Baba", "Babova",
                    "https://s3-alpha.figma.com/hub/file/948140848/1f4d8ea7-e9d9-48b7-b70c-819482fb10fb-cover.png",
                    null);
            baba.addVote(new BabaVote(10, 10));
            babaRepo.save(baba);
        };
    }
}

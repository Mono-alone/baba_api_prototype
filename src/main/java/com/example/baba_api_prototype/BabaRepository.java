package com.example.baba_api_prototype;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BabaRepository extends JpaRepository<Baba, UUID> {
}

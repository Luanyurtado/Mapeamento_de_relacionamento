package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Drogaria;

public interface DrogariaRepository extends JpaRepository<Drogaria, Long> {

}
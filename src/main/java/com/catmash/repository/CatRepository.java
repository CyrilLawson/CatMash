package com.catmash.repository;

import com.catmash.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, String> {

    Optional<Cat> findById(String id);

    List<Cat> findAll();
}

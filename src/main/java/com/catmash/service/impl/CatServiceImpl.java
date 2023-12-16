package com.catmash.service.impl;

import com.catmash.entity.Cat;
import com.catmash.exception.EntityNotFoundException;
import com.catmash.repository.CatRepository;
import com.catmash.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CarService {
    private final CatRepository repository;

    @Override
    public Cat voteForCatWithId(String id) {
        Optional<Cat> catentity = repository.findById(id);
        catentity.ifPresentOrElse(t -> t.setScore(t.getScore() + 1),
                () -> new EntityNotFoundException(Cat.class.getSimpleName(), id));

        return catentity.get();
    }

    @Override
    public List<Cat> listAllCats() {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"score"));
    }
}

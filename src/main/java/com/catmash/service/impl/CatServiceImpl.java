package com.catmash.service.impl;

import com.catmash.entity.Cat;
import com.catmash.repository.CatRepository;
import com.catmash.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CarService {

    private final CatRepository repository;

    @Override
    public Cat voteForCatWithId(String id) {
        /*repository.findById(id)
                .ifPresent(t -> {
                    t.setScore(t.getScore() + 1);
                    var cat = repository.save(t);
                    return cat;
                });*/
        return null;
    }

    @Override
    public List<Cat> listAllCats() {
        return null;
    }
}

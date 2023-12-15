package com.catmash.service;

import com.catmash.entity.Cat;

import java.util.List;

public interface CarService {

    Cat voteForCatWithId(String id);

    List<Cat> listAllCats();
}

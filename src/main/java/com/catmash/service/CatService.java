package com.catmash.service;

import com.catmash.entity.Cat;

import java.util.List;

public interface CatService {

    Cat voteForCatWithId(String id);

    List<Cat> listTop5CuttestCats();
}

package com.catmash.controllers;

import com.catmash.mapper.CatMapper;
import com.catmash.model.Cat;
import com.catmash.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class CatController {

    private final CarService catService;

    private final CatMapper catMapper;

    @PutMapping("/cats/{id}")
    public ResponseEntity<Cat> voteForCatWithId(@PathVariable String id){
        var cat = catService.voteForCatWithId(id);
        return ResponseEntity.ok(catMapper.mapToDto(cat));
    }

    @PutMapping("/cats")
    public ResponseEntity<List<Cat>> listCats(){
        var list = catMapper.map(catService.listAllCats());
        return ResponseEntity.ok(list);
    }
}

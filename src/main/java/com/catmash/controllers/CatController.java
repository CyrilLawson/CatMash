package com.catmash.controllers;

import com.catmash.mapper.CatMapper;
import com.catmash.model.Cat;
import com.catmash.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Resource to vote for a cat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created: the book is successfully inserted"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Not Modified: the book is unsuccessfully inserted") })
    public ResponseEntity<Cat> voteForCatWithId(@PathVariable String id){
        var cat = catService.voteForCatWithId(id);
        return ResponseEntity.ok(catMapper.mapToDto(cat));
    }

    @GetMapping("/cats")
    @ApiOperation(value = "Resource to list all the cats with their score")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List returned successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    public ResponseEntity<List<Cat>> listCats(){
        var list = catMapper.map(catService.listAllCats());
        return ResponseEntity.ok(list);
    }
}

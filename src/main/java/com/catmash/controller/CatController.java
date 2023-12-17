package com.catmash.controller;

import com.catmash.mapper.CatMapper;
import com.catmash.model.Cat;
import com.catmash.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    private final CatMapper catMapper;

    @Operation(summary = "Vote for a cat", description = "Resource to vote for a cat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vote accepted"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PutMapping("/cats/{id}")
    public ResponseEntity<Cat> voteForCatWithId(@PathVariable(value = "id")  String id, @RequestBody String idToModify){
        var cat = catService.voteForCatWithId(id);
        return ResponseEntity.ok(catMapper.mapToDto(cat));
    }

    @Operation(summary = "List the top 5 cutest cats", description = "List the top 5 cutest cats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List returned successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/cats")
    public ResponseEntity<List<Cat>> listCats(){
        var list = catMapper.map(catService.listTop5CuttestCats());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "List all cats", description = "List all cats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List returned successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/cats/all")
    public ResponseEntity<List<Cat>> list(){
        var list = catMapper.map(catService.list());
        return ResponseEntity.ok(list);
    }
}

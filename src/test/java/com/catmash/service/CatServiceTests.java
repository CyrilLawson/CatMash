package com.catmash.service;

import com.catmash.entity.Cat;
import com.catmash.repository.CatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CatServiceTests {

    @Autowired
    private CatService catService;

    @MockBean
    private CatRepository repository;

    @Test
    void shouldVoteForCat(){

        //Given
        BDDMockito.given(repository.findById(Mockito.any(String.class)))
                .willReturn(Optional.of(new Cat("ID", "URL", 1)));

        //When
        Cat returnedCat = catService.voteForCatWithId("ID");

        //Then
        Assertions.assertEquals("ID", returnedCat.getId());
        Assertions.assertEquals("URL", returnedCat.getUrl());
        Assertions.assertEquals(2, returnedCat.getScore());
    }

    @Test
    void shouldListTop5CutestCats(){
        //Given
        BDDMockito.given(repository.findAll(Sort.by(Sort.Direction.DESC,"score")))
                .willReturn(List.of(
                        new Cat("ID1", "URL1", 1),
                        new Cat("ID2", "URL2", 2),
                        new Cat("ID3", "URL3", 3),
                        new Cat("ID4", "URL4", 4),
                        new Cat("ID5", "URL5", 5)));

        //When
        List<Cat> returnedCats = catService.listTop5CuttestCats();

        //Then
        Assertions.assertEquals(5, returnedCats.size());
    }
}

package com.catmash.controller;

import com.catmash.entity.Cat;
import com.catmash.mapper.CatMapper;
import com.catmash.service.CatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CatControllerTests {

    static final String URL = "/api/v1/cats/";
    private MockMvc mockMvc;

    @MockBean
    CatService catService;

    @MockBean
    CatMapper catMapper;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatController controller = new CatController(catService, catMapper);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldVoteForACat() throws Exception {

        BDDMockito.given(catService.voteForCatWithId(Mockito.any(String.class)))
                .willReturn(new Cat("ID", "URL", 2));

        BDDMockito.given(catMapper.mapToDto(Mockito.any(Cat.class)))
                .willReturn(new com.catmash.model.Cat("ID", "URL", 2));

        mockMvc.perform(put("/api/v1/cats/{id}", "ID")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Cat("ID", "URL", 2))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ID"))
                .andExpect(jsonPath("$.url").value("URL"))
                .andExpect(jsonPath("$.score").value(2));
    }

    @Test
    void shouldListTopFiveCutestCats() throws Exception {

        BDDMockito.given(catMapper.map(Mockito.any(List.class)))
                .willReturn(List.of(new com.catmash.model.Cat("ID1", "URL1", 1),
                        new com.catmash.model.Cat("ID2", "URL2", 2),
                        new com.catmash.model.Cat("ID3", "URL3", 3),
                        new com.catmash.model.Cat("ID4", "URL4", 4),
                        new com.catmash.model.Cat("ID5", "URL5", 5)));

        BDDMockito.given(catService.listTop5CuttestCats())
                .willReturn(
                        List.of(new Cat("ID1", "URL1", 1),
                        new Cat("ID2", "URL2", 2),
                        new Cat("ID3", "URL3", 3),
                        new Cat("ID4", "URL4", 4),
                        new Cat("ID5", "URL5", 5))
                        );

        mockMvc.perform(get("/api/v1/cats")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("ID1"))
                .andExpect(jsonPath("$[0].url").value("URL1"))
                .andExpect(jsonPath("$[0].score").value(1))
                .andExpect(jsonPath("$[1].id").value("ID2"))
                .andExpect(jsonPath("$[1].url").value("URL2"))
                .andExpect(jsonPath("$[1].score").value(2))
                .andExpect(jsonPath("$[2].id").value("ID3"))
                .andExpect(jsonPath("$[2].url").value("URL3"))
                .andExpect(jsonPath("$[2].score").value(3))
                .andExpect(jsonPath("$[3].id").value("ID4"))
                .andExpect(jsonPath("$[3].url").value("URL4"))
                .andExpect(jsonPath("$[3].score").value(4))
                .andExpect(jsonPath("$[4].id").value("ID5"))
                .andExpect(jsonPath("$[4].url").value("URL5"))
                .andExpect(jsonPath("$[4].score").value(5));
    }
}

package com.catmash.service.impl;

import com.catmash.entity.Cat;
import com.catmash.exception.EntityNotFoundException;
import com.catmash.repository.CatRepository;
import com.catmash.service.CatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    @Value("classpath:cats.json")
    Resource resourceFile;
    private final CatRepository repository;

    private ObjectMapper objectMapper;

    @PostConstruct
    public void populateDatabase() throws IOException {
        if (repository.findAll().isEmpty()) {
            this.objectMapper = new ObjectMapper();
            InputStream is = new FileInputStream(resourceFile.getFile());
            String jsonTxt = IOUtils.toString(is);

            JSONObject json = new JSONObject(jsonTxt);
            JSONArray array = (JSONArray) json.get("images");
            for (int i = 0; i < array.length(); i++) {
                repository.save(new Cat(array.getJSONObject(i).getString("id"),
                        array.getJSONObject(i).getString("url"), 0));
            }
        }
    }

    @Override
    public Cat voteForCatWithId(String id) {
        Optional<Cat> catentity = repository.findById(id);
        catentity.ifPresentOrElse(t -> t.setScore(t.getScore() + 1),
                () -> new EntityNotFoundException(Cat.class.getSimpleName(), id));

        return catentity.get();
    }

    @Override
    public List<Cat> listTop5CuttestCats() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "score"))
                .stream().limit(5).toList();
    }
}

package com.catmash.mapper;

import com.catmash.model.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CatMapper {

    Cat mapToDto(com.catmash.entity.Cat cat);

    List<Cat> map(List<com.catmash.entity.Cat> cats);
}

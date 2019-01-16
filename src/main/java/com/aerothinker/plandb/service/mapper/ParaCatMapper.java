package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.ParaCatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParaCat and its DTO ParaCatDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface ParaCatMapper extends EntityMapper<ParaCatDTO, ParaCat> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    ParaCatDTO toDto(ParaCat paraCat);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    ParaCat toEntity(ParaCatDTO paraCatDTO);

    default ParaCat fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParaCat paraCat = new ParaCat();
        paraCat.setId(id);
        return paraCat;
    }
}

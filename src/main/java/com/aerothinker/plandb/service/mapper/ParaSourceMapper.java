package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.ParaSourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParaSource and its DTO ParaSourceDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface ParaSourceMapper extends EntityMapper<ParaSourceDTO, ParaSource> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    ParaSourceDTO toDto(ParaSource paraSource);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    ParaSource toEntity(ParaSourceDTO paraSourceDTO);

    default ParaSource fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParaSource paraSource = new ParaSource();
        paraSource.setId(id);
        return paraSource;
    }
}

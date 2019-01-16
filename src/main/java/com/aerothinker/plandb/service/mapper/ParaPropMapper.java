package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.ParaPropDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParaProp and its DTO ParaPropDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface ParaPropMapper extends EntityMapper<ParaPropDTO, ParaProp> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    ParaPropDTO toDto(ParaProp paraProp);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    ParaProp toEntity(ParaPropDTO paraPropDTO);

    default ParaProp fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParaProp paraProp = new ParaProp();
        paraProp.setId(id);
        return paraProp;
    }
}

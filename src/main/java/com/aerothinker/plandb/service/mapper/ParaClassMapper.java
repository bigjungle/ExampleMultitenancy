package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.ParaClassDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParaClass and its DTO ParaClassDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface ParaClassMapper extends EntityMapper<ParaClassDTO, ParaClass> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    ParaClassDTO toDto(ParaClass paraClass);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    ParaClass toEntity(ParaClassDTO paraClassDTO);

    default ParaClass fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParaClass paraClass = new ParaClass();
        paraClass.setId(id);
        return paraClass;
    }
}

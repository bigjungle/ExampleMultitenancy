package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.ParaTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParaType and its DTO ParaTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface ParaTypeMapper extends EntityMapper<ParaTypeDTO, ParaType> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    ParaTypeDTO toDto(ParaType paraType);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    ParaType toEntity(ParaTypeDTO paraTypeDTO);

    default ParaType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParaType paraType = new ParaType();
        paraType.setId(id);
        return paraType;
    }
}

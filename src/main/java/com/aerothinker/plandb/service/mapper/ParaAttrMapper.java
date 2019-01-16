package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.ParaAttrDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParaAttr and its DTO ParaAttrDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface ParaAttrMapper extends EntityMapper<ParaAttrDTO, ParaAttr> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    ParaAttrDTO toDto(ParaAttr paraAttr);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    ParaAttr toEntity(ParaAttrDTO paraAttrDTO);

    default ParaAttr fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParaAttr paraAttr = new ParaAttr();
        paraAttr.setId(id);
        return paraAttr;
    }
}

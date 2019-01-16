package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.RmsDepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RmsDep and its DTO RmsDepDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface RmsDepMapper extends EntityMapper<RmsDepDTO, RmsDep> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.depName", target = "parentDepName")
    RmsDepDTO toDto(RmsDep rmsDep);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    RmsDep toEntity(RmsDepDTO rmsDepDTO);

    default RmsDep fromId(Long id) {
        if (id == null) {
            return null;
        }
        RmsDep rmsDep = new RmsDep();
        rmsDep.setId(id);
        return rmsDep;
    }
}

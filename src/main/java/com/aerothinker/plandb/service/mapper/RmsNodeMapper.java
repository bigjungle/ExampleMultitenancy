package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.RmsNodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RmsNode and its DTO RmsNodeDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface RmsNodeMapper extends EntityMapper<RmsNodeDTO, RmsNode> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    RmsNodeDTO toDto(RmsNode rmsNode);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "rmsRoles", ignore = true)
    RmsNode toEntity(RmsNodeDTO rmsNodeDTO);

    default RmsNode fromId(Long id) {
        if (id == null) {
            return null;
        }
        RmsNode rmsNode = new RmsNode();
        rmsNode.setId(id);
        return rmsNode;
    }
}

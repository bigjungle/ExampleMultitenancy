package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.RmsRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RmsRole and its DTO RmsRoleDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, RmsNodeMapper.class})
public interface RmsRoleMapper extends EntityMapper<RmsRoleDTO, RmsRole> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    RmsRoleDTO toDto(RmsRole rmsRole);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "rmsUsers", ignore = true)
    RmsRole toEntity(RmsRoleDTO rmsRoleDTO);

    default RmsRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        RmsRole rmsRole = new RmsRole();
        rmsRole.setId(id);
        return rmsRole;
    }
}

package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.RmsUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RmsUser and its DTO RmsUserDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsRoleMapper.class})
public interface RmsUserMapper extends EntityMapper<RmsUserDTO, RmsUser> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    RmsUserDTO toDto(RmsUser rmsUser);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    RmsUser toEntity(RmsUserDTO rmsUserDTO);

    default RmsUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        RmsUser rmsUser = new RmsUser();
        rmsUser.setId(id);
        return rmsUser;
    }
}

package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.RmsPersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RmsPerson and its DTO RmsPersonDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface RmsPersonMapper extends EntityMapper<RmsPersonDTO, RmsPerson> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    RmsPersonDTO toDto(RmsPerson rmsPerson);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    RmsPerson toEntity(RmsPersonDTO rmsPersonDTO);

    default RmsPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        RmsPerson rmsPerson = new RmsPerson();
        rmsPerson.setId(id);
        return rmsPerson;
    }
}

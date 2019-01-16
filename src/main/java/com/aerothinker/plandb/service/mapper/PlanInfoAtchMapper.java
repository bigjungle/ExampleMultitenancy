package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoAtchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoAtch and its DTO PlanInfoAtchDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, PlanInfoMapper.class})
public interface PlanInfoAtchMapper extends EntityMapper<PlanInfoAtchDTO, PlanInfoAtch> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "planInfo.id", target = "planInfoId")
    @Mapping(source = "planInfo.name", target = "planInfoName")
    PlanInfoAtchDTO toDto(PlanInfoAtch planInfoAtch);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "planInfoId", target = "planInfo")
    PlanInfoAtch toEntity(PlanInfoAtchDTO planInfoAtchDTO);

    default PlanInfoAtch fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoAtch planInfoAtch = new PlanInfoAtch();
        planInfoAtch.setId(id);
        return planInfoAtch;
    }
}

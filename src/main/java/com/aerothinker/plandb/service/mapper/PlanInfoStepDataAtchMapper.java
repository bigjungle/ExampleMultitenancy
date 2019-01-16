package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoStepDataAtch and its DTO PlanInfoStepDataAtchDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, PlanInfoStepAtchMapper.class, PlanInfoStepDataMapper.class})
public interface PlanInfoStepDataAtchMapper extends EntityMapper<PlanInfoStepDataAtchDTO, PlanInfoStepDataAtch> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "planInfoStepAtch.id", target = "planInfoStepAtchId")
    @Mapping(source = "planInfoStepAtch.name", target = "planInfoStepAtchName")
    @Mapping(source = "planInfoStepData.id", target = "planInfoStepDataId")
    @Mapping(source = "planInfoStepData.name", target = "planInfoStepDataName")
    PlanInfoStepDataAtchDTO toDto(PlanInfoStepDataAtch planInfoStepDataAtch);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "planInfoStepAtchId", target = "planInfoStepAtch")
    @Mapping(source = "planInfoStepDataId", target = "planInfoStepData")
    PlanInfoStepDataAtch toEntity(PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO);

    default PlanInfoStepDataAtch fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoStepDataAtch planInfoStepDataAtch = new PlanInfoStepDataAtch();
        planInfoStepDataAtch.setId(id);
        return planInfoStepDataAtch;
    }
}

package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoStepDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoStep and its DTO PlanInfoStepDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, RmsDepMapper.class, PlanInfoMapper.class})
public interface PlanInfoStepMapper extends EntityMapper<PlanInfoStepDTO, PlanInfoStep> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "createdDepBy.id", target = "createdDepById")
    @Mapping(source = "createdDepBy.depName", target = "createdDepByDepName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "modifiedDepBy.id", target = "modifiedDepById")
    @Mapping(source = "modifiedDepBy.depName", target = "modifiedDepByDepName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "planInfo.id", target = "planInfoId")
    @Mapping(source = "planInfo.name", target = "planInfoName")
    PlanInfoStepDTO toDto(PlanInfoStep planInfoStep);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "createdDepById", target = "createdDepBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "modifiedDepById", target = "modifiedDepBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "planInfoId", target = "planInfo")
    PlanInfoStep toEntity(PlanInfoStepDTO planInfoStepDTO);

    default PlanInfoStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoStep planInfoStep = new PlanInfoStep();
        planInfoStep.setId(id);
        return planInfoStep;
    }
}

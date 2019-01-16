package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoStepData and its DTO PlanInfoStepDataDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, RmsDepMapper.class, PlanInfoStepMapper.class, PlanInfoDataMapper.class})
public interface PlanInfoStepDataMapper extends EntityMapper<PlanInfoStepDataDTO, PlanInfoStepData> {

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
    @Mapping(source = "verifiedDepBy.id", target = "verifiedDepById")
    @Mapping(source = "verifiedDepBy.depName", target = "verifiedDepByDepName")
    @Mapping(source = "planInfoStep.id", target = "planInfoStepId")
    @Mapping(source = "planInfoStep.name", target = "planInfoStepName")
    @Mapping(source = "planInfoData.id", target = "planInfoDataId")
    @Mapping(source = "planInfoData.name", target = "planInfoDataName")
    PlanInfoStepDataDTO toDto(PlanInfoStepData planInfoStepData);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "createdDepById", target = "createdDepBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "modifiedDepById", target = "modifiedDepBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "verifiedDepById", target = "verifiedDepBy")
    @Mapping(source = "planInfoStepId", target = "planInfoStep")
    @Mapping(source = "planInfoDataId", target = "planInfoData")
    PlanInfoStepData toEntity(PlanInfoStepDataDTO planInfoStepDataDTO);

    default PlanInfoStepData fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoStepData planInfoStepData = new PlanInfoStepData();
        planInfoStepData.setId(id);
        return planInfoStepData;
    }
}

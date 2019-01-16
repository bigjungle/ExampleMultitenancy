package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoStepDataHis and its DTO PlanInfoStepDataHisDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, RmsDepMapper.class, PlanInfoStepMapper.class, PlanInfoDataHisMapper.class})
public interface PlanInfoStepDataHisMapper extends EntityMapper<PlanInfoStepDataHisDTO, PlanInfoStepDataHis> {

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
    @Mapping(source = "planInfoDataHis.id", target = "planInfoDataHisId")
    @Mapping(source = "planInfoDataHis.name", target = "planInfoDataHisName")
    PlanInfoStepDataHisDTO toDto(PlanInfoStepDataHis planInfoStepDataHis);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "createdDepById", target = "createdDepBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "modifiedDepById", target = "modifiedDepBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "verifiedDepById", target = "verifiedDepBy")
    @Mapping(source = "planInfoStepId", target = "planInfoStep")
    @Mapping(source = "planInfoDataHisId", target = "planInfoDataHis")
    PlanInfoStepDataHis toEntity(PlanInfoStepDataHisDTO planInfoStepDataHisDTO);

    default PlanInfoStepDataHis fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoStepDataHis planInfoStepDataHis = new PlanInfoStepDataHis();
        planInfoStepDataHis.setId(id);
        return planInfoStepDataHis;
    }
}

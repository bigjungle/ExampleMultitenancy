package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoStepDataAtchHis and its DTO PlanInfoStepDataAtchHisDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, PlanInfoStepAtchMapper.class, PlanInfoStepDataHisMapper.class})
public interface PlanInfoStepDataAtchHisMapper extends EntityMapper<PlanInfoStepDataAtchHisDTO, PlanInfoStepDataAtchHis> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "planInfoStepAtch.id", target = "planInfoStepAtchId")
    @Mapping(source = "planInfoStepAtch.name", target = "planInfoStepAtchName")
    @Mapping(source = "planInfoStepDataHis.id", target = "planInfoStepDataHisId")
    @Mapping(source = "planInfoStepDataHis.name", target = "planInfoStepDataHisName")
    PlanInfoStepDataAtchHisDTO toDto(PlanInfoStepDataAtchHis planInfoStepDataAtchHis);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "planInfoStepAtchId", target = "planInfoStepAtch")
    @Mapping(source = "planInfoStepDataHisId", target = "planInfoStepDataHis")
    PlanInfoStepDataAtchHis toEntity(PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO);

    default PlanInfoStepDataAtchHis fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoStepDataAtchHis planInfoStepDataAtchHis = new PlanInfoStepDataAtchHis();
        planInfoStepDataAtchHis.setId(id);
        return planInfoStepDataAtchHis;
    }
}

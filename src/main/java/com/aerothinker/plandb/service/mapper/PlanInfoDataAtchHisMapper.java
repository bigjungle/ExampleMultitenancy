package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoDataAtchHis and its DTO PlanInfoDataAtchHisDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, PlanInfoAtchMapper.class, PlanInfoDataHisMapper.class})
public interface PlanInfoDataAtchHisMapper extends EntityMapper<PlanInfoDataAtchHisDTO, PlanInfoDataAtchHis> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "planInfoAtch.id", target = "planInfoAtchId")
    @Mapping(source = "planInfoAtch.name", target = "planInfoAtchName")
    @Mapping(source = "planInfoDataHis.id", target = "planInfoDataHisId")
    @Mapping(source = "planInfoDataHis.name", target = "planInfoDataHisName")
    PlanInfoDataAtchHisDTO toDto(PlanInfoDataAtchHis planInfoDataAtchHis);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "planInfoAtchId", target = "planInfoAtch")
    @Mapping(source = "planInfoDataHisId", target = "planInfoDataHis")
    PlanInfoDataAtchHis toEntity(PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO);

    default PlanInfoDataAtchHis fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoDataAtchHis planInfoDataAtchHis = new PlanInfoDataAtchHis();
        planInfoDataAtchHis.setId(id);
        return planInfoDataAtchHis;
    }
}

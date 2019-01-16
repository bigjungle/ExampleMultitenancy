package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoDataAtch and its DTO PlanInfoDataAtchDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class, PlanInfoAtchMapper.class, PlanInfoDataMapper.class})
public interface PlanInfoDataAtchMapper extends EntityMapper<PlanInfoDataAtchDTO, PlanInfoDataAtch> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "planInfoAtch.id", target = "planInfoAtchId")
    @Mapping(source = "planInfoAtch.name", target = "planInfoAtchName")
    @Mapping(source = "planInfoData.id", target = "planInfoDataId")
    @Mapping(source = "planInfoData.name", target = "planInfoDataName")
    PlanInfoDataAtchDTO toDto(PlanInfoDataAtch planInfoDataAtch);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "planInfoAtchId", target = "planInfoAtch")
    @Mapping(source = "planInfoDataId", target = "planInfoData")
    PlanInfoDataAtch toEntity(PlanInfoDataAtchDTO planInfoDataAtchDTO);

    default PlanInfoDataAtch fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoDataAtch planInfoDataAtch = new PlanInfoDataAtch();
        planInfoDataAtch.setId(id);
        return planInfoDataAtch;
    }
}

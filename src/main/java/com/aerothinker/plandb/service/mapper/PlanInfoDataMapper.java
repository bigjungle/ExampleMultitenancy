package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.PlanInfoDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlanInfoData and its DTO PlanInfoDataDTO.
 */
@Mapper(componentModel = "spring", uses = {VerifyRecMapper.class, ParaTypeMapper.class, ParaClassMapper.class, ParaCatMapper.class, ParaStateMapper.class, ParaSourceMapper.class, ParaAttrMapper.class, ParaPropMapper.class, RmsUserMapper.class, RmsDepMapper.class, PlanInfoMapper.class})
public interface PlanInfoDataMapper extends EntityMapper<PlanInfoDataDTO, PlanInfoData> {

    @Mapping(source = "verifyRec.id", target = "verifyRecId")
    @Mapping(source = "verifyRec.name", target = "verifyRecName")
    @Mapping(source = "paraType.id", target = "paraTypeId")
    @Mapping(source = "paraType.name", target = "paraTypeName")
    @Mapping(source = "paraClass.id", target = "paraClassId")
    @Mapping(source = "paraClass.name", target = "paraClassName")
    @Mapping(source = "paraCat.id", target = "paraCatId")
    @Mapping(source = "paraCat.name", target = "paraCatName")
    @Mapping(source = "paraState.id", target = "paraStateId")
    @Mapping(source = "paraState.name", target = "paraStateName")
    @Mapping(source = "paraSource.id", target = "paraSourceId")
    @Mapping(source = "paraSource.name", target = "paraSourceName")
    @Mapping(source = "paraAttr.id", target = "paraAttrId")
    @Mapping(source = "paraAttr.name", target = "paraAttrName")
    @Mapping(source = "paraProp.id", target = "paraPropId")
    @Mapping(source = "paraProp.name", target = "paraPropName")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "createdDepBy.id", target = "createdDepById")
    @Mapping(source = "createdDepBy.depName", target = "createdDepByDepName")
    @Mapping(source = "ownerBy.id", target = "ownerById")
    @Mapping(source = "ownerBy.userName", target = "ownerByUserName")
    @Mapping(source = "ownerDepBy.id", target = "ownerDepById")
    @Mapping(source = "ownerDepBy.depName", target = "ownerDepByDepName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    @Mapping(source = "modifiedDepBy.id", target = "modifiedDepById")
    @Mapping(source = "modifiedDepBy.depName", target = "modifiedDepByDepName")
    @Mapping(source = "verifiedBy.id", target = "verifiedById")
    @Mapping(source = "verifiedBy.userName", target = "verifiedByUserName")
    @Mapping(source = "verifiedDepBy.id", target = "verifiedDepById")
    @Mapping(source = "verifiedDepBy.depName", target = "verifiedDepByDepName")
    @Mapping(source = "publishedBy.id", target = "publishedById")
    @Mapping(source = "publishedBy.userName", target = "publishedByUserName")
    @Mapping(source = "publishedDepBy.id", target = "publishedDepById")
    @Mapping(source = "publishedDepBy.depName", target = "publishedDepByDepName")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    @Mapping(source = "planInfo.id", target = "planInfoId")
    @Mapping(source = "planInfo.name", target = "planInfoName")
    PlanInfoDataDTO toDto(PlanInfoData planInfoData);

    @Mapping(source = "verifyRecId", target = "verifyRec")
    @Mapping(source = "paraTypeId", target = "paraType")
    @Mapping(source = "paraClassId", target = "paraClass")
    @Mapping(source = "paraCatId", target = "paraCat")
    @Mapping(source = "paraStateId", target = "paraState")
    @Mapping(source = "paraSourceId", target = "paraSource")
    @Mapping(source = "paraAttrId", target = "paraAttr")
    @Mapping(source = "paraPropId", target = "paraProp")
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "createdDepById", target = "createdDepBy")
    @Mapping(source = "ownerById", target = "ownerBy")
    @Mapping(source = "ownerDepById", target = "ownerDepBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    @Mapping(source = "modifiedDepById", target = "modifiedDepBy")
    @Mapping(source = "verifiedById", target = "verifiedBy")
    @Mapping(source = "verifiedDepById", target = "verifiedDepBy")
    @Mapping(source = "publishedById", target = "publishedBy")
    @Mapping(source = "publishedDepById", target = "publishedDepBy")
    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "planInfoId", target = "planInfo")
    PlanInfoData toEntity(PlanInfoDataDTO planInfoDataDTO);

    default PlanInfoData fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanInfoData planInfoData = new PlanInfoData();
        planInfoData.setId(id);
        return planInfoData;
    }
}

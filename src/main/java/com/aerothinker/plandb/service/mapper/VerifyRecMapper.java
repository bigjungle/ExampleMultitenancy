package com.aerothinker.plandb.service.mapper;

import com.aerothinker.plandb.domain.*;
import com.aerothinker.plandb.service.dto.VerifyRecDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VerifyRec and its DTO VerifyRecDTO.
 */
@Mapper(componentModel = "spring", uses = {RmsUserMapper.class})
public interface VerifyRecMapper extends EntityMapper<VerifyRecDTO, VerifyRec> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.userName", target = "createdByUserName")
    @Mapping(source = "modifiedBy.id", target = "modifiedById")
    @Mapping(source = "modifiedBy.userName", target = "modifiedByUserName")
    VerifyRecDTO toDto(VerifyRec verifyRec);

    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "modifiedById", target = "modifiedBy")
    VerifyRec toEntity(VerifyRecDTO verifyRecDTO);

    default VerifyRec fromId(Long id) {
        if (id == null) {
            return null;
        }
        VerifyRec verifyRec = new VerifyRec();
        verifyRec.setId(id);
        return verifyRec;
    }
}

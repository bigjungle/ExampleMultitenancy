package com.aerothinker.plandb.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.aerothinker.plandb.domain.enumeration.ValidType;

/**
 * A DTO for the ParaAttr entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 256)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParaAttrDTO paraAttrDTO = (ParaAttrDTO) o;
        if (paraAttrDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paraAttrDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParaAttrDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

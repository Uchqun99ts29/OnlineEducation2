package com.education.dto;

import com.education.entity.Auditable;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericDto {

   private Long id;
    @Override
    public int hashCode() {
       return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Auditable other = (Auditable) obj;
        return Objects.equals(id,other.getId());
    }
}

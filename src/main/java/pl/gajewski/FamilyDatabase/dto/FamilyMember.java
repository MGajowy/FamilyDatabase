package pl.gajewski.FamilyDatabase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMember {
    private String familyName;
    private String givenName;
    private Integer age;
    private Long familyId;
}

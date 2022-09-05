package pl.gajewski.FamilyDatabase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyResponse {
    private String familyName;
    private Integer nrOfAdults;
    private Integer nrOfChildren;
    private Integer nrOfinfants;
}

package pl.gajewski.FamilyDatabase.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@Table(schema = ModelConstants.SCHEMA_FAMILY_DB, name = ModelConstants.TABLE_FAMILY_DB)

public class FamilyOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private Long id;

    @Column(name = ModelConstants.COLUMN_UUID)
    private String uuidFamily;

    @Column(name = ModelConstants.COLUMN_FAMILY_NAME, length = 255)
    private String familyName;

    @Column(name = ModelConstants.COLUMN_NR_OF_ADULTS, length = 36)
    private Integer nrOfAdults;

    @Column(name = ModelConstants.COLUMN_NR_OF_CHILDERN, length = 36)
    private Integer nrOfChildren;

    @Column(name = ModelConstants.COLUMN_NR_OF_INFANTS, length = 36)
    private Integer nrOfinfants;

//    @OneToMany(mappedBy = "familyOBId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @ToString.Exclude
//    List<FamilyMemberOB> familyMemberOBList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FamilyOB familyOB = (FamilyOB) o;
        return id != null && Objects.equals(id, familyOB.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

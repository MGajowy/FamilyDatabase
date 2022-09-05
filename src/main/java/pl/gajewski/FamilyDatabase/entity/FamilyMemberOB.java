package pl.gajewski.FamilyDatabase.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.ui.Model;
import pl.gajewski.FamilyDatabase.dto.FamilyMember;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(schema = ModelConstants.SCHEMA_FAMILY_MEMBER_DB, name = ModelConstants.TABLE_FAMILY_MEMBER_DB)
@RequiredArgsConstructor
public class FamilyMemberOB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelConstants.COLUMN_ID, length = 36)
    private Long id;

    @Column(name = ModelConstants.COLUMN_FAMILY_ID)
    private Long familyId;

    @Column(name = ModelConstants.COLUMN_FAMILY_NAME, length = 255)
    private String familyName;

    @Column(name = ModelConstants.COLUMN_GIVEN_NAME, length = 255)
    private String givenName;

    @Column (name = ModelConstants.COLUMN_AGE)
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FamilyMemberOB that = (FamilyMemberOB) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public FamilyMember obToDtoFamily(){
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFamilyName(getFamilyName());
        familyMember.setAge(getAge());
        familyMember.setGivenName(getGivenName());
        return familyMember;
    }
}

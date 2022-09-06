package pl.gajewski.FamilyDatabase.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.gajewski.FamilyDatabase.dto.Family;
import pl.gajewski.FamilyDatabase.dto.FamilyMember;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FamilyDataBaseServiceTest {
    @Mock
    EntityManager entityManager;
    @InjectMocks
    FamilyDataBaseService familyDataBaseService;

    @Test
    void shouldCreateFamily() {
        // given
        Family family = new Family(1L, "NOWAK", 1, 0, 0, "123");
        // when
        Long actual = familyDataBaseService.createFamily(family);
        // then
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(1L);

    }

    @Test
    void shouldcreateMemberFamily() {
        // given
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFamilyId(1L);
        familyMember.setFamilyName("KOWALSKI");
        familyMember.setGivenName("PIOTR");
        familyMember.setAge(22);
        // when
        Boolean actual = familyDataBaseService.createMemberFamily(familyMember);
        // then
        assertThat(actual).isTrue();
    }
}
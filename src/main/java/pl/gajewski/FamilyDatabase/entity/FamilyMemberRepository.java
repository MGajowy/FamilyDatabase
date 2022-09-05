package pl.gajewski.FamilyDatabase.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberRepository extends JpaRepository<FamilyMemberOB, Long> {
}

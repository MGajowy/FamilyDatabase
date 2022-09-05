package pl.gajewski.FamilyDatabase.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository  extends JpaRepository<FamilyOB, Long> {

}

package pl.gajewski.FamilyDatabase.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gajewski.FamilyDatabase.controller.constant.FamilyDataBaseConstant;
import pl.gajewski.FamilyDatabase.dto.Family;
import pl.gajewski.FamilyDatabase.dto.FamilyMember;
import pl.gajewski.FamilyDatabase.dto.FamilyResponse;
import pl.gajewski.FamilyDatabase.entity.FamilyOB;
import pl.gajewski.FamilyDatabase.entity.FamilyMemberOB;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FamilyDataBaseService {
    private final EntityManager entityManager;

    @Autowired
    public FamilyDataBaseService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Long createFamily(Family family) {
        FamilyOB familyOB = new FamilyOB();
        try {
            BeanUtils.copyProperties(family, familyOB);
            entityManager.persist(familyOB);
            log.info("Zapis rodziny " + family.getFamilyName() + " przebiegł poprawnie.");
            return familyOB.getId();
        } catch (Exception e) {
            log.info(FamilyDataBaseConstant.FAIL_SAVE_FAMILY + family.getUuidFamily() + " " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public Boolean createMemberFamily(FamilyMember familyMember) {
        try {
            FamilyMemberOB familyMemberOB = new FamilyMemberOB();
            BeanUtils.copyProperties(familyMember, familyMemberOB);
            entityManager.persist(familyMemberOB);
            log.info("Zapis członka rodziny " + familyMember.getGivenName() + " przebiegł poprawnie.");
            return true;
        } catch (Exception e) {
            log.info(FamilyDataBaseConstant.FAIL_SAVE_FAMILY_MEMBER + e.getMessage());
            return false;
        }
    }

    public FamilyResponse searchFamily(Long id) {
        FamilyOB familyOB = entityManager.createQuery("from FamilyOB f WHERE f.id = :id", FamilyOB.class)
                .setParameter("id", id).getSingleResult();
        return FamilyResponse.builder()
                .familyName(familyOB.getFamilyName())
                .nrOfChildren(familyOB.getNrOfChildren())
                .nrOfAdults(familyOB.getNrOfAdults())
                .nrOfinfants(familyOB.getNrOfinfants())
                .build();
    }

    public FamilyMember[] serachMemberFamily(Long id) {
        List<FamilyMemberOB> familyMemberOBList = getMemberFamilyList(id);
        List<FamilyMember> resultList = familyMemberOBList.stream()
                .map(FamilyMemberOB::obToDtoFamily)
                .collect(Collectors.toList());
        return resultList.toArray(new FamilyMember[0]);
    }

    private List<FamilyMemberOB> getMemberFamilyList(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FamilyMemberOB> cq = cb.createQuery(FamilyMemberOB.class);
        Root<FamilyMemberOB> root = cq.from(FamilyMemberOB.class);
        ParameterExpression<Long> parameter = cb.parameter(Long.class);
        cq.where(cb.equal(root.get("familyId"), parameter));

        TypedQuery<FamilyMemberOB> query = entityManager.createQuery(cq);
        query.setParameter(parameter, id);
        return query.getResultList();
    }
}

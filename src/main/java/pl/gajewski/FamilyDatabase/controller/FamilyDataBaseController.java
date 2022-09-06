package pl.gajewski.FamilyDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajewski.FamilyDatabase.controller.constant.FamilyDataBaseConstant;
import pl.gajewski.FamilyDatabase.dto.Family;
import pl.gajewski.FamilyDatabase.dto.FamilyMember;
import pl.gajewski.FamilyDatabase.dto.FamilyResponse;
import pl.gajewski.FamilyDatabase.service.FamilyDataBaseService;


@RestController
@RequestMapping(FamilyDataBaseConstant.DB)
public class FamilyDataBaseController {

    private final FamilyDataBaseService familyDataBaseService;

    @Autowired
    public FamilyDataBaseController(FamilyDataBaseService familyDataBaseService) {
        this.familyDataBaseService = familyDataBaseService;
    }

    @PostMapping(FamilyDataBaseConstant.CREATE_FAMILY)
    public Long createFamily(@RequestBody Family family) {
        return familyDataBaseService.createFamily(family);
    }

    @PostMapping(FamilyDataBaseConstant.CREATE_MEMBER_FAMILY)
    public ResponseEntity<HttpStatus> createMemberFamily(@RequestBody FamilyMember familyMember) {
        Boolean result = familyDataBaseService.createMemberFamily(familyMember);
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(FamilyDataBaseConstant.SEATCH_FAMILY + "/{id}")
    public FamilyResponse searchFamily(@PathVariable("id") Long id) {
        return familyDataBaseService.searchFamily(id);
    }

    @GetMapping(FamilyDataBaseConstant.SEATCH_MEMBER_FAMILY + "/{id}")
    public FamilyMember[] searchMemberFamily(@PathVariable("id") Long id) {
        return familyDataBaseService.serachMemberFamily(id);
    }
}

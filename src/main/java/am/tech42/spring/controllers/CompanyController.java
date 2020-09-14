package am.tech42.spring.controllers;

import am.tech42.spring.dto.ReturnCompanyDto;
import am.tech42.spring.exception.UnknownUserException;
import am.tech42.spring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/logo/add")
    @PreAuthorize("hasAnyAuthority('logo:add')")
    public ResponseEntity<?> addLogo(@RequestParam("id") String id,
                                     @RequestParam("logo") MultipartFile logo) {
        companyService.saveLogo(id,logo);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('get:company')")
    public ResponseEntity<ReturnCompanyDto> getCompany (@PathVariable("id") String id){
        ReturnCompanyDto returnCompanyDto ;
        try {
            returnCompanyDto = companyService.getCompany(id);
        } catch (UnknownUserException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(returnCompanyDto,HttpStatus.OK);
    }



}

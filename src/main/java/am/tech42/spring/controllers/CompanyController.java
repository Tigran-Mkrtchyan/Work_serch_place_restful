package am.tech42.spring.controllers;

import am.tech42.spring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/logo/add")
    public ResponseEntity<?> addLogo(@RequestParam("id") String id,
                                     @RequestParam("logo") MultipartFile logo) {
        companyService.saveLogo(id,logo);
        return  new ResponseEntity<>(HttpStatus.OK);

    }


}

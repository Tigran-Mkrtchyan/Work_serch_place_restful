package am.tech42.spring.controllers;


import am.tech42.spring.dto.ReturnEmployeeDto;
import am.tech42.spring.exception.UnknownUserException;
import am.tech42.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
     private EmployeeService employeeService;

    @GetMapping(value = "/cv/add")
    @PreAuthorize("hasAnyAuthority('cv:write')")
    public ResponseEntity<?> addCv(@RequestParam("id") String id,
                                   @RequestParam ("cv") MultipartFile file){
        employeeService.saveCv(id,file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('get:employee')")
    public ResponseEntity<ReturnEmployeeDto> getEmployee(@PathVariable String id){
        ReturnEmployeeDto employee;
        try {
            employee = employeeService.getEmployee(id);
        } catch (UnknownUserException e) {
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }
}

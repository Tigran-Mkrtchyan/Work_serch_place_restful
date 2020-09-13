package am.tech42.spring.controllers;


import am.tech42.spring.dto.CompanyDto;
import am.tech42.spring.dto.EmployeeDto;
import am.tech42.spring.dto.ReturnUserDto;
import am.tech42.spring.model.User;
import am.tech42.spring.service.CompanyService;
import am.tech42.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
        ReturnUserDto newUser = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping(value = "/company", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
        ReturnUserDto newUser = companyService.saveCompany(companyDto);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}

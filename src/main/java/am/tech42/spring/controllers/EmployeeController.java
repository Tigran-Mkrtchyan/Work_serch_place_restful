package am.tech42.spring.controllers;


import am.tech42.spring.dto.ReturnEmployeeDto;
import am.tech42.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
     private EmployeeService employeeService;

    @GetMapping(value = "/cv/add")
    public ResponseEntity<?> addCv(@RequestParam("id") String id,
                                   @RequestParam ("cv") MultipartFile file){
        employeeService.saveCv(id,file);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ReturnEmployeeDto> getEmployee(@RequestBody String id){
        ReturnEmployeeDto employee = employeeService.getEmployee(id);
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }
}

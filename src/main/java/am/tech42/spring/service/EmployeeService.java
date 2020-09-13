package am.tech42.spring.service;


import am.tech42.spring.dto.EmployeeDto;
import am.tech42.spring.dto.ReturnEmployeeDto;
import am.tech42.spring.dto.ReturnUserDto;
import am.tech42.spring.model.Employee;
import am.tech42.spring.model.User;
import am.tech42.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private  UserService userService;

    public ReturnUserDto saveEmployee(EmployeeDto employeeDto){
        User user = userService.createUser(employeeDto);
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setBirthday(Date.valueOf(employeeDto.getBirthday()));
        employeeRepository.save(employee);
        ReturnUserDto newUser = new ReturnUserDto();
        newUser.setUserId(user.getId());
        newUser.setName(employeeDto.getFirstName());
        newUser.setRole(user.getRole());
        return newUser;
    }

    public String saveCv(String id,MultipartFile cv) {
        String fileName = cv.getOriginalFilename();
        String cvPath = "C:\\Users\\Galust\\Desktop\\uploads\\cvs\\"+fileName;
        File cvFile = new File(cvPath);
        try {
            cv.transferTo(cvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Employee employee =  employeeRepository.findEmployeeByUserId(id).get();
        employee.setCvUrl(cvPath);
        employeeRepository.save(employee);

        return cvPath;
    }

    public ReturnEmployeeDto getEmployee(String id) {
        Employee employee = employeeRepository.findEmployeeByUserId(id).get();
        ReturnEmployeeDto employeeDto = new ReturnEmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setBirthday(employee.getBirthday().toString());
        employeeDto.evaluateAgeFromBirthday(employee.getBirthday());
        return employeeDto;

    }
}

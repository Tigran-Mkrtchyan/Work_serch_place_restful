package am.tech42.spring.service;

import am.tech42.spring.dto.*;
import am.tech42.spring.model.Company;
import am.tech42.spring.model.Employee;
import am.tech42.spring.model.User;
import am.tech42.spring.repository.CompanyRepository;
import am.tech42.spring.repository.EmployeeRepository;
import am.tech42.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public User createUser(EmployeeDto employeeDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(employeeDto.getEmail());
        user.setUsername(employeeDto.getEmail());
        user.setPassword(employeeDto.getPassword());
        user.setRole(Role.EMPLOYEE.name());
        return user;
    }
    public User createUser(CompanyDto companyDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(companyDto.getEmail());
        user.setUsername(companyDto.getEmail());
        user.setPassword(companyDto.getPassword());
        user.setRole(Role.COMPANY.name());
        return user;
    }

    public ReturnUserDto getUser(UserDto userDto){
        Optional<User> userOptional = userRepository.findUserByEmailAndPassword(userDto.getEmail(),userDto.getPassword());
        User user = userOptional.orElse(null);
        ReturnUserDto newUser = new ReturnUserDto();
        newUser.setUserId(user.getId());
        String role = user.getRole();
        newUser.setRole(role);
        if(role.equals(Role.COMPANY.name())){
            Company company = companyRepository.findCompanyByUserId(user.getId()).get();
            newUser.setName(company.getCompanyName());
        }else{
            Employee employee = employeeRepository.findEmployeeByUserId(user.getId()).get();
            newUser.setName(employee.getFirstName());
        }
        return newUser;

    }

}

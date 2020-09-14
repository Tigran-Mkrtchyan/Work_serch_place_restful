package am.tech42.spring.service;

import am.tech42.spring.dto.*;
import am.tech42.spring.exception.EmailNotExistsException;
import am.tech42.spring.exception.UnknownUserException;
import am.tech42.spring.model.Company;
import am.tech42.spring.model.Employee;
import am.tech42.spring.model.enums.Role;
import am.tech42.spring.model.User;
import am.tech42.spring.repository.CompanyRepository;
import am.tech42.spring.repository.EmployeeRepository;
import am.tech42.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(EmployeeDto employeeDto) throws EmailNotExistsException {
        User user = userRepository.findUserByEmail(employeeDto.getEmail()).orElse(null);
        if(user != null){
            throw new EmailNotExistsException(
                    "There is an account with that email adress: " + employeeDto.getEmail()
            );
        }
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(employeeDto.getEmail());
        user.setUsername(employeeDto.getEmail());
        user.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        user.setRole(Role.EMPLOYEE);
        return user;
    }
    public User createUser(CompanyDto companyDto) throws EmailNotExistsException {
        User user = userRepository.findUserByEmail(companyDto.getEmail()).orElse(null);
        if(user != null){
            throw new EmailNotExistsException(
                    "There is an account with that email adress: " + companyDto.getEmail()
            );
        }
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(companyDto.getEmail());
        user.setUsername(companyDto.getEmail());
        user.setPassword(passwordEncoder.encode(companyDto.getPassword()));
        user.setRole(Role.COMPANY);
        return user;
    }

    public ReturnUserDto getUser(UserDto userDto) throws UnknownUserException {
        User user = userRepository.findUserByEmail(userDto.getEmail()).orElseThrow(
                () -> new UnknownUserException("email or password is incorrect"));

        if (!passwordEncoder.matches(userDto.getPassword(),user.getPassword())){
            throw new UnknownUserException("email or password is incorrect");
        }
        ReturnUserDto newUser = new ReturnUserDto();
        newUser.setUserId(user.getId());
        String role = user.getRole().name();
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

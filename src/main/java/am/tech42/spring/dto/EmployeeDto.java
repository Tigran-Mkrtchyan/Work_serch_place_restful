package am.tech42.spring.dto;

import lombok.Data;


@Data
public class EmployeeDto {

    private  String email;

    private String password;

    private  String firstName;

    private String lastName;

    private String birthday;
}

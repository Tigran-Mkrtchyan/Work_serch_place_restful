package am.tech42.spring.dto;

import lombok.Data;

@Data
public class ReturnUserDto {
    private  String userId;
    private String name;
    private String role;
}

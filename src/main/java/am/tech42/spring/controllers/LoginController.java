package am.tech42.spring.controllers;

import am.tech42.spring.dto.ReturnUserDto;
import am.tech42.spring.dto.UserDto;
import am.tech42.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private   UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnUserDto> loginUser(@RequestBody UserDto userDto){
        ReturnUserDto user = userService.getUser(userDto);
            return new ResponseEntity<>(user,HttpStatus.OK);
    }


}

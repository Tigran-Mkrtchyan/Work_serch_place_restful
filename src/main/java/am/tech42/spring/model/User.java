package am.tech42.spring.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name ="users")
public class User  {

    @Id
    @Column(name ="id" )
    private String id ;
 
    @Column(name = "role")
    private String role;

    @Column(name = "username")
    private  String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private  String email;

}

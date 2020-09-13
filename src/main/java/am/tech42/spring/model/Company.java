package am.tech42.spring.model;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    @OneToMany(mappedBy = "company",cascade = {CascadeType.REMOVE})
//    private Set<Post> posts = new HashSet<>();

    @OneToOne(mappedBy = "company" ,cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Logo logo;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "company_name")
    private String companyName;

    public void addLogo (Logo logo){
        this.logo = logo;
        logo.setCompany(this);
    }

}

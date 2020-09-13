package am.tech42.spring.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "logos")
public class Logo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToOne
    @JoinColumn(name = "Company_id" ,referencedColumnName = "id")
    private Company company;

}

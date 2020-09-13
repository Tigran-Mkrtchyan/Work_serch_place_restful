package am.tech42.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name ="company_id", referencedColumnName = "id")
    private Company company;

    private String description;

    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "job_Type_id" ,referencedColumnName = "id")
    private JobType jobType;

    @ManyToMany
    @JoinTable(name = "post_level",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "level_id")}
    )
    private Set<Level> levels = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "skill_id" ,referencedColumnName = "id")
    private Skill skill;

}
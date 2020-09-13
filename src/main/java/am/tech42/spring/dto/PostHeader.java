package am.tech42.spring.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class PostHeader {
    private int postId;
    private String CompanyName;
    private String skill;
    private String jobType;
    private Date deadline;
    private String logoPath;
}

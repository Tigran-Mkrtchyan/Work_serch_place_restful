package am.tech42.spring.dto;

import lombok.Data;

import java.util.Set;


@Data
public class PostDto {
    private  String userId;
    private String description;
    private String deadline;
    private int jobTypeId;
    private Set<Integer> levelsId;
    private int skillId;
}

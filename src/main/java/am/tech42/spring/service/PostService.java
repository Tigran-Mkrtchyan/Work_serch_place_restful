package am.tech42.spring.service;

import am.tech42.spring.dto.PostDto;
import am.tech42.spring.dto.PostHeader;
import am.tech42.spring.exception.UnknownUserException;
import am.tech42.spring.model.*;
import am.tech42.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobTypeRepository jobTypeRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private SkillRepository skillRepository;

    public void addPost(PostDto postDto) throws UnknownUserException {
        Post post = new Post();
        JobType jobType = jobTypeRepository.getOne(postDto.getJobTypeId());
        Skill skill = skillRepository.getOne(postDto.getSkillId());
        Set<Level> levels = new HashSet<>();
        Level level;
        for (int levelId : postDto.getLevelsId()) {
            level = levelRepository.getOne(levelId);
            levels.add(level);
        }
        Company company = companyRepository.findCompanyByUserId(postDto.getUserId()).orElseThrow(
                ()-> new UnknownUserException("User not found")
        );
        post.setCompany(company);
        post.setDeadline(Date.valueOf(postDto.getDeadline()));
        post.setDescription(postDto.getDescription());
        post.setLevels(levels);
        post.setSkill(skill);
        post.setJobType(jobType);
        postRepository.save(post);
    }

    public PostDto getPost(Integer id) {
        Post post = postRepository.getOne(id);
        if (isDeadlineExpired(post.getDeadline())) {
            return null;
        }
        PostDto postDto = new PostDto();
        postDto.setDeadline(post.getDeadline().toString());
        postDto.setDescription(post.getDescription());
        postDto.setUserId(post.getCompany().getUser().getId());
        postDto.setJobTypeId(post.getJobType().getId());
        postDto.setSkillId(post.getSkill().getId());
        Set<Level> levels = post.getLevels();
        Set<Integer> levelsId = new HashSet<>();
        for (Level level : levels) {
            levelsId.add(level.getId());
        }
        postDto.setLevelsId(levelsId);
        return postDto;

    }


    public List<PostHeader> getPosts() {
        List<PostHeader> postHeaders = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            if (isDeadlineExpired(post.getDeadline())) {
                postRepository.delete(post);
                continue;
            }
            PostHeader postHeader = new PostHeader();
            Company company = post.getCompany();
            postHeader.setPostId(post.getId());
            postHeader.setCompanyName(company.getCompanyName());
            postHeader.setDeadline(post.getDeadline());
            postHeader.setJobType(post.getJobType().getTypeName());
            postHeader.setSkill(post.getSkill().getSkillName());
            if (company.getLogo() != null) {
                postHeader.setLogoPath(company.getLogo().getLogoUrl());
            }
            postHeaders.add(postHeader);
        }
        return postHeaders;
    }

    private boolean isDeadlineExpired(Date deadline) {
        java.util.Date dateForDeadLine = new java.util.Date(deadline.getTime());
        java.util.Date now = new java.util.Date();
        return now.after(dateForDeadLine);
    }
}

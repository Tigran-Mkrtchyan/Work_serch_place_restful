package am.tech42.spring.repository;

import am.tech42.spring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Override
    void delete(Post post);
}

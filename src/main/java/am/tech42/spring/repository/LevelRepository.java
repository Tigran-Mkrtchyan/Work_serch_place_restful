package am.tech42.spring.repository;

import am.tech42.spring.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level,Integer> {

}

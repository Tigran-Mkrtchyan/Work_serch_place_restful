package am.tech42.spring.repository;

import am.tech42.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findUserByEmailAndPassword(String email, String password);

    Optional<User> findUserById(String id);
}

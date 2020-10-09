package pe.ulima.edu.atisavi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.ulima.edu.atisavi.model.Role;
import pe.ulima.edu.atisavi.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}

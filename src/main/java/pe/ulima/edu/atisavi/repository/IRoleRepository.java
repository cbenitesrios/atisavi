package pe.ulima.edu.atisavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.ulima.edu.atisavi.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{

}

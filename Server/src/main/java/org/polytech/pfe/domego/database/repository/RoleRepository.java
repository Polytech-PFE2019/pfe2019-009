package org.polytech.pfe.domego.database.repository;

import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, Integer> {

    Optional<Role> findRoleById(int id);

    Optional<Role> findRoleByName(RoleType name);
}

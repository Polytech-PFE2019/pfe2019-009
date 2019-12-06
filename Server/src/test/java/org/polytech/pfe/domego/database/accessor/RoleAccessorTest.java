package org.polytech.pfe.domego.database.accessor;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoleAccessorTest {

    private final RoleAccessor roleAccessor;

    @Autowired
    public RoleAccessorTest(RoleAccessor roleAccessor) {
        this.roleAccessor = roleAccessor;
    }



    @Test
    void checkRoleAllRoleArePresent() {
        List<Role> roles = roleAccessor.getAllRoles();
        Assertions.assertFalse(roles.isEmpty());
        Assertions.assertTrue(roles.size() == RoleType.getNumberOfRole());

    }

    @Test
    void checkRoleById() {
        Optional<Role> role = roleAccessor.getSpecificRoleById(RoleType.MAITRE_D_OUVRAGE.getId());
        RoleType type = RoleType.MAITRE_D_OUVRAGE;
        Assertions.assertTrue(role.isPresent());

        assertEquals(role.get().getName(), type);

        assertEquals(role.get().getName().getId(), type.getId());
        assertEquals(role.get().getName().getName(), type.getName());

    }
}
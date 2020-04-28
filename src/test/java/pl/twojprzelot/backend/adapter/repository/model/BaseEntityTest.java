package pl.twojprzelot.backend.adapter.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {
    private static final String FIRST_ID = "FIRST_ID";
    private static final String ANOTHER_ID = "ANOTHER_ID";

    private BaseEntity firstBaseEntity;
    private BaseEntity sameBaseEntityAsFirstBaseEntity;
    private BaseEntity anotherBaseEntity;

    @BeforeEach
    void setUp() {
        firstBaseEntity = new BaseEntity();
        firstBaseEntity.setId(FIRST_ID);

        sameBaseEntityAsFirstBaseEntity = new BaseEntity();
        sameBaseEntityAsFirstBaseEntity.setId(FIRST_ID);

        anotherBaseEntity = new BaseEntity();
        anotherBaseEntity.setId(ANOTHER_ID);
    }

    @Test
    void equalsTest_equalObjects() {
        assertEquals(firstBaseEntity, sameBaseEntityAsFirstBaseEntity);
    }

    @Test
    void equalsTest_notEqualObjects() {
        assertNotEquals(firstBaseEntity, anotherBaseEntity);
    }

    @Test
    void hashCodeTest_equalObjects() {
        assertEquals(firstBaseEntity.hashCode(), sameBaseEntityAsFirstBaseEntity.hashCode());
    }

    @Test
    void hashCodeTest_notEqualObjects() {
        assertNotEquals(firstBaseEntity.hashCode(), anotherBaseEntity.hashCode());
    }
}
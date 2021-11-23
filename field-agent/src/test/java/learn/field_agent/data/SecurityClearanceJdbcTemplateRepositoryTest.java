package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void test1ShouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        actual = repository.findById(3);
        assertEquals(null, actual);
    }

    @Test
    void test2ShouldFindAll(){
        List<SecurityClearance> actual = repository.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    void test3ShouldAdd(){
        SecurityClearance confidential = new SecurityClearance();
        confidential.setName("Confidential");
        SecurityClearance actual = repository.add(confidential);
        confidential.setSecurityClearanceId(3);
        assertEquals(confidential, actual);
    }

    @Test
    void test4ShouldUpdate(){
        SecurityClearance secret = new SecurityClearance(1, "SecretService");
        assertTrue(repository.update(secret));
    }

    @Test
    void test5ShouldDelete(){
        assertTrue(repository.deleteById(3));
    }


}
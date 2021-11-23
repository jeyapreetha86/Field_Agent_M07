package learn.field_agent.data;

import learn.field_agent.models.AgentAlias;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AliasJdbcTemplateRepositoryTest {

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void test1ShouldFindByName() {
        AgentAlias alias = new AgentAlias();
        alias.setAgentId(7);
        alias.setFirstName("Ulises");
        alias.setMiddleName("B");
        alias.setLastName("Muhammad");
        alias.setDob(LocalDate.parse("2008-04-01"));
        alias.setHeightInInches(80);
        alias.setAliasId(1);
        alias.setName("Hazel");
        alias.setPersona("Sauven");

        List<AgentAlias> actual = repository.findByAlias("Hazel");
        assertEquals(1, actual.size());
        assertEquals(alias, actual.get(0));
    }


    @Test
    void test2ShouldAdd(){
        Alias alias = new Alias();
        alias.setName("Phylys");
        alias.setAgentId(8);
        alias.setPersona("This is test");
        Alias actual = repository.add(alias);
        alias.setAliasId(8);
        assertEquals(alias, actual);
    }

    @Test
    void test3ShouldUpdate(){
        Alias alias = new Alias();
        alias.setAliasId(7);
        alias.setName("Test");
        alias.setAgentId(1);
        alias.setPersona("This is new test");
        assertTrue(repository.update(alias));
    }

    @Test
    void test4ShouldDelete(){
        assertTrue(repository.deleteById(8));
    }


}

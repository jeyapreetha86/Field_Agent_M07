package learn.field_agent.domain;


import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AliasServiceTest {
    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    @Test
    void test1ShouldNotAddWhenInvalid() {
        Alias alias = makeAlias();
        alias.setName(null);

        Result<Alias> actual = service.add(alias);
        System.out.println(actual.getMessages());
        assertEquals(ResultType.INVALID, actual.getType());

        alias = makeAlias();
        Result<Alias> actual1 = service.add(alias);
        System.out.println(actual1.getMessages());
        assertEquals(ResultType.INVALID, actual1.getType());
    }

    @Test
    void test2ShouldAdd() {
        Alias alias = makeAlias();
        alias.setAliasId(0);

        Alias mockOut = makeAlias();
        mockOut.setAliasId(8);

        when(repository.add(alias)).thenReturn(mockOut);

        Result<Alias> actual = service.add(alias);
        System.out.println(actual.getType());
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void test3ShouldNotUpdateWhenInvalid() {
        Alias alias = makeAlias();
        alias.setAliasId(0);
        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias = makeAlias();
        alias.setName(null);
        actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void test4ShouldUpdate() {
        Alias alias = makeAlias();

        when(repository.update(alias)).thenReturn(true);

        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    Alias makeAlias() {
        Alias alias = new Alias();
        alias.setAliasId(7);
        alias.setName("Test");
        alias.setAgentId(1);
        alias.setPersona("This is new test");
        return alias;
    }
}

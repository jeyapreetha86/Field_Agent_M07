package learn.field_agent.domain;


import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SecurityClearanceServiceTest {
    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    @Test
    void test1ShouldNotAddWhenInvalid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setName(null);

        Result<SecurityClearance> actual = service.add(securityClearance);
        System.out.println(actual.getMessages());
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void test2ShouldAdd() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setName("Private");
        securityClearance.setSecurityClearanceId(0);

        SecurityClearance mockOut = makeSecurityClearance();
        mockOut.setName("Private");
        mockOut.setSecurityClearanceId(3);

        when(repository.add(securityClearance)).thenReturn(mockOut);

        Result<SecurityClearance> actual = service.add(securityClearance);
        System.out.println(actual.getType());
        securityClearance.setSecurityClearanceId(3);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void test3ShouldNotUpdateWhenInvalid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(0);
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance = makeSecurityClearance();
        securityClearance.setName(null);
        actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void test4ShouldUpdate() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setName("Private");

        when(repository.update(securityClearance)).thenReturn(true);

        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Secret");
        securityClearance.setSecurityClearanceId(1);
        return securityClearance;
    }
}

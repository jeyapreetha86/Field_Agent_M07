package learn.field_agent.data;

import learn.field_agent.models.Agent;
import learn.field_agent.models.AgentAlias;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;

import java.util.List;

public interface AliasRepository {

    List<Alias> findAll();
    Alias findById(int aliasId);

    List<AgentAlias> findByAlias(String aliasName);

    Alias add(Alias alias);

    boolean update(Alias alias);

    boolean deleteById(int aliasId);
}

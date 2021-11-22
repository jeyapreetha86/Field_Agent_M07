package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.AgentAlias;
import learn.field_agent.models.Alias;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AliasService {

    private final AliasRepository aliasRepository;

    public AliasService(AliasRepository aliasRepository) {
        this.aliasRepository = aliasRepository;
    }

    public List<Alias> findAll() {
        return aliasRepository.findAll();
    }

    public List<AgentAlias> findByAlias(String aliasName) {
        return aliasRepository.findByAlias(aliasName);
    }

    public Alias findById(int aliasId) {
        return aliasRepository.findById(aliasId);
    }

    public Result<Alias> add(Alias alias) {
        Result<Alias> result = validate(alias);

        if (!result.isSuccess()) {
            return result;
        }


        if (alias.getAliasId() != 0) {
            result.addMessage("aliasId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        alias = aliasRepository.add(alias);
        result.setPayload(alias);
        return result;
    }

    public Result<Alias> update(Alias alias) {
        Result<Alias> result = validate(alias);
        if (!result.isSuccess()) {
            return result;
        }

        if (alias.getAliasId() <= 0) {
            result.addMessage("aliasId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!aliasRepository.update(alias)) {
            String msg = String.format("aliasId: %s, not found", alias.getAliasId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int aliasId) {
        return aliasRepository.deleteById(aliasId);
    }


    private Result<Alias> validate(Alias alias) {
        Result<Alias> result = new Result<>();

        if (alias == null) {
            result.addMessage("Alias cannot be null", ResultType.INVALID);
            return result;
        }



        if (Validations.isNullOrBlank(alias.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        if(alias.getAliasId() == 0){
            List<Alias> findAllList = findAll();
            List<Alias> findAllListInner = findAll();
            for(Alias sc : findAllList){
                if(sc.getName().equalsIgnoreCase(alias.getName())){
                    for(Alias as : findAllListInner){
                        if(!Validations.isNullOrBlank(as.getPersona())) {
                            if (as.getName().equalsIgnoreCase(alias.getName()) && as.getPersona().equalsIgnoreCase(alias.getPersona())) {
                                result.addMessage("Name is Duplicate", ResultType.INVALID);
                            }
                        }
                        else{
                            if (as.getName().equalsIgnoreCase(alias.getName()) && Validations.isNullOrBlank(alias.getPersona())) {
                                result.addMessage("Name is Duplicate", ResultType.INVALID);
                            }
                        }
                    }
                }
            }
        }
        else{
            List<Alias> findAllList = findAll();
            List<Alias> findAllListInner = findAll();
            for(Alias sc : findAllList){
                if((sc.getAliasId() != alias.getAliasId()) && (sc.getName().equalsIgnoreCase(alias.getName())) ){
                    for(Alias as : findAllListInner){
                        if(!Validations.isNullOrBlank(as.getPersona())) {
                            if (as.getAliasId() != alias.getAliasId() && as.getName().equalsIgnoreCase(alias.getName()) && as.getPersona().equalsIgnoreCase(alias.getPersona())) {
                                result.addMessage("Name Exist for different Id", ResultType.INVALID);
                            }
                        }
                        else{
                            if (as.getAliasId() != alias.getAliasId() && as.getName().equalsIgnoreCase(alias.getName()) && Validations.isNullOrBlank(alias.getPersona())) {
                                result.addMessage("Name Exist for different Id", ResultType.INVALID);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }



}

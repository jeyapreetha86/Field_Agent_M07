package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class SecurityClearanceService {

        private final SecurityClearanceRepository securityClearanceRepository;

        public SecurityClearanceService(SecurityClearanceRepository securityClearanceRepository) {
            this.securityClearanceRepository = securityClearanceRepository;
        }

        public List<SecurityClearance> findAll() {
            return securityClearanceRepository.findAll();
        }

        public SecurityClearance findById(int securityClearanceId) {

            return securityClearanceRepository.findById(securityClearanceId);
        }

        public Result<SecurityClearance> add(SecurityClearance securityClearance) {
            Result<SecurityClearance> result = validate(securityClearance);
            if (!result.isSuccess()) {
                return result;
            }

            if (securityClearance.getSecurityClearanceId() != 0) {
                result.addMessage("securityClearanceId cannot be set for `add` operation", ResultType.INVALID);
                return result;
            }

            securityClearance = securityClearanceRepository.add(securityClearance);
            result.setPayload(securityClearance);
            return result;
        }

        public Result<SecurityClearance> update(SecurityClearance securityClearance) {
            Result<SecurityClearance> result = validate(securityClearance);
            if (!result.isSuccess()) {
                return result;
            }

            if (securityClearance.getSecurityClearanceId() <= 0) {
                result.addMessage("securityClearanceId must be set for `update` operation", ResultType.INVALID);
                return result;
            }

            if (!securityClearanceRepository.update(securityClearance)) {
                String msg = String.format("securityClearanceId: %s, not found", securityClearance.getSecurityClearanceId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }

            return result;
        }

        public boolean deleteById(int securityClearanceId) {
            return securityClearanceRepository.deleteById(securityClearanceId);
        }


        private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
            Result<SecurityClearance> result = new Result<>();

            if (securityClearance == null) {
                result.addMessage("SecurityClearance cannot be null", ResultType.INVALID);
                return result;
            }

            if (Validations.isNullOrBlank(securityClearance.getName())) {
                result.addMessage("Security clearance name is required", ResultType.INVALID);
            }
            List<SecurityClearance> findAllList = findAll();
            if(securityClearance.getSecurityClearanceId() == 0){
               for(SecurityClearance sc : findAllList){
                   if(sc.getName().equalsIgnoreCase(securityClearance.getName())){
                       result.addMessage("Name cannot be duplicated", ResultType.INVALID);
                   }
               }
            }
            else{
                for(SecurityClearance sc : findAllList){
                    if((sc.getSecurityClearanceId() != securityClearance.getSecurityClearanceId()) && (sc.getName().equalsIgnoreCase(securityClearance.getName())) ){
                        result.addMessage("Name Exist for different Id", ResultType.INVALID);
                    }
                }
            }
            return result;
        }



}

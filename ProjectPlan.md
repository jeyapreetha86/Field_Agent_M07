# Field Agent API - M07 Assessment Plan
#### (Jeya Preetha Ganesh Kumar)



## Overview: 

Field Agent API is an application that maintains the details of Agency, Agents, Mission, Location Security Clearance and Alias

## High-level Requirements
### Below Features are the features that need to be added in the current application
- Create full HTTP CRUD for security clearance.
- Create full HTTP CRUD for agent aliases.
- Implement global error handling.


### CRUD for Security Clearance
#### To Do
- Create full HTTP CRUD for security clearance.

#### Steps
- Create the interface SecurityClearanceRepository.java
- Create SecurityClearanceJDBCTemplateRepository.java by implementing SecurityClearanceRepository interface
- Add findAll, findById, add, update, delete methods
- Create SecurityClearanceMapper.java to serialize the the data read
- Create SecruityClearanceService.java with findAll, findById, add, update, delete methods and implement validations on add, update, delete methods
- Create SecurityController.java with GET, POST, PUT and DELETE endpoint methods.


### CRUD for agent aliases
#### To Do



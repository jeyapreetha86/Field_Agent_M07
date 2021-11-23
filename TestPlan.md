
# Module 7 Code Reviews

## Process

### Testing Locally

_Testing this assessment locally is time consuming if the trainee hasn't provided a collection of prewritten HTTP requests for each new API endpoint. In that situation, scheduling a meeting with the trainee to have them demo their endpoints is a quicker overall option._

* Clone the trainee's repo to your local machine
* Drop any existing assessment databases from previous code reviews
* Using Workbench...
  * Run the script to create the production database
  * Run the script to create the test database
* Per the test plan, use the VS Code REST Client extension to send HTTP requests to the trainee's API endpoints
* Note the bugs that you find so you can summarize them in a top level pull request comment

## Requirements Checklist

* [x] Find all security clearances
* [x] Find a security clearance by id
* [x] Add a security clearance
* [x] Update a security clearance
* [x] Delete a security clearance (challenge)
* [x] Find agent with aliases
* [x] Add an alias
* [x] Update an alias
* [x] Delete an alias
* [x] Global Error Handling (correctly handles data and general errors differently)
* [x] Test data components (all data components are tested with valuable tests)
* [x] Test domain components (all domain components are tested with valuable tests)
* [x] Java Idioms (excellent layering, class design, method responsibilities, and naming)

## Test Plan

_If the trainee followed instructions during kickoff, they should have an HTTP file with a good sequence of events for demonstrating CRUD capabilities._

### Security Clearance

* [x] GET all security clearances
* [x] GET a security clearance by ID
* [x] For GET return a 404 if security clearance is not found
* [x] POST a security clearance
* [x] For POST return a 400 if the security clearance fails one of the domain rules
  * [x] Security clearance name is required
  * [x] Name cannot be duplicated
* [x] PUT an existing security clearance
* [x] For PUT return a 400 if the security clearance fails one of the domain rules
* [x] DELETE a security clearance that is not in use by ID
* [x] For DELETE return a 404 if the security clearance is not found
* [x] For DELETE return a 400 if the security clearance is in use 

### Alias

* [x] GET an agent record with aliases attached
* [x] POST an alias
* [x] For POST return a 400 if the alias fails one of the domain rules
  * [x] Name is required
  * [x] Persona is not required unless a name is duplicated. The persona differentiates between duplicate names.
* [x] PUT an alias
* [x] For PUT return a 400 if the alias fails one of the domain rules
* [x] DELETE an alias by ID
* [x] For DELETE Return a 404 if the alias is not found

### Global Error Handling

* [x] Return a specific data integrity error message for data integrity issues
* [x] Return a general error message for issues other than data integrity
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:


### Guides
The following guides illustrate how to use some features concretely:


## The coding exercise is as follows:

## Test Java Application

We will need a new Java app to read in a CSV file from a directory. The contents will then need to be sent to a REST API endpoint, in JSON format, and saved to a SQL database.

1. Create a console app to read in a CSV file from a directory.

2. Parse the CSV file of which the contents are:

#### Customer Ref, Customer Name, Address Line 1, Address Line 2, Town, County, Country, Postcode

3. Loop through the rows of the CSV file and send each row to a REST API POST endpoint, in JSON format.

4. The REST API should then save the content to a database table. Format can match the CSV file.

5. Create a REST API GET endpoint to retrieve the customer information, passing in a customer ref to filter the data. Contents should be returned in JSON format.

6. Some documentation or Wiki to explain the approach taken.

Where possible, a Test-Driven Development (TDD) approach should be taken.

#### Details 

# text exercise implementation 

- I implemented the Restful API and Client rest.  
- using spring boot 
- Restful API
- Junit for test (The tests done both to check for exceptions that the cases of correct answers).



- If you use Maven, run the following command in a terminal window (in the complete directory):
`./mvnw spring-boot:run`

- Under the resource folder, there is a .csv file from where I created some records to insert as an initial step

- when you decide to run the application, the contents of the files are loaded into an H2 DB

### How to compile ?
- If you use Maven, run the following command in a terminal window (in the complete directory):
	`mvn clean install`
	
### How to Run and Test ?

if you use eclipse or sts , you can decide to run the project with ,
click on the project name with the right mouse button :
 'Run as' > Spring boot App

# From IDE/Tool
-open your IDE, import as maven project and you can launch directly with maven
  
# API's documentation
[WebLink API RESTful API](http://localhost:8080/swagger-ui/index.html)

# spring-boot
- List of Spring Boot Tutorials

### How to compile ?
- If you use Maven, run the following command in a terminal window (in the complete directory):
	`mvn clean install`
	
### How to Run and Test ?

- If you use Maven, run the following command in a terminal window (in the complete directory):
`$ ./mvnw clean spring-boot:run`
Alternatively using your installed maven version type this:
`$ mvn clean spring-boot:run`

Run Spring Boot application with command: `mvn spring-boot:run`

### DB console 
After run  , go to -> `http://localhost:8080/console` to have a look at DB console  runtime

If you want to test URL by client service example : 'Postman'

- Create a new GET request and as body > raw , add this

When the app starts, we can immediately interrogate it.
`$ curl -v localhost:8080/api/v1/customers`
`$ curl -v localhost:8080/api/v1/customer/ESG?`

 'ESG' is an example 
 
 `server.port`= 8080
 
-`http://localhost:8080/api/v1/customer/ESG?`

-`http://localhost:8080/api/v1/customers`

### By Commandline 
Now run the service with curl (in a separate terminal window), by running the following command (shown with its output):

`$ curl localhost:8080/api/v1/customer/ESG?`

`$ curl localhost:8080/api/v1/customers`

$ curl -v -X GET localhost:8080/api/v1/customer/ESG? 

- If you want change port , go to :  
`src/main/resources/application.properties`

Change the the property value for `server.port`	


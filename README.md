# api-rest-assured-framework

This API Automation project is created using REST Assured with java and integrated BDD Cucumber framework with it.

## Follow below Steps to create a similar project

Step 1 : Create a new maven project and install/import required packages/plugins in pom.xml.

* Rest Assured
* Cucumber-JVM: Java
* Cucumber-JVM: TestNG
* Jackson Databind
* testng

Step 2 : Create a folder structure

* Project Name
* under src/main/java create pojo(package) - will contain all pojo classes 
* under src/test/java create constants.api_endpoints(package) - will contain details of api endpoints
* under src/test/java create cucumberOptions(package) - it will have Test Runner class
* under src/test/java create features(package) - will contain all the features files
* under src/test/java create stepDefinitions(package) - will contain all the step definition files
* under src/test/java create service(package) - will contain all the utilities files
* under src/test/java create helpers(package) - will contain all the utilities files which need to create a body payload
* under project/directory create testng.xml file

## Reporting Tool used in framework to trigger the execution report :

```
 Cucumber-HTML Report
```

## To run the whole suite, type below command on terminal

```
mvn clean test verify
```


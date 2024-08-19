# 📦 Java-Selenium-Docker-Selenoid UI testing project 
This project is a study Maven Java application demonstrating automated testing practice using following stack of technologies: Selenium WebDriver, Docker, Selenoid, JUnit, MS SQL server, Allure. 
It is designed to showcase skills in writing maintainable and efficient automated tests.

## 🌟 Features
- Page Object Model (POM) using Page Factory: implements POM design pattern using Page Factory to organize test code and improve tests maintainability by separating test objects from test scripts.
- Method Chaining: implements Method Chaining in POM to make test steps more readable.
- Log4j2: implements Log4j2 logging framework to perform application logging, it provides precise information about a run of the application. 
- Instancio Models: utilizes Instancio to generate dynamic test data.
- Allure Reports: integrated Allure for generating detailed and visually appealing test reports.

## 🚧 Prerequisites
Before you can run this project, you must have the following software installed on your computer:
- Java Development Kit (this application has been written in JDK 17)
- Maven (this application has been written using Maven version 3.9.6)
- Docker desktop 
## 🛠️ Installation
1. Clone this repository to your local machine.   
   ```sh
   git clone https://github.com/TetianaLuk/repo3.git
   ```
2. Open the command line and run following commands to pull necessary Docker images:
   ```sh
   docker pull aerokube/selenoid:latest
   docker pull aerokube/selenoid-ui:latest
   docker pull selenoid/vnc_chrome:110.0
   docker pull selenoid/vnc_firefox:125.0
   ```

  Run following command to make sure that all images are present in the list of images:
   ```sh
   docker images  
   ```

  Repository contains `config` folder with `browsers.json` file, after the project has been cloned to your machine, go to this file and copy exact full path to it somewhere. You have to insert this path in the command below.
  
  Please note, the path should not contain spaces.  For example, the path looks like this: C:/Users/project/config  
  Run following commands to create and run necessary Docker containers:
  ```sh
   docker run -d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v /c/Users/project/config/:/etc/selenoid/:ro  aerokube/selenoid:latest  –limit 10
   docker run  -d --name selenoid-ui --link selenoid -p 8090:8080 aerokube/selenoid-ui --selenoid-uri=http://selenoid:4444     
   ```

  Run following command to make sure that containers are running:
  ```sh
   docker ps  
   ```
     
  Open your web browser and navigate to http://localhost:8090. You should see the Selenoid UI dashboard.

3. Navigate to the project directory using the command line.
4. Install the dependencies.   
   ```sh
   mvn clean install
   ```
## 🌐 Website under test
* https://skarb.foxminded.ua <br/>
Note that this website is being used for testing purposes, and I, the tester, acknowledge that I do not own or have any rights to this website. 
Testing activities are for demo purposes only.
## 🚀Usage
### Running Tests

Navigate to the project directory using the command line. 
To execute all test classes, run command:
   ```sh
   mvn clean test 
   ```  
To execute one test class run the following command but replace `<test_class>` with the name of the test class:
   ```sh
   mvn test -Dtest=<test_class>
   ```  
To see the process of test run, open your web browser and navigate to http://localhost:8090. You should see executing at this moment test on the Selenoid UI dashboard.

### Generating Allure Report

You can generate a report using one of the following commands:
  ```sh
   mvn allure:serve
   ```  
Report will be generated into temp folder. Web server with results will start.
  ```sh
   mvn allure:report
   ```  
Report will be generated to directory: target/site/allure-maven/index.html

## Stop and remove containers after you finished test run
After you finished all desired test runs, do not forget to stop and remove containers. To do this, run the following commands:
  ```sh
   docker stop solenoid-ui
   docker stop selenoid
   docker container rm solenoid-ui
   docker container rm selenoid
   ```  
If you do not need images anymore, remove each one of them using following command:
  ```sh
   docker image rm <name of image including version>
   ```  
## 📦 Test classes 
This project contains 13 test classes (test suits):
- `VolunteerRegistrationFormPositiveTests`: suit of positive tests for Volunteer registration form.
- `VolunteerRegistrationFormNegativeTests`: suit of negative tests for Volunteer registration form.
- `VolunteerParameterizedTest`: parameterized test for Volunteer registration form with use of valid data from @MethodSource.
- `VolunteerInMailHogServiceTest`: test attempts to register new Volunteer and then confirm his email in the MailHog service.
- `PartnerRegistrationFormPositiveTests`: suit of positive tests for Partner registration form.
- `PartnerRegistrationFormNegativeTests`: suit of negative tests for Partner registration form.
- `PartnerParameterizedTest`: parameterized test for Partner registration form with use of valid data from @MethodSource.
- `PartnerInMailHogServiceTest`: test attempts to register new Partner and then confirm his email in the MailHog service.
- `NGOtasksForVolunteerParameterizedTest`: tests attempt to login in existing NGO profile and create new tasks for volunteer using valid data from @MethodSource and @CsvFileSource.
- `NGOregistrationFormPositiveTest`: positive test for NGO registration form.
- `DatabaseConnectionTests`: suit of positive tests which are interacting with data in the Database using SQL queries. 
- `ChromeTest`: simple test that attempts to open Chrome browser and go to specified url.
- `FirefoxTest`: simple test that attempts to open Firefox browser and go to specified url.

## 📦 Project structure 
- config: browsers.json configuration file for Docker
- src/main/java: 
  - database: Database connection manager class.
  - pageobjects: Page Object classes implementing the POM with Page Factory.
  - utils: classes for driver configuration and setup, random data generation, read properties methods, methods for interaction with web elements.
- src/main/resources: log4j2.xml configuration file.
- src/test/java: 
  - models: classes with Instancio models for fake test data generation.
  - testdata: classes with test data for parameterized tests.
  - tests: test classes.
- src/test/resources:
  - testdata: config.properties file, and csv file (data for task for Volunteer for parameterized test).
  - allure.properties configuration file.
- gitignore file.
- pom.xml Maven configuration file.

## 🌟 Contact
Please contact bobrotetiana@gmail.com if you have any questions.

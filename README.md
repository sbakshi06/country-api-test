
# country-api-test
Automation Test Suite for Country API by groupkt

### Summary:
This automation framework is to test the [Country APIs] provided by [Groupkt].

The following apis have been tested
* Get All Countries
  > http://services.groupkt.com/country/get/all

* Get Country by country code
  > http://services.groupkt.com/country/get/iso2code/{COUNTRY_ISO2CODE}
* Add new country
  > http://services.groupkt.com/country


 Technologies/Frameworks used
* TestNG for test driven development approach.
* RestAssured framework for HTTP client calls.

A modular approach is followed with method and variable names suggesting their purpose throughout the project, to avoid redundant comments.

### How to run tests:
Import the project as a Maven project and execute following command.
```sh
$ mvn test
```
There is a test which makes a POST call to the API which is expected to fail as POST requests are not supported by the API currently.
The test report can be found under the generated **target** folder at the following path:
```
./target/surefire-reports/html/index.html
```

### How to generate TestNG reports manually:
```
Run the testng.xml file
```
The testng report is generated under **./test-output/html** folder. A sample test run is present at index.html file located under the 'test-output/html' folder.

[Country APIs]: http://services.groupkt.com/post/c9b0ccb9/restful-webservices-to-get-and-search-countries.htm
[Groupkt]: http://services.groupkt.com/home.htm



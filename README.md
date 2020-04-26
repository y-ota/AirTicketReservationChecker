# Air Tikect Reservation Checker
This tool is to check the flight reservation using selenium.

## Requirements
* Java 8
* Maven
* Selenium
* Chromedriver
* Simple Java Mail

## Setup & Run
1. Change the following code.
```
driver.findElement(By.cssSelector(Mappings.MEMBER_NO)).sendKeys("your id");
driver.findElement(By.cssSelector(Mappings.ACCESS_CD)).sendKeys("your pass");
```
```
.from("e-mail") // change e-mail
.to("e-mail") // change e-mail
```

2. Build with Maven.

3. `java AirTicketReservationChecker`

4. Screenshot will be taken automatically and e-mail you.

## Licnense
[MIT License](https://github.com/y-ota/AirTicketReservationChecker/blob/master/README.md)

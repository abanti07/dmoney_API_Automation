# dmoney_API_Automation
### In this project,I have automated the d-money API (https://api.postman.com/collections/1844288-143eb923-423f-4c91-a198-fe6e56d20e35?access_key=PMAT-01GJ3CC22Q0066PJWP3T0XHQ8G) using Rest Assured framework.The tasks which are done in tis project is given bellow
     - login
     - Create user
     - Deposit money 
     - Withdraw
     - Send money
     - Payment
     - Check balance
## Tools & Technologies
- Intellij idea
- TestNG
- Rest Assured
- Allure (To generate report)
## Prerequiste
- jdk
- gradle
## How to run
- Give the following command in terminal
  -- gradle test clean
- To generate Allure report :
   -- allure generate allure-reports --clean -output
   -- allure serve allure-results

# Description
* This is a java Selenium test project developed for testing restrictions feature of Confluence using chrome driver.
* This project uses page object pattern to avoid tests being too sensitive to UI changes.
* This project uses data driven approach in order to keep data separate from functional test logic and to aid with quick scaling.
* This project uses testNG test framework for its sophisticated features such as html run reports, ability to group and prioritize tests, parallel testing, data parameterization.
* This project is built using maven tool for its simplicity of use w.r.t downloading dependencies and building the project.

#Presetup
Since these tests run on confluence production site instead of staging(test) environment, user creation, space and page creation are done manually prior to automation.
These users and pages are hard coded in the scripts.

# How to run
Prerequisite: Install testng, maven, java, chrome driver
To run the tests, goto testng.xml in src/test/resources, right click, run as testng suite.

# Test cases included:
1. Add restrictions on a user.
2. Remove restrictions applied on a user
3. Inspecting permissions (added to both testcases 1 and 2 as part of validation)


# Further enhancements to framework with Atlassian testing environment

- [ ] Include dynamic user, space and page creation using staging environment.
- [ ] Database validations after each write operation to help isolate back end and front end issues.
- [ ] Email validations for flows that trigger emails like request and accept permissions.
- [ ] Attach the tests as part of CI/CD pipeline
- [ ] Space settings can be set in the script to test more combinations with page and space settings

Test run results - testng html report
![picture](test-output/testResultsScreenShot.png)

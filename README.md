                    **PHP Travels Test Plan**
    Objectives:
        Test scope: testing page header, body and footer.
    Page Structure:
        - Header
            - My account
            - Language
            - Currency
            - Menu items
        - Search bar
        - Main Body
        - Footer

    Environment:
        Web browser:
            - Google Chrome
            - Firefox
        URL: https://www.phptravels.net/home **
        IDE: IntelliJ
        JDK: 8 (Java version 1.8)

    Automation Strategy:
        - Project build: Maven
        - Web Driver: Selenium
        - Testing framework: TestNG
        - Design pattern: Page Object Model
        - Version control: GitHub Remote Repository --> Project name: PHPTravels
            ○ Project management & bug tracking: JIRA
  
    Time Line/ Schedule:
        Sprint:
            Two weeks sprint cycle
        Meetings:
            -Sprint Planning
            -Daily Stand-up
            -Demo (Sprint Review)
            -Retro

    To run the SmokeSuite.xml or RegressionSuite.xml from Command Prompt:
        (Assuming maven is installed in your computer)
    
    1. Go to configuration.properties and change the browser type to chrome-headless(the thread count is set to 5, it is not good to run that many tests at once in non headless mode)
    
    2. While in IntelliJ project, right click on the project folder  ->  Open In  ->  Explorer(Windows)
    
    3. File Explorer show be open after last step; click on the file path and copy it.
    
    4. Go to Start -> type cmd -> select Command Prompt
    
    5. Enter following commands in specified order:
        1. cd C:\Users\Duke\Desktop\cybertek\PHPTravels -> then click Enter.
        2. maven clean test -DFileName=SmokeSuite.xml
              - FileName is the variable used within the sureFire plugin ${FileName}
              - after "=" enter the xml file you wish to execute

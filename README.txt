This will work as my short description, basiclly, this project main features is: 
- Data driven. 
- Key driven. 
- Modular, reusable methods. 
- OOP design applied. 
- Selenium. 
- TestNG used. 
- Allure reporting tool is used. 
- Cross browser support. 
- configuration file: property file. 

With that stated, an overview of how the framework works: 
- The pages folder applys the page object model as every page that will be progressed to and the UI locators that we will interact with are 
identified in every page's class. 
- The utilities folder applys the oop and reusability of code as each file in it satisifies a certain goal, actionElements to handle UI 
interactions, baseClass to handle browser web drivers, properties file, to handle configuration file usage, Helper, to handle failures 
screenshots taken. 
- VodafoneSearchQuery uses all the above to perform testing using TestNG with no hardcodes and oop usage. 

As for technical challenges: 
- At the beginning i used specific webdrivers to run on my pc, then i used after webdriver manager to be able to run on whatever browser 
exists on the running device. 
- In both cases IE had problems working with me, so i used capabilities to tweak and control as much properties as i can to run IE, and 
specified the version i worked with and it is found in IEDriver folder. 
- At the beggining i used allure listeners to take the screenshots but there was problems with the aspectJ, so i altered to a more coded approach. 
- The documentations was a challenge with me because my previous agile experience wasn't so into documentations. 
- Didn't have the time to apply the selenium grid. 
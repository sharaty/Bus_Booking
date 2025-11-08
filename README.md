🚌 Bus Booking Automation Project
📖 Overview

This project automates the bus ticket booking flow for an online bus reservation website using Selenium WebDriver, Java, and TestNG.
It follows the Page Object Model (POM) design pattern to ensure better code reusability and maintainability.

⚙️ Tech Stack

Programming Language: Java

Automation Tool: Selenium WebDriver

Testing Framework: TestNG

Build Tool: Maven

Design Pattern: Page Object Model (POM)

Reporting: TestNG Reports / Extent Reports (if added)

Data Handling: CSV / Excel

🚀 Features Automated

Launching the browser and navigating to the RedBus-like booking site

Searching buses between source and destination

Selecting date of travel

Choosing a bus and seat

Proceeding to passenger details page

Validating the booking summary

Logging test results to a CSV or report file






Bus_Booking/
│
├── src/main/java/
│   ├── pages/              # Page Object Model classes
│   ├── utils/              # Utilities (driver setup, CSV handling)
│
├── src/test/java/
│   ├── tests/              # TestNG test classes
│
├── testng.xml              # TestNG suite configuration
├── pom.xml                 # Maven dependencies and build config
└── README.md               # Project documentation

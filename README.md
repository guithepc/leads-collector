# Leads collector

**Collect your HeroSpark leads easily with this application.** Selenium automation designed to collect infinite leads from your HeroSpark funnel.

## Table of Contents
- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)


## Introduction

There are a few adjustments you need to make before using the program. For instance, you should configure the time interval for it to iterate to the next page. It's important to know the number of lead pages you have so you can adjust this setting according to your needs. (https://github.com/guithepc/leads-collector/blob/master/src/main/java/com/pcaut/leads_collector/services/impl/ConnectionServiceImpl.java#L53)

Other adjustment you need to do is define the file path where the leads list .txt will be created. (https://github.com/guithepc/leads-collector/blob/master/src/main/java/com/pcaut/leads_collector/services/impl/ConnectionServiceImpl.java#L68)

## Installation

Steps to install and set up the environment for the project:

1. Clone the repository:
   ```bash
   git clone https://github.com/guithepc/leads-collector.git
   ```

## Usage

1. Run the **LeadsCollectorApplication.java** file.
2. Make a POST request in:
    ```
    http://localhost:8080/services/connect
    ```
3. A Chrome web page will open where you'll need to enter your email and password and then **WAIT!!!** 
The automation will click the login button once it's ready to proceed.
4. Subsequently, the TXT file will be generated at the specified path.

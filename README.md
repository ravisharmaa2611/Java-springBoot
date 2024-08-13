

# Theater Hall Management System

The Theater Hall Management System is a comprehensive web application designed to streamline the management of theater halls, events, bookings, and user access control. This project is built using Java, Spring Boot, Spring Data (JPA), MySQL, Thymeleaf, and Spring Security.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
5. [Usage](#usage)
   - [Configuration](#configuration)
   - [Running the Application](#running-the-application)


## Introduction

The Theater Hall Management System is a user-friendly web application that simplifies the process of managing theater halls, events, and bookings. It provides an intuitive interface for both administrators and users, ensuring a seamless experience when reserving tickets for various events. Additionally, the system incorporates Spring Security to manage user authentication and authorization securely.

Description:

Designed and developed a modern Theatre Hall Management System using cutting-edge technologies, including Spring Boot, Thymeleaf, Spring Security, JPA, and MySQL. This system streamlined ticket booking, event management, and security, enhancing the overall theater experience.

Key Achievements and Responsibilities:

- *Spring Boot Framework:* Utilized Spring Boot to create a robust and scalable application, ensuring rapid development and deployment.

- *Thymeleaf Templates:* Designed a dynamic and responsive user interface using Thymeleaf templates, providing an intuitive booking experience for patrons.

- *Spring Security:* Implemented comprehensive security measures with Spring Security, including user authentication and authorization, to safeguard customer data and payment information.

- *JPA Integration:* Integrated JPA (Java Persistence API) for efficient data management, enabling seamless interaction with the MySQL database for ticketing, show scheduling, and customer records.

- *Ticket Booking System:* Engineered a user-friendly ticket booking system that allowed customers to select shows, choose seats, and complete transactions securely.

- *Event Management:* Created an event management module for theater staff to efficiently schedule and manage events, including play details, pricing, and seating arrangements.

- *Reporting and Analytics:* Integrated reporting capabilities to generate insights into ticket sales, revenue, and audience demographics, facilitating data-driven decisions.

- *Testing and Quality Assurance:* Conducted thorough testing, including unit testing and end-to-end testing, to ensure system reliability and performance.

This project exemplified my expertise in modern web application development, showcasing proficiency in Spring Boot, Thymeleaf, Spring Security, JPA, and MySQL. It demonstrated my ability to create secure, user-friendly systems while efficiently managing and analyzing data for enhanced theater operations.

## Features

- **Theater Hall Management**: Administrators can add, edit, and remove theater halls, specifying details such as seating capacity, location, and facilities.

- **Event Management**: Create and manage events, including information such as event name, date, time, price, and hall assignment.

- **Booking Management**: Users can browse available events and make bookings by selecting seats and providing necessary information.

- **User Access Control**: Implements Spring Security to control user access, including roles like admin and regular user, ensuring data security.

## Technologies Used

The project leverages the following technologies and frameworks:

- Java
- Spring Boot
- Spring Data (JPA)
- MySQL
- Thymeleaf (for server-side templating)
- Spring Security

## Getting Started

### Prerequisites

Before you begin, ensure you have the following prerequisites installed:

- Java Development Kit (JDK) 
- MySQL database server
- Git (for version control)
- Your preferred Java Integrated Development Environment (IDE)

### Installation

1. Clone the repository:

   ```bash
   https://github.com/ravisharmaa2611/Theatre-hall-management-.git
   ```

2. Navigate to the project directory:

   ```bash
   cd theater-hall-management
   ```

3. Configure your MySQL database settings in `application.properties`:

   ```properties
   spring.datasource.url=jdbc driver with database url
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## Usage

### Configuration

The database schema will be automatically created using Spring Data JPA. Ensure that your database is up and running, and adjust the database connection settings as mentioned in the prerequisites.

### Running the Application

1. Build the project:

   ```bash
   ./mvnw clean install
   ```

2. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

3. Access the application in your web browser:

   ```
   http://localhost:8080
   ```



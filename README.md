# Navigation Project

## Project Description

The project serves as a navigation system for selecting optimal routes between cities. The database stores information about routes, and the program allows users to choose the best routes based on distance and time.

## Features

1. **Direction Selection:**
   - Users can choose routes from the list of available directions.

2. **Finding Optimal Routes:**
   - The program finds the shortest and fastest routes between selected departure and destination points using Dijkstra's algorithm.

3. **Compilation of Transport List with Schedule:**
   - The program compiles a list of transport with schedules between cities.

## Installation and Setup

1. Install a local database
2. Use the files for manual database initialization located in the `sql-scripts` folder:
   - `create_navigation_schema_tables.sql`
   - `insert_navigation_data.sql`

## Technologies Used

- **Log4j:** Logging library for Java.
- **Lombok:** Java library that automatically plugs into your editor and build tools, spicing up your java.
- **MySQL Connector:** JDBC driver for MySQL.
- **MyBatis:** SQL mapper framework.
- **Jackson:** JSON processor for Java.
  
## Program Results
The program's results are recorded in a file after each session, containing a list of all routes and their variants during the session.

## Contributors

This project was developed by:
- [yunchits](https://github.com/yunchits)
- [pasha2291](https://github.com/pasha2291)
- [tkarsakov](https://github.com/tkarsakov)
- [Senerro](https://github.com/Senerro)

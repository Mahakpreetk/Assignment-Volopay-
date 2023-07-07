TO write this api the language used is java and framework used is Springboot.

In the code the "TotalItemsController" class defines the /api/total_items endpoint, 
which accepts the required parameters "start_date, end_date, and department". 

The controller's "getTotalItemsSold" method retrieves the total number of items sold 
in Marketing within the specified date range by iterating over the dataset array and 
filtering the sales based on the given criteria.

Since i have used dataset directly ,it will read the data from the Excel file specified 
and populate the dataset array accordingly.


Make sure while testing the code test with the excel file places in folder api_one as the data
file has been manipulated a little in order to make it suitable for code.




While setting up the code on your side make sure you add Apache POI library to read the data
from excel sheet


To add the Apache POI library in project Open the pom.xml file in your project directory.

Within the <dependencies> section, add the following dependencies:

<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.0.0</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.0.0</version>
</dependency>

This adds the Apache POI dependencies for reading and writing Excel files.

Save the pom.xml file.


Maven will automatically download the required Apache POI JAR files and add
them to your project classpath when you build your project.
Once the dependencies are added, you can use the Apache POI library in 
code to work with Excel files.

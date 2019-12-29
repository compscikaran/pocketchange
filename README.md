# PocketChange

An expense tracker that tracks different category of expenses.
It allows to filter monthly and category wise expenses. 
It has a dashboard that gives the user basic analytics of their expenses.

1. Install MySQL DB and run the create_table.sql file in resources folder
2. Compile war by running 

> ./mvnw clean package -X -DskipTests

3. Deploy the generated war file to standalone tomcat 9 server
4. Application will be available at http://localhost:8080/pocketchange/

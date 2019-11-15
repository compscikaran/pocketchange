#Stop Nginx and Tomcat
ssh $1@$2 systemctl stop nginx 
ssh $1@$2 systemctl stop tomcat
#Create new Build
./mvnw clean package -DskipTests -X
#Deploy new build
scp ./target/*.war $1@$2:/opt/tomcat/webapps/pocketchange.war
#Start Tomcat and Nginx
ssh $1@$2 systemctl start tomcat
ssh $1@$2 systemctl start nginx

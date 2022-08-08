mvn clean package
# rm -rf ../programm/apache-tomcat-10.0.21/webapps/bookstore
rm -rf ../programm/apache-tomcat-10.0.21/webapps/bookstore.war
cp ./target/bookstore.war ../programm/apache-tomcat-10.0.21/webapps/bookstore.war
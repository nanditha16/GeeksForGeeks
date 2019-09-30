
# Prerequisite

Java and Maven needs to be installed on the system to run the test

Execute the following command to prepare the environment:

sudo yum install -y java-1.8.0-openjdk-devel
echo "JAVA_HOME=$(readlink -f /usr/bin/java | sed "s:bin/java::")" | sudo tee -a /etc/profile
source /etc/profile
cd
wget http://mirror.csclub.uwaterloo.ca/apache/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz (if the binary is not available, download the latest version from: http://mirror.csclub.uwaterloo.ca/apache/maven/maven-3/)
tar -zxvf apache-maven-3.5.4-bin.tar.gz
sudo mv ~/apache-maven-3.5.4 /opt
sudo chown -R root:root /opt/apache-maven-3.5.4
sudo ln -s /opt/apache-maven-3.5.4 /opt/apache-maven
echo 'export PATH=$PATH:/opt/apache-maven/bin' | sudo tee -a /etc/profile
source /etc/profile


# Getting started

To run test, replace the java in the src/main/com/genesys folder and issue "mvn clean test" to build the test

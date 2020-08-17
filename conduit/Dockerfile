FROM markhobson/maven-chrome
WORKDIR /e2e
ADD . /e2e/.
RUN mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
chcp 65001
cd /d "%~dp0"
mvn clean install
java -cp "./lib/*;target\wordle-1.5.jar" Main
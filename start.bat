chcp 1251
cd /d "%~dp0"
javac -encoding utf8 -d out/ src/main/java/api/*java src/main/java/domain/*.java src/main/java/infrastructure/*.java src/main/java/*.java src/main/java/Main.java
java -cp out/ Main
chcp 1251
cd /d "%~dp0"
mkdir out
javac -cp ".;lib/*" -d out src/main/java/api/apiLogic/*.java src/main/java/api/console/*.java src/main/java/api/env/*.java src/main/java/domain/model/game/*.java src/main/java/domain/model/attempt/*.java src/main/java/domain/service/*.java src/main/java/infrastructure/filePars/*.java src/main/java/infrastructure/*.java src/main/java/*.java
java -cp "out/;./lib/*" Main
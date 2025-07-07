chcp 1251
d:
cd D:\learn\java\wordle2
javac -d out/ src/main/java/api/*java src/main/java/domain/*.java src/main/java/infrastructure/*.java src/main/java/*.java src/main/java/Main.java
java -cp out/ Main
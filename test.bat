@echo off

javac -sourcepath ./src -d build src/net/volgatech/Main.java
java -cp build/ net/volgatech/Main
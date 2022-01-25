@echo off

@del /s /q *.class

"C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot\bin\javac.exe" -d ./bin --module-path ./lib/ --add-modules=javafx.controls,javafx.fxml --source-path ./src %*

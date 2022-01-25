@echo off
"C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot\bin\java.exe" --class-path ./bin --module-path ./lib/ --add-modules=javafx.controls,javafx.fxml %*

# Commande pour la compilation du projet à faire dans le fichier src:

/!\ Faire attention à bien les faire avec javafx
```sh
cd src
javac -Xlint:all -Xdiags:verbose *.java util/*.java .\scenes\editor\EditorController.java .\scenes\main\MainMenuController.java .\scenes\option\OptionController.java grid/*.java
java Main
```
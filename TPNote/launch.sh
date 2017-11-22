javac -d . ./src/*.java
gnome-terminal -x bash -c "java -cp obfousfous.jar fousfous.ServeurJeu 1234 1"
gnome-terminal -x bash -c "java -cp obfousfous.jar fousfous.ClientJeu fousfous.JoueurAleatoire localhost 1234"
gnome-terminal -x bash -c "java fousfous.ClientJeu fousfous.PlateauFousfous localhost 1234"

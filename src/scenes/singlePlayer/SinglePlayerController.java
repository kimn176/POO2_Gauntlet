package scenes.singlePlayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import util.ImageEnum;
import util.Window;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import util.PlayerProfile;
import javafx.scene.layout.Background;

public class SinglePlayerController implements Initializable {
    public StackPane stack = new StackPane();

    @FXML
    Button warriorButton;
    @FXML
    Button valkyrieButton;
    @FXML
    Button elfButton;
    @FXML
    Button wizardButton;

    //Profils de joueur
    private PlayerProfile warriorProfile = new PlayerProfile("Warrior", 4, 3, 2, 1);
    private PlayerProfile valkyrieProfile = new PlayerProfile("Valkyrie", 3, 2, 3, 2);
    private PlayerProfile elfProfile = new PlayerProfile("Elf", 1, 2, 3, 4);
    private PlayerProfile wizardProfile = new PlayerProfile("Wizard", 1, 4, 2, 3);
    private boolean warriorChoose = false;
    private boolean valkyrieChoose = false;
    private boolean elfChoose = false;
    private boolean wizardChoose = false;
    @FXML
    public void warriorAction(ActionEvent event) {
        warriorChoose = !warriorChoose;
        if (warriorChoose) {
            warriorButton.setStyle("-fx-background-color:red");
            updatePlayerProfile(warriorProfile);
            System.out.println("Warrior choosed");

            valkyrieButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            elfButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            wizardButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        } else{
            warriorButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        }
    }

    @FXML
    public void valkyrieAction(ActionEvent event) {
        valkyrieChoose = !valkyrieChoose;
        if (valkyrieChoose) {
            valkyrieButton.setStyle("-fx-background-color:red");
            updatePlayerProfile(valkyrieProfile);
            System.out.println("Valkyrie choosed");

            warriorButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            elfButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            wizardButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        } else{
            valkyrieButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        }
    }

    @FXML
    public void elfAction(ActionEvent event) throws Exception {
        elfChoose = !elfChoose;
        if (elfChoose) {
            elfButton.setStyle("-fx-background-color:red");
            updatePlayerProfile(elfProfile);
            System.out.println("Elf choosed");

            warriorButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            valkyrieButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            wizardButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        } else{
            elfButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        }
    }

    @FXML
    public void wizardAction(ActionEvent event) throws Exception {
        wizardChoose = !wizardChoose;
        if (wizardChoose) {
            wizardButton.setStyle("-fx-background-color:red");
            System.out.println("Wirard chossed");
            updatePlayerProfile(wizardProfile);

            warriorButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            elfButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
            valkyrieButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        } else{
            wizardButton.setStyle("-fx-background-color: rgba(240, 230, 140, 0.3);");
        }
    }

    @FXML
    public void backAction(ActionEvent event) throws Exception {
        System.out.println("Action Back");
        Window poo_projet = Window.app;
        poo_projet.showHome();
    }

    @FXML
    public void startAction(ActionEvent event) throws Exception {
        System.out.println("Action Quitter");
        Window.app.stop();
        System.exit(0);
    }

    // Mettre à jour les caractéristiques du joueur en fonction du profil sélectionné
    private void updatePlayerProfile(PlayerProfile profile) {
        int defense = profile.getDefense();
        int damage = profile.getDamage();
        int speed = profile.getSpeed();
        int rangeAttack = profile.getRangeAttack();
    }

    // Méthode pour générer un ImageView à partir d'une ImageEnum
    private ImageView generateImageView(ImageEnum imageEnum) {
        ImageView imageView = imageEnum.generateImageData(4, 0).generateImageView();
        imageView.setFitWidth(100); // Ajustez la largeur selon vos besoins
        imageView.setFitHeight(100); // Ajustez la hauteur selon vos besoins
        return imageView;
    }

    private ImageView generateImageView(ImageEnum imageEnum, int x, int y) {
        ImageView imageView = imageEnum.generateImageData(x, y).generateImageView();
        imageView.setFitWidth(100); // Ajustez la largeur selon vos besoins
        imageView.setFitHeight(100); // Ajustez la hauteur selon vos besoins
        return imageView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Création d'une image pour l'arrière-plan
        Image backgroundImage = new Image(getClass().getResource("/sprites/page_accueil.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        stack.setBackground(new Background(background));

        //Set buttons images
        warriorButton.setGraphic(generateImageView(ImageEnum.WARRIOR));
        valkyrieButton.setGraphic(generateImageView(ImageEnum.VALKYRIE));
        elfButton.setGraphic(generateImageView(ImageEnum.ELF));
        wizardButton.setGraphic(generateImageView(ImageEnum.WIZARD));

        //Creation of Runnable Buttons
        CharacterThread Wt = new CharacterThread(warriorButton, ImageEnum.WARRIOR);
        CharacterThread Vt = new CharacterThread(valkyrieButton, ImageEnum.VALKYRIE);
        CharacterThread Et = new CharacterThread(elfButton, ImageEnum.ELF);
        CharacterThread Wit = new CharacterThread(wizardButton, ImageEnum.WIZARD);

        //Animation Warrior Button
        warriorButton.setOnMouseEntered(e -> {
            Wt.restart();
            Thread t = new Thread(Wt);
            t.start();
        });
        warriorButton.setOnMouseExited(e -> {
            Wt.stop();
        });

        //Animation Valkyrie Button
        valkyrieButton.setOnMouseEntered(e -> {
            Vt.restart();
            Thread t = new Thread(Vt);
            t.start();
        });
        valkyrieButton.setOnMouseExited(e -> {
            Vt.stop();
        });

        //Animation Elf Button
        elfButton.setOnMouseEntered(e -> {
            Et.restart();
            Thread t = new Thread(Et);
            t.start();
        });
        elfButton.setOnMouseExited(e -> {
            Et.stop();
        });

        //Animation Wizard Button
        wizardButton.setOnMouseEntered(e -> {
            Wit.restart();
            Thread t = new Thread(Wit);
            t.start();
        });
        wizardButton.setOnMouseExited(e -> {
            Wit.stop();
        });


    }

    /**
     * @author nino
     * @author lucas
     * @author kim
     * @author minh
     *
     * This function take a button who a character is in to spin him into button, in a new Thread Runnable
     */
    static class CharacterThread implements Runnable {
        private boolean looping = true;
        private final ImageEnum character;
        private final Button button;
        private final ImageView currentImageView;

        /**
         * @param but It's the button used to be selected
         * @param character This is the ImageEnum of the character
         */
        public CharacterThread(Button but, ImageEnum character) {
            this.character = character;
            this.button = but;
            currentImageView = (ImageView)button.getGraphic();
        }

        @Override
        public void run() {
            try {
                int row = 0;
                int col = 0;
                while(looping) {
                    this.character.updateImageView(currentImageView, col, row);
                    Thread.sleep(50);
                    row++;

                    if(row%2 == 0) {
                        col++;
                        row = 0;
                    }

                    if(col == 8) {
                        col = 0;
                    }
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        public void stop() {
            this.character.updateImageView(currentImageView, 4, 0);
            looping = false;
        }

        public void restart() {
            looping = true;
        }
    }
}

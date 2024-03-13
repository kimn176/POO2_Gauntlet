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

import java.net.URL;
import java.util.ResourceBundle;
import util.PlayerProfile;

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

    @FXML
    public void warriorAction(ActionEvent event) {
        System.out.println("Worrior choosed");
        updatePlayerProfile(warriorProfile);
    }

    @FXML
    public void valkyrieAction(ActionEvent event) {
        System.out.println("Valkyrie choosed");
        updatePlayerProfile(valkyrieProfile);
    }

    @FXML
    public void elfAction(ActionEvent event) throws Exception {
        System.out.println("Elf choosed");
        updatePlayerProfile(elfProfile);
    }

    @FXML
    public void wizardAction(ActionEvent event) throws Exception {
        System.out.println("Wirard chossed");
        updatePlayerProfile(wizardProfile);
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
        imageView.setFitWidth(50); // Ajustez la largeur selon vos besoins
        imageView.setFitHeight(50); // Ajustez la hauteur selon vos besoins
        return imageView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Création d'une image pour l'arrière-plan
        Image backgroundImage = new Image(getClass().getResource("/sprites/page_accueil.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        stack.setBackground(new Background(background));

        warriorButton.setGraphic(generateImageView(ImageEnum.WARRIOR));
        valkyrieButton.setGraphic(generateImageView(ImageEnum.VALKYRIE));
        elfButton.setGraphic(generateImageView(ImageEnum.ELF));
        wizardButton.setGraphic(generateImageView(ImageEnum.WIZARD));

    }
}

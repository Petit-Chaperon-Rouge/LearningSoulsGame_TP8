package lsg;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lsg.characters.Hero;
import lsg.characters.Zombie;
import lsg.graphics.CSSFactory;
import lsg.graphics.ImageFactory;
import lsg.graphics.panes.AnimationPane;
import lsg.graphics.panes.CreationPane;
import lsg.graphics.panes.HUDPane;
import lsg.graphics.panes.TitlePane;
import lsg.graphics.widgets.characters.renderers.HeroRenderer;
import lsg.graphics.widgets.characters.renderers.ZombieRenderer;
import lsg.graphics.widgets.texts.GameLabel;
import lsg.weapons.Sword;

/**
 * Created by alecoeuc on 08/01/18.
 */
public class LearningSoulsGameApplication extends Application {

    private Scene scene;
    private AnchorPane root;
    private TitlePane gameTitle;
    private CreationPane creationPane;
    private String heroName;
    private AnimationPane animationPane;
    private Hero hero;
    private HeroRenderer heroRenderer;
    private Zombie zombie;
    private ZombieRenderer zombieRenderer;
    private HUDPane hudPane;

    private static final double height = 800;
    private static final double width = 1200;



    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Learning Souls Game");

        root = new AnchorPane();

        scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        buildUI();
        addListeners();
        primaryStage.show();
        startGame();
    }

    private void buildUI() {
        scene.getStylesheets().addAll(CSSFactory.getStyleSheet("LSG.css"));
        gameTitle = new TitlePane(scene, "Test d'utilisation du Label");
        root.getChildren().addAll(gameTitle);
        AnchorPane.setLeftAnchor(gameTitle, 0.0);
        AnchorPane.setTopAnchor(gameTitle, 5.0);
        AnchorPane.setRightAnchor(gameTitle, 0.0);

        creationPane = new CreationPane();
        creationPane.setOpacity(0);
        root.getChildren().addAll(creationPane);
        AnchorPane.setLeftAnchor(creationPane, 0.0);
        AnchorPane.setTopAnchor(creationPane, 0.0);
        AnchorPane.setRightAnchor(creationPane, 0.0);
        AnchorPane.setBottomAnchor(creationPane, 0.0);

        animationPane = new AnimationPane(root);

        hudPane = new HUDPane();
        AnchorPane.setLeftAnchor(hudPane, 0.0);
        AnchorPane.setTopAnchor(hudPane, 0.0);
        AnchorPane.setRightAnchor(hudPane, 0.0);
        AnchorPane.setBottomAnchor(hudPane, 0.0);
    }

    public void startGame() {
        gameTitle.zoomIn((event) -> {
            creationPane.fadeIn((event1) -> {
                ImageFactory.preloadAll(() -> {
                    System.out.println("Pré-chargement des images terminé");
                });
            });
        });
        System.out.println("Animation lancée !");
    }

    private void addListeners() {
        creationPane.getNameField().setOnAction(((event) -> {
            heroName = creationPane.getNameField().getText();
            System.out.println("Nom du héro : " + heroName);
            if(!heroName.equals("")) {
                root.getChildren().remove(creationPane);
                gameTitle.zoomOut((event1) -> {
                    play();
                });
            }
        }));
    }

    private void play() {
        root.getChildren().addAll(animationPane);
        root.getChildren().addAll(hudPane);
        createHero();
        createMonster((event) -> {hudPane.getMessagePane().showMessage("FIGHT !");});
    }

    private void createHero() {
        hero = new Hero(heroName);
        hero.setWeapon(new Sword());
        heroRenderer = animationPane.createHeroRenderer();
        heroRenderer.goTo(animationPane.getPrefWidth()*0.5 - heroRenderer.getFitWidth()*0.65, null);
    }

    private void createMonster(EventHandler<ActionEvent> finishedHandler) {
        zombie = new Zombie();
        zombieRenderer = animationPane.createZombieRenderer();
        zombieRenderer.goTo(animationPane.getPrefWidth()*0.5 - zombieRenderer.getBoundsInLocal().getWidth() * 0.15, finishedHandler);
    }
}

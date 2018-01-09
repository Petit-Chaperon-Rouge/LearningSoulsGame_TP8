package lsg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lsg.graphics.CSSFactory;
import lsg.graphics.ImageFactory;
import lsg.graphics.panes.AnimationPane;
import lsg.graphics.panes.CreationPane;
import lsg.graphics.panes.TitlePane;
import lsg.graphics.widgets.texts.GameLabel;

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
        creationPane.getNameField().setOnAction((event -> {
            heroName = creationPane.getNameField().getText();
            System.out.println("Nom du héro : " + heroName);
            if(!heroName.equals("")) {
                root.getChildren().remove(creationPane);
                gameTitle.zoomOut(event1 -> {
                    play();
                });
            }
        }));
    }

    private void play() {
        root.getChildren().addAll(animationPane);
        animationPane.startDemo();
    }
}

package lsg.graphics.panes;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import javafx.scene.control.TextField;
import javafx.util.Duration;
import lsg.graphics.widgets.texts.GameLabel;

/**
 * Created by alecoeuc on 09/01/18.
 */
public class CreationPane extends VBox {

    private TextField nameField;
    private GameLabel titleLabel;

    private static final double FIELD_WIDTH = 200;
    private static final Duration ANIMATION_DURATION = Duration.millis(1500);


    public TextField getNameField() {
        return this.nameField;
    }


    public CreationPane() {
        super();
        nameField = new TextField();
        nameField.setMaxWidth(FIELD_WIDTH);
        titleLabel = new GameLabel("Player Name");
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(titleLabel);
        this.getChildren().addAll(nameField);
    }


    public void fadeIn(EventHandler<ActionEvent> finishedHandler) {
        FadeTransition ft = new FadeTransition(ANIMATION_DURATION, this);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.play();
    }

}

package lsg.graphics.panes;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

/**
 * Created by alecoeuc on 09/01/18.
 */
public class HUDPane extends BorderPane {

    private MessagePane messagePane;


    public MessagePane getMessagePane() {
        return this.messagePane;
    }


    public HUDPane() {
        buildCenter();
    }


    public void buildCenter() {
        messagePane = new MessagePane();
        messagePane.setAlignment(Pos.CENTER);
        this.getChildren().add(messagePane);

    }

}

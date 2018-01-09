package lsg.graphics.panes;

import javafx.scene.layout.VBox;
import lsg.graphics.widgets.texts.GameLabel;

/**
 * Created by alecoeuc on 09/01/18.
 */
public class MessagePane extends VBox {

    public void showMessage(String msg){
        GameLabel toShow = new GameLabel(msg);
        this.getChildren().add(toShow);
    }

}

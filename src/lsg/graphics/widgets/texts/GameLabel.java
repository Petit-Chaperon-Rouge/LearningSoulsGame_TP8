package lsg.graphics.widgets.texts;

import javafx.scene.Node;
import javafx.scene.control.Label;
import lsg.graphics.CSSFactory;

/**
 * Created by alecoeuc on 08/01/18.
 */
public class GameLabel extends Label {

    public GameLabel() {
        super();
        initFont();
    }

    public GameLabel(String text) {
        super(text);
        initFont();
    }

    public GameLabel(String text, Node graphic) {
        super(text, graphic);
        initFont();
    }


    private void initFont() {
        this.getStylesheets().addAll(CSSFactory.getStyleSheet("LSGFont.css"));
        this.getStyleClass().addAll("game-font");
        this.getStyleClass().addAll("game-font-fx");
    }

}

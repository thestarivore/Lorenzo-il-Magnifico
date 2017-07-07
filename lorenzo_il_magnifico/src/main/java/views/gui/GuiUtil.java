package views.gui;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Created by Mattia on 04/07/2017.
 */
public class GuiUtil {

    public static final String devCardPath = "/image/LorenzoCards_compressed_png/";

    public static String getDevCardURL(String string) {
            String url = devCardPath + string +".png";
            return url;
    }

    public  Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (gridPane.getColumnIndex(node) == col && gridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}

package BookStoreApp;

import java.util.ArrayList;
import javafx.scene.Scene;
/**
 *
 * @author picku
 */
public interface AppState {
    public void handle(BookStoreApp context);
}
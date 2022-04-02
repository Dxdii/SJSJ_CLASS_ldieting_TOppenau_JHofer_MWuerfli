package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class Controller_Typ1{
    @FXML
    private RadioButton Ja;
    @FXML
    private Label heading;
    @FXML
    private RadioButton nein;
    private String select;

    public void ChangeLabel(String text){
        heading.setText(text);
    }
    public String Answer(){
        return select;
    }
    public void NextQuestion(ActionEvent event){
        ToggleGroup group = new ToggleGroup();
        Ja.setToggleGroup(group);
        nein.setToggleGroup(group);
        String str = String.valueOf(group.getSelectedToggle());
        String[] split = str.split("'");
        select = split[1];
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void Select(){
        Ja.setSelected(true);
    }

}

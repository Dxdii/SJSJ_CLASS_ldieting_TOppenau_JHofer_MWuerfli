package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Controller_Typ2{
    @FXML
    private Label heading;
    @FXML
    public Slider slide;


    public void ChangeLabel(String text){
        heading.setText(text);
    }
    public void SetMin(Integer min){
        slide.setMin(min);
    }
    public void SetMax(Integer max){
        slide.setMax(max);
    }
    public String Slide(){
        return String.valueOf(slide.getValue());
    }
    public void NextQuestion(ActionEvent event){
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}

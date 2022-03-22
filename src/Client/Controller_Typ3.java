package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_Typ3 {

    @FXML
    private TextField field;
    @FXML
    private Label question;

    @FXML
    void NextQuestion(ActionEvent event) {
        closeStage(event);
    }


    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public String Text(){
        return field.getText();
    }

    public void ChangeLabel(String question){
        this.question.setText(question);
    }

}
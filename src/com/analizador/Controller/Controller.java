package com.analizador.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnCheck;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> token;

    @FXML
    private TableColumn<?, ?> descripcion;


    @FXML
    void actionBtn(ActionEvent event) {
        txtArea.getText(); //trae los datos del txtArea
    }
}

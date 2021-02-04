package com.analizador.Controller;

import com.analizador.Model.AnalizadorLexico;
import com.analizador.Model.MostarInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Controller {
    AnalizadorLexico analizador = new AnalizadorLexico();

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnCheck;

    @FXML
    private TableView<MostarInfo> tableView;

    @FXML
    private TableColumn<MostarInfo, String> token;

    @FXML
    private TableColumn<MostarInfo, String> descripcion;


    @FXML
    void actionBtn() {
        String text = txtArea.getText();
        tableView.getItems().clear();
        if (!text.isEmpty()){
            ArrayList<MostarInfo> info = analizador.analizar(text);
            token.setCellValueFactory(new PropertyValueFactory<>("word"));
            descripcion.setCellValueFactory(new PropertyValueFactory<>("description"));
            tableView.getItems().addAll(info);
        }
    }
}

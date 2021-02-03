package com.analizador.Controller;

import com.analizador.Model.AnalizadorLexico;
import com.analizador.Model.MostarInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Controller {
    AnalizadorLexico analizador = new AnalizadorLexico();

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
    void actionBtn() {
        String text = txtArea.getText(); //trae los datos del txtArea
        if (text.isEmpty()){
            System.out.println("Esta vacio we");
        } else {
            ArrayList<MostarInfo> info = analizador.analizar(text);
            System.out.println(info);
        }
    }
}

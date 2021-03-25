package com.analizador.Controller;

import com.analizador.Model.AnalizadorLexico;
import com.analizador.Model.AnalizadorSemantico;
import com.analizador.Model.MostarInfo;
import com.analizador.Model.Sintaxis;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Controller {
    AnalizadorLexico analizador = new AnalizadorLexico();
    Sintaxis sintaxis = new Sintaxis();
    AnalizadorSemantico semantico = new AnalizadorSemantico();

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

            MostarInfo error = analizador.detectaError(info);
            if (error.getWord().equals("Bien")){
                boolean analizado = sintaxis.analizar(info);
                if (analizado){
                    semantico.prueba(info);
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Correcto");
                    alerta.setHeaderText("Ejecucion finalizada");
                    alerta.setContentText("Finalizo exitosamente");
                    alerta.showAndWait();
                }
                System.out.println("------------------------------------------------------------------------");
            }
        }
    }
}

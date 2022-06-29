package com.example.tiabokyfx.Controller;


import com.example.tiabokyfx.Database.DatabaseConnection;
import com.example.tiabokyfx.Model.Lecteur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    Stage stage;

    @FXML
    private Button LivresBtn;
    @FXML
    private Button PretsBtn;
    @FXML
    private Button lecteursBtn;

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private DatePicker naissanceField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField telephoneField;

    @FXML
    private TableView<Lecteur> lecteursTable;
    @FXML
    private TableColumn<Lecteur, Integer> numeroCol;
    @FXML
    private TableColumn<Lecteur, String> nomCol;
    @FXML
    private TableColumn<Lecteur, String> prenomCol;
    @FXML
    private TableColumn<Lecteur, String> naissanceCol;
    @FXML
    private TableColumn<Lecteur, String> adresseCol;
    @FXML
    private TableColumn<Lecteur, String> telephoneCol;

    ObservableList<Lecteur> listLECTEURS;

    int index = -1;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @FXML
    void OnLecteursBtnClicked(ActionEvent event) {
        initButton();
        lecteursBtn.setStyle("-fx-background-color : rgb(0, 176, 255);");
    }
    @FXML
    void OnLivresBtnClicked(ActionEvent event) {

        initButton();
        LivresBtn.setStyle("-fx-background-color : rgb(0, 176, 255);");
    }
    @FXML
    void OnPretsBtnClicked(ActionEvent event) {
        initButton();
        PretsBtn.setStyle("-fx-background-color : rgb(0, 176, 255);");
    }
    @FXML
    void ajouterLecteurBtnClicked(ActionEvent event) {
        if(nomField.getText().isBlank() || prenomField.getText().isBlank() || adresseField.getText().isBlank() || telephoneField.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Veuiller remplir les champs correctement !!");
            alert.show();
        }else{
            Lecteur.addLecteur(nomField,prenomField,naissanceField,adresseField,telephoneField);
            refrechLecteursTable();
        }
    }

    private void refrechLecteursTable() {
        listLECTEURS = Lecteur.getLecteurs();
        lecteursTable.setItems(listLECTEURS);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        numeroCol.setCellValueFactory(new PropertyValueFactory<Lecteur, Integer>("numero"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("prenom"));
        naissanceCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("naissance"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("adresse"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<Lecteur, String>("telephone"));

        refrechLecteursTable();
    }


    public void initButton(){
        PretsBtn.setStyle("-fx-background-color : transparent;");
        lecteursBtn.setStyle("-fx-background-color : transparent;");
        LivresBtn.setStyle("-fx-background-color : transparent;");
    }
    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

}

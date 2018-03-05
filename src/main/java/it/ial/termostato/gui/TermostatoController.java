package it.ial.termostato.gui;

import it.ial.termostato.FasceDelGiorno;
import it.ial.termostato.Termostato;
import it.ial.termostato.TermostatoUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class TermostatoController {

	private ObservableList<FasceDelGiorno> data = FXCollections.observableArrayList();

	@FXML
	private TableView<FasceDelGiorno> grigliaTableView;

	@FXML
	private TableColumn<FasceDelGiorno, String> giornoColumn;

	@FXML
	public void initialize() {
		giornoColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<FasceDelGiorno, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<FasceDelGiorno, String> param) {
						return new SimpleStringProperty(param.getValue().getGiornoAsString());
					}
				});
		for (int ora = 0; ora < 24; ora++) {
			grigliaTableView.getColumns().add(creaColonna(ora));
		}
		grigliaTableView.setItems(data);
	}

	private TableColumn<FasceDelGiorno, String> creaColonna(int ora) {
		TableColumn<FasceDelGiorno, String> oraColumn = new TableColumn<>(TermostatoUtil.toOraAsString(ora));
		oraColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<FasceDelGiorno, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<FasceDelGiorno, String> param) {
						return new SimpleStringProperty(param.getValue().getFasciaOraria(ora).getFascia().name());
					}
				});
		return oraColumn;
	}

	public void setTermostato(Termostato termostato) {
		data.setAll(termostato.getGriglia());
	}
}

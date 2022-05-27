/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Statistiche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxRiver"
	private ComboBox<River> boxRiver; // Value injected by FXMLLoader

	@FXML // fx:id="txtStartDate"
	private TextField txtStartDate; // Value injected by FXMLLoader

	@FXML // fx:id="txtEndDate"
	private TextField txtEndDate; // Value injected by FXMLLoader

	@FXML // fx:id="txtNumMeasurements"
	private TextField txtNumMeasurements; // Value injected by FXMLLoader

	@FXML // fx:id="txtFMed"
	private TextField txtFMed; // Value injected by FXMLLoader

	@FXML // fx:id="txtK"
	private TextField txtK; // Value injected by FXMLLoader

	@FXML // fx:id="btnSimula"
	private Button btnSimula; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doRiempi(ActionEvent event) {
		txtResult.clear();
		River river = boxRiver.getValue();
		model.setRiverStat(river);
		txtStartDate.setText(river.getFlows().get(0).getDay().toString());
		txtEndDate.setText(river.getFlows().get(river.getFlows().size() - 1).getDay().toString());
		txtNumMeasurements.setText(river.getFlows().size() + "");
		txtFMed.setText(river.getFlowAvg() + "");
	}

	@FXML
	void handleSimula(ActionEvent event) {
		txtResult.clear();
		River river = boxRiver.getValue();
		String fattore = txtK.getText();
		double k = 0;
		if (river == null && fattore == "")
			txtResult.setText("Selezionare un fiume e inserire un fattore di scala k>0.");
		else if (river == null)
			txtResult.setText("Selezionare un fiume.");
		else if (fattore == "")
			txtResult.setText("Inserire un fattore di scala.");
		else if (!toDouble(fattore))
			txtResult.setText("Inserire un fattore di scala numerico.");
		else if (Double.parseDouble(fattore) <= 0)
			txtResult.setText("Inserire un fattore di scala k>0.");
		else {
			k = Double.parseDouble(fattore);
			// System.out.println(k);
			Statistiche statistiche = model.simula(river, k);
			txtResult.setText("Numero di giorni in cui non si è potuta garantire l’erogazione minima: "
					+ statistiche.getNumGiorniNegativi() + "\n");
			txtResult.appendText("Occupazione media Cmed del bacino nel corso della simulazione: "
					+ statistiche.getOccupazioneMedia());
		}
	}

	private boolean toDouble(String fattore) {
		try {
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNumMeasurements != null
				: "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	public void setModel(Model model) {
		this.model = model;

		List<River> allRivers = new ArrayList<River>(model.getAllRivers());
		Collections.sort(allRivers);
		boxRiver.getItems().addAll(allRivers);
	}
}

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	Dictionary d;
	
	/**
	 * @param d the d to set
	 */
	public void setD(Dictionary d) {
		this.d = d;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private TextArea txtUserMessage;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label txtInfoErrors;

    @FXML
    private Button btnClearText;

    @FXML
    private Label txtInfoTime;

    @FXML
    void doClearText(ActionEvent event) {
    	
    	d.reset();
    	txtUserMessage.clear();
    	txtResult.clear();
    	txtInfoErrors.setText("");
    	txtInfoTime.setText("");
    	
    	
    	

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	Long l1 = System.nanoTime();
    	
        txtResult.setText("");
    	
    	String input = txtUserMessage.getText().toLowerCase();
    	List<String> testoUtente = new LinkedList<String>();
    	List<RichWord> testoUtenteControllato = new LinkedList<RichWord>();
    	int numeroErrori=0;
    	
    	StringTokenizer st = new StringTokenizer( input, " "); 
    	
    	
    	while (st.hasMoreTokens()){
    		String parola = st.nextToken().replaceAll("[ \\p{Punct}]", "");
    		testoUtente.add(parola);
    	}
    	
    	d.loadDictionary(cmbLanguage.getValue());
    	
    	
    	testoUtenteControllato = d.spellCheckText(testoUtente);
    	
    	
    	for (RichWord r : testoUtenteControllato){
    		if (!r.isCorretta()){
    			txtResult.appendText(r.getParola()+"\n");
    			numeroErrori++;
    		}
    	}
    	Long l2 = System.nanoTime();
    	Long result = (long) (l2-l1);

    	
    	txtInfoErrors.setText("The text contains "+numeroErrori+" errors.");
    	txtInfoTime.setText("Spell check completed in "+result+" nanoseconds.");
    	
        

    }

    @FXML
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtUserMessage != null : "fx:id=\"txtUserMessage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInfoErrors != null : "fx:id=\"txtInfoErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInfoTime != null : "fx:id=\"txtInfoTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        cmbLanguage.getItems().addAll("English", "Italian");
        
        if (cmbLanguage.getItems().size() > 0)
            cmbLanguage.setValue(cmbLanguage.getItems().get(0));
    
    }
}

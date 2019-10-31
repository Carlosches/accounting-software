package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.PhysicalFlow;
import model.Process;

public class AccountingController {
	  @FXML
	  private TextField ioPPText;
	  
	  @FXML
	  private TextField ifPPText;

	  @FXML
	  private GridPane prodEquivPP;

	  @FXML
	  private TextField started;

	  @FXML
	  private GridPane prodEquivPeps;

	  @FXML
	  private TextField finished;

    
    
    
    private Process process;
    @FXML
    void initialize() {
    	process = new Process();
    }
    @FXML
    void fillFlowButton(ActionEvent event) {
    	String ioPP = ioPPText.getText();
    	String ifPP = ifPPText.getText();
    	String st = started.getText();
    	String fi = finished.getText();
    	int iPP = 0;
    	int fPP = 0;
    	int start = 0;
    	int finish = 0;
    	
    	if(ioPP.isEmpty()) {
    		iPP = -1;
    	}else {
    		iPP = Integer.parseInt(ioPP);
    	}
    	
    	if(ifPP.isEmpty()) {
    		fPP = -1;
    	}else {
    		fPP = Integer.parseInt(ifPP);
    	}
    	
    	if(st.isEmpty()) {
    		start = -1;
    	}else {
    		start = Integer.parseInt(st);
    	}
    	
    	if(fi.isEmpty()) {
    		finish = -1;
    	}else {
    		finish = Integer.parseInt(fi);
    	}
    	process.setFlow(new PhysicalFlow(iPP, fPP, start, finish));
    	
    	if(process.getFlow().check()) {
    		ioPPText.setText(""+process.getFlow().getIOPP().getUnits());
    		ifPPText.setText(""+process.getFlow().getIFPP().getUnits());
    		started.setText(""+process.getFlow().getStarted().getUnits());
    		finished.setText(""+process.getFlow().getFinished());
    	}
    }
}

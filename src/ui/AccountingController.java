package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.PhysicalFlow;
import model.Process;
import model.Status;

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

	@FXML
	private TextField previousUnits;

    @FXML
    private TextField startedAndFinished;
	
    @FXML
    private TextField ioPPMODPercent;

    @FXML
    private TextField ioPPMDPercent;

    @FXML
    private TextField ioPPCIFPercent;

    @FXML
    private TextField ifPPMDPercent;

    @FXML
    private TextField ifPPMODPercent;

    @FXML
    private TextField ifPPCIFPercent;
    
    @FXML
    private TextField addedCostMD;

    @FXML
    private TextField addedCostMOD;

    @FXML
    private TextField addedCostCIF;
    
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
		String pu = previousUnits.getText();
		String sAF = startedAndFinished.getText();
		String aCMD = addedCostMD.getText();
		String aCMOD = addedCostMOD.getText();
		String aCCIF = addedCostCIF.getText();
		int iPP = 0;
		int fPP = 0;
		int start = 0;
		int finish = 0;
		int previous = 0;
		int startedFinished = 0;
		double addedCostMD = 0;
		double addedCostMOD = 0;
		double addedCostCIF = 0;
		
		if(!checkPercentages())
			return;
		
		try {
			addedCostMD = Double.parseDouble(aCMD);
		}catch(NumberFormatException e) {
			return;
		}
		try {
			addedCostMOD = Double.parseDouble(aCMOD);
		}catch(NumberFormatException e) {
			return;
		}
		try {
			addedCostCIF = Double.parseDouble(aCCIF);
		}catch(NumberFormatException e) {
			return;
		}
		
		if(ioPP.isEmpty()) {
			iPP = -1;
		}else {
			try {
				iPP = Integer.parseInt(ioPP);
			}catch(NumberFormatException e) {
				ioPPText.clear();
				ioPPText.setPromptText("Please insert a valid number");
				ifPPText.clear();
				started.clear();
				finished.clear();
				previousUnits.clear();
				startedAndFinished.clear();
				return;
			}
		}
		
		if(ifPP.isEmpty()) {
			fPP = -1;
		}else {
			try {
			fPP = Integer.parseInt(ifPP);
			}catch(NumberFormatException e) {
				ifPPText.clear();
				ifPPText.setPromptText("Please insert a valid number");
				ioPPText.clear();
				started.clear();
				finished.clear();
				previousUnits.clear();
				startedAndFinished.clear();
				return;
			}
			
		}

		if(st.isEmpty()) {
			start = -1;
		}else {
			try {
			start = Integer.parseInt(st);
			}catch(NumberFormatException e) {
				started.clear();
				started.setPromptText("Please insert a valid number");
				ifPPText.clear();
				ioPPText.clear();
				finished.clear();
				previousUnits.clear();
				startedAndFinished.clear();
				return;
			}
		}

		if(fi.isEmpty()) {
			finish = -1;
		}else {
			try {
			finish = Integer.parseInt(fi);
			}catch(NumberFormatException e) {
				finished.clear();
				finished.setPromptText("Please insert a valid number");
				ifPPText.clear();
				started.clear();
				ioPPText.clear();
				previousUnits.clear();
				startedAndFinished.clear();
				return;
			}
		}
		if(pu.isEmpty()) {
			previous = -1;
		}else {
			try {
			previous = Integer.parseInt(pu);
			}catch(NumberFormatException e) {
				previousUnits.clear();
				previousUnits.setPromptText("Please insert a valid number");
				ifPPText.clear();
				started.clear();
				finished.clear();
				ioPPText.clear();
				startedAndFinished.clear();
				return;
			}
		}
		if(sAF.isEmpty()) {
			startedFinished = -1;
		}else {
			try {
			startedFinished = Integer.parseInt(sAF);
			}catch(NumberFormatException e) {
				startedAndFinished.clear();
				startedAndFinished.setPromptText("Please insert a valid number");
				ifPPText.clear();
				started.clear();
				finished.clear();
				ioPPText.clear();
				previousUnits.clear();
				return;
			}
		}
		process.setFlow(new PhysicalFlow(iPP, fPP, start, finish, previous, startedFinished));
		if(process.getFlow().check()) {
			//Status s1 = new Status((100 - Double.parseDouble(ioPPMDPercent.getText()))/100, (100 - Double.parseDouble(ioPPMODPercent.getText()))/100, (100 - Double.parseDouble(ioPPCIFPercent.getText()))/100, mDvalue, mODvalue, cIFvalue, cTvalue)
			ioPPText.setText(String.valueOf(process.getFlow().getiOPP().getUnits()));
			ifPPText.setText(String.valueOf(process.getFlow().getiFPP().getUnits()));
			started.setText(String.valueOf(process.getFlow().getStarted().getUnits()));
			finished.setText(String.valueOf(process.getFlow().getFinished()));
			previousUnits.setText(String.valueOf(process.getFlow().getPrevious()));
			startedAndFinished.setText(String.valueOf(process.getFlow().getStartedFinished()));
			process.setAddedCostMD(addedCostMD);
			process.setAddedCostMOD(addedCostMOD);
			process.setAddedCostCIF(addedCostCIF);
			process.getPeps();
			process.getPP();
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					prodEquivPeps.add(new Label(String.valueOf(process.getProduction().getPeps()[i][j])), j, i);
				}
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 4; j++) {
					prodEquivPP.add(new Label(String.valueOf(process.getProduction().getPP()[i][j])), j, i);
				}
			}
		}else {
			startedAndFinished.clear();
			ifPPText.clear();
			started.clear();
			finished.clear();
			ioPPText.clear();
			previousUnits.clear();
			startedAndFinished.setPromptText("Please insert a valid number");
			ifPPText.setPromptText("Please insert a valid number");
			started.setPromptText("Please insert a valid number");
			finished.setPromptText("Please insert a valid number");
			ioPPText.setPromptText("Please insert a valid number");
			previousUnits.setPromptText("Please insert a valid number");
		}
	}
	private boolean checkPercentages() {
		boolean possible = true;
		possible = checkSinglePercentage(ioPPMDPercent);
		possible = checkSinglePercentage(ioPPMODPercent);
		possible = checkSinglePercentage(ioPPCIFPercent);
		possible = checkSinglePercentage(ifPPMDPercent);
		possible = checkSinglePercentage(ifPPMODPercent);
		possible = checkSinglePercentage(ifPPCIFPercent);
		return possible;
	}
	
	private boolean checkSinglePercentage(TextField tf) {
		boolean possible = true;
		int ans = 0;
		try {
			ans = Integer.parseInt(tf.getText());
		}catch(NumberFormatException e) {
			possible = false;
		}
		if(ans < 0 || ans > 100)
			possible = false;
		return possible;
	}
}

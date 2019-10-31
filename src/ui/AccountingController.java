package ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private TextField started;

    @FXML
    private TextField addedCostMD;

    @FXML
    private TextField addedCostMOD;

    @FXML
    private TextField addedCostCIF;

    @FXML
    private TextField transfCosts;

    @FXML
    private TextField finished;

    @FXML
    private TextField startedAndFinished;

    @FXML
    private TextField previousUnits;

    @FXML
    private TextField ioPPMODPercent;

    @FXML
    private TextField ioPPMDPercent;

    @FXML
    private TextField ioPPCIFPercent;

    @FXML
    private TextField valueCIF;

    @FXML
    private TextField valueMOD;

    @FXML
    private TextField valueMD;

    @FXML
    private TextField valueTC;

    @FXML
    private TextField ifPPMDPercent;

    @FXML
    private TextField ifPPMODPercent;

    @FXML
    private TextField ifPPCIFPercent;

    @FXML
    private TextField transfCostUnits;

    @FXML
    private GridPane prodEquivPeps;

    @FXML
    private GridPane prodEquivPP;

    @FXML
    private GridPane unitaryCostPeps;

    @FXML
    private GridPane finishedProductPeps;

    @FXML
    private GridPane inProcessProductPeps;

    @FXML
    private Label totalAssignedCostsPeps;

    @FXML
    private GridPane unitaryCostPP;

    @FXML
    private GridPane costAssignationPP;

    @FXML
    private Label totalAssignedCostsPP;
    
	private Process process;
	
	@FXML
	void initialize() {
		process = new Process();
	}
	@FXML
	void fillFlowButton(ActionEvent event) {
		boolean possible = true;
		String ioPP = ioPPText.getText();
		String ifPP = ifPPText.getText();
		String st = started.getText();
		String fi = finished.getText();
		String pu = previousUnits.getText();
		String sAF = startedAndFinished.getText();
		String aCMD = addedCostMD.getText();
		String aCMOD = addedCostMOD.getText();
		String aCCIF = addedCostCIF.getText();
		String vMD = valueMD.getText();
		String vMOD = valueMOD.getText();
		String vCIF = valueCIF.getText();
		String vTC = valueTC.getText();
		int iPP = checkFields(ioPP);
		int fPP = checkFields(ifPP);
		int start = checkFields(st);
		int finish = checkFields(fi);
		int previous = checkFields(pu);
		int startedFinished = checkFields(sAF);	
		double addedCostMD = -1;
		double addedCostMOD = -1;
		double addedCostCIF = -1;
		double ioMD = -1;
		double ioMOD = -1;
		double ioCIF = -1;
		double ioTC = 0;
		possible = checkTC();
		try {
			addedCostMD = Double.parseDouble(aCMD);
			addedCostMOD = Double.parseDouble(aCMOD);
			addedCostCIF = Double.parseDouble(aCCIF);
			ioMD = Double.parseDouble(vMD);
			ioMOD = Double.parseDouble(vMOD);
			ioCIF = Double.parseDouble(vCIF);
			if(!transfCosts.getText().isEmpty() && !transfCostUnits.getText().isEmpty())
				ioTC = Double.parseDouble(vTC);
		}catch(NumberFormatException e) { ioTC = -1;}
		
		if(!checkPercentages() || iPP == -1 || fPP == -1 || start == -1 || finish == -1 || previous == -1 || startedFinished == -1 || addedCostMD == -1 || addedCostMOD == -1 || addedCostCIF == -1)
			possible = false;
		
		if(possible) {
			process.setFlow(new PhysicalFlow(iPP, fPP, start, finish, previous, startedFinished));
			double tc = 0;
			int tcUnit = 0;
			if(!transfCosts.getText().isEmpty() && !transfCostUnits.getText().isEmpty()) {
				tc = Double.parseDouble(transfCosts.getText());
				tcUnit = Integer.parseInt(transfCostUnits.getText());
			}
			
			process.setTransferedCost(tc);
			if(process.getFlow().check()) {
				Status s1 = new Status((100 - Double.parseDouble(ioPPMDPercent.getText()))/100, (100 - Double.parseDouble(ioPPMODPercent.getText()))/100, (100 - Double.parseDouble(ioPPCIFPercent.getText()))/100, ioMD, ioMOD, ioCIF, ioTC);
				Status s2 = new Status(Double.parseDouble(ifPPMDPercent.getText())/100, Double.parseDouble(ifPPMODPercent.getText())/100, Double.parseDouble(ifPPCIFPercent.getText())/100, 0, 0, 0, 0);
				process.getFlow().getiOPP().setStatus(s1);
				process.getFlow().getiFPP().setStatus(s2);
				ioPPText.setText(String.valueOf(process.getFlow().getiOPP().getUnits()));
				ifPPText.setText(String.valueOf(process.getFlow().getiFPP().getUnits()));
				started.setText(String.valueOf(process.getFlow().getStarted().getUnits()));
				finished.setText(String.valueOf(process.getFlow().getFinished()));
				previousUnits.setText(String.valueOf(process.getFlow().getPrevious()));
				startedAndFinished.setText(String.valueOf(process.getFlow().getStartedFinished()));
				process.setAddedCostMD(addedCostMD);
				process.setAddedCostMOD(addedCostMOD);
				process.setAddedCostCIF(addedCostCIF);
				process.setIOPP(ioMD + ioMOD + ioCIF + ioTC);
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
				double[] unit = process.calculateUnitaryCosts();
				double[] peps = process.getAssignedCostsPeps();
				double[] pp = process.getAssignedCostsPP();
				showAssignedCosts(unit, peps, pp);
				
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
		}else {
			Alert alert = new Alert(AlertType.ERROR, "Please insert valid values");
			alert.showAndWait();
		}
	}
	
	private void showAssignedCosts(double[] unit, double[] peps, double[] pp) {
		for (int i = 0; i < 5; i++) {
			unitaryCostPeps.add(new Label(String.valueOf(unit[i])), 1, i);
		}
		for (int i = 0; i < 5; i++) {
			unitaryCostPP.add(new Label(String.valueOf(unit[i+5])), 1, i);
		}
		for(int i = 0; i < 7; i++) {
			finishedProductPeps.add(new Label(String.valueOf(peps[i])), 1, i);
		}
		for(int i = 0; i < 6; i++) {
			inProcessProductPeps.add(new Label(String.valueOf(peps[i+7])), 1, i);
		}
		totalAssignedCostsPeps.setText(String.valueOf(peps[peps.length-1]));
		for(int i = 0; i < 5; i++) {
			costAssignationPP.add(new Label(String.valueOf(pp[i])), 1, i);
		}
		totalAssignedCostsPP.setText(String.valueOf(pp[pp.length-1]));
	}
	
	private boolean checkTC() {
		boolean possible = true;
		if((transfCosts.getText().isEmpty() && !transfCostUnits.getText().isEmpty()) || (!transfCosts.getText().isEmpty() && transfCostUnits.getText().isEmpty()))
			possible = false;
		if(!transfCosts.getText().isEmpty() && !transfCostUnits.getText().isEmpty()) {
			double cost = 0;
			int unit = 0;
			try {
				cost = Double.parseDouble(transfCosts.getText());
				unit = Integer.parseInt(transfCostUnits.getText());
			}catch(NumberFormatException e) {
				possible = false;
			}
		}
		return possible;
	}
	
	private int checkFields(String s) {
		int ans = -1;
		if(s.isEmpty()) {
			ans = 0;
		}else {
			try {
				ans = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				startedAndFinished.clear();
				ifPPText.clear();
				started.clear();
				finished.clear();
				ioPPText.clear();
				previousUnits.clear();
			}
		}
		return ans;
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

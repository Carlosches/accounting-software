package model;

public class Process {
	
	private PhysicalFlow flow;
	private EquivalentProduction production;
	private CostAllocation costs;
	private double addedCostMD;
	private double addedCostMOD;
	private double addedCostCIF;
	private double ioPP;
	private double transferedCost;
	
	/**
	 * @param flow
	 * @param production
	 * @param costs
	 */
	public Process() {
		production = new EquivalentProduction();
	}
	/**
	 * @return the transferedCost
	 */
	public double getTransferedCost() {
		return transferedCost;
	}
	/**
	 * @return the flow
	 */
	public PhysicalFlow getFlow() {
		return flow;
	}
	/**
	 * @param flow the flow to set
	 */
	public void setFlow(PhysicalFlow flow) {
		this.flow = flow;
	}
	/**
	 * @return the production
	 */
	public EquivalentProduction getProduction() {
		return production;
	}
	/**
	 * @param production the production to set
	 */
	public void setProduction(EquivalentProduction production) {
		this.production = production;
	}
	/**
	 * @return the costs
	 */
	public CostAllocation getCosts() {
		return costs;
	}
	/**
	 * @param costs the costs to set
	 */
	public void setCosts(CostAllocation costs) {
		this.costs = costs;
	}
	
	public void getPeps() {
		production.calculatePeps(flow.getiOPP().getStatus(), flow.getiFPP().getStatus(), flow.getiOPP().getUnits(), flow.getStartedFinished(), flow.getiFPP().getUnits());
	}
	
	
	public void getPP() {
		production.calculatePP(flow.getiFPP().getStatus(), flow.getFinished(), flow.getiFPP().getUnits());
	}
	
	public void setAddedCostMD(double addedCostMD) {
		this.addedCostMD = addedCostMD;
	}
	
	public void setAddedCostMOD(double addedCostMOD) {
		this.addedCostMOD = addedCostMOD;
	}
	
	public void setAddedCostCIF(double addedCostCIF) {
		this.addedCostCIF = addedCostCIF;
	}

	public void setTransferedCost(double transferedCost) {
		this.transferedCost = transferedCost;
	}
	
	public double[] calculateUnitaryCosts() {
		double unitaryTCPeps = flow.getiOPP().getStatus().getCTvalue()/flow.getiOPP().getUnits();
		double unitaryTCPP = (transferedCost + flow.getiOPP().getStatus().getCTvalue())/(flow.getStarted().getUnits() + flow.getiOPP().getUnits());
		double unitaryMDPeps = addedCostMD / production.getPeps()[3][1];
		double unitaryMODPeps = addedCostMOD / production.getPeps()[3][2];
		double unitaryCIFPeps = addedCostCIF / production.getPeps()[3][3];
		double unitaryMDPP = (addedCostMD+flow.getiOPP().getStatus().getMDvalue()) / production.getPP()[2][1];
		double unitaryMODPP = (addedCostMOD+flow.getiOPP().getStatus().getMODvalue()) / production.getPP()[2][2];
		double unitaryCIFPP = (addedCostCIF+flow.getiOPP().getStatus().getCIFvalue()) / production.getPP()[2][3];
		costs = new CostAllocation(unitaryTCPeps, unitaryTCPP,unitaryMDPeps, unitaryMODPeps, unitaryCIFPeps, unitaryMDPP, unitaryMODPP, unitaryCIFPP);
		double[] arr = {unitaryTCPeps, unitaryMDPeps, unitaryMODPeps, unitaryCIFPeps, unitaryTCPeps+unitaryMDPeps+unitaryMODPeps+unitaryCIFPeps, unitaryTCPP,
				unitaryMDPP, unitaryMODPP, unitaryCIFPP, unitaryTCPP+unitaryMDPP+unitaryMODPP+unitaryCIFPP};
		return arr;
	}
	
	
	/**
	 * @return the addedCostMD
	 */
	public double getAddedCostMD() {
		return addedCostMD;
	}
	/**
	 * @return the addedCostMOD
	 */
	public double getAddedCostMOD() {
		return addedCostMOD;
	}
	/**
	 * @return the addedCostCIF
	 */
	public double getAddedCostCIF() {
		return addedCostCIF;
	}
	/**
	 * @return the ioPP
	 */
	public double getIoPP() {
		return ioPP;
	}
	public double[] getAssignedCostsPeps() {
		return costs.calculateAssignedCostsPeps(production.getPeps(), ioPP);
	}
	
	public double[] getAssignedCostsPP() {
		return costs.calculateAssignedCostsPP(production.getPP());
	}
	public void setIOPP(double ioPP) {
		this.ioPP = ioPP;
	}
	
	public double getIOPP() {
		return ioPP;
	}
}

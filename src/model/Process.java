package model;

public class Process {
	
	private PhysicalFlow flow;
	private EquivalentProduction production;
	private CostAllocation costs;
	private double addedCostMD;
	private double addedCostMOD;
	private double addedCostCIF;
	
	/**
	 * @param flow
	 * @param production
	 * @param costs
	 */
	public Process() {
		EquivalentProduction production = new EquivalentProduction();
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
	
	public void calculateUnitaryCosts() {
		double unitaryTC = 0;
		double unitaryMDPeps = addedCostMD / production.getPeps()[3][1];
		double unitaryMODPeps = addedCostMOD / production.getPeps()[3][2];
		double unitaryCIFPeps = addedCostCIF / production.getPeps()[3][3];
		double unitaryMDPP = (addedCostMD+flow.getiOPP().getStatus().getMDvalue()) / production.getPeps()[3][1];
		double unitaryMODPP = (addedCostMOD+flow.getiOPP().getStatus().getMODvalue()) / production.getPeps()[3][2];
		double unitaryCIFPP = (addedCostCIF+flow.getiOPP().getStatus().getCIFvalue()) / production.getPeps()[3][3];
		costs = new CostAllocation(unitaryTC, unitaryMDPeps, unitaryMODPeps, unitaryCIFPeps, unitaryMDPP, unitaryMODPP, unitaryCIFPP);
	}
	
	public double[] getAssignedCostsPeps() {
		return costs.calculateAssignedCostsPeps(production.getPeps());
	}
	
	public double[] getAssignedCostsPP() {
		return costs.calculateAssignedCostsPP(production.getPP());
	}
}

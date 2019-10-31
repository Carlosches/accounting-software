package model;

public class Process {
	
	private PhysicalFlow flow;
	private EquivalentProduction production;
	private CostAllocation costs;
	
	
	/**
	 * @param flow
	 * @param production
	 * @param costs
	 */
	public Process() {
	
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
	
	
	
}

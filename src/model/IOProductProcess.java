package model;

public class IOProductProcess {

	private Status status;
	private int units;
	private double iOPP;
	
	
	/**
	 * @param units
	 */
	public IOProductProcess(int units) {
		this.units = units;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
		iOPP = status.getMDvalue() + status.getCIFvalue() + status.getMODvalue() + status.getCTvalue();
	}
	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}
	public double getiOPP() {
		return iOPP;
	}

	
	
}

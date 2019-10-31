package model;

public class IFProductProcess {
	private Status status;
	private int units;
	
	
	public IFProductProcess(int units) {
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
}

package model;

public class PhysicalFlow {
	
	private IOProductProcess iOPP;
	private IFProductProcess iFPP;
	private Started started;
	
	private int finishedUnits;
	private int previousUnits;
	private int startedAndFinishedUnits;
		
	/**
	 * @param iOPP
	 * @param iFPP
	 * @param started
	 */
	public PhysicalFlow(int iOPP, int iFPP, int st, int finished, int previous, int startedFinished) {
		
		if(iOPP!=-1)
			this.iOPP = new IOProductProcess(iOPP);
		if(iFPP!=-1)
			this.iFPP = new IFProductProcess(iFPP);
		if(st != -1)
			started = new Started(st);
		this.finishedUnits = finished;
		this.previousUnits = previous;
		this.startedAndFinishedUnits = startedFinished;
	}
	
	public boolean check() {
		boolean valid = false;
		if(iOPP!=null && started != null && finishedUnits!=-1 ) {
			int unit = iOPP.getUnits()+started.getUnits()-finishedUnits;
			iFPP = new IFProductProcess(unit);
			previousUnits = iOPP.getUnits();
			startedAndFinishedUnits = finishedUnits - previousUnits;
			valid = true;
		}else if(iOPP!=null && started != null && iFPP!=null ) {
			finishedUnits = iOPP.getUnits()+started.getUnits()-iFPP.getUnits();
			previousUnits = iOPP.getUnits();
			startedAndFinishedUnits = finishedUnits - previousUnits;
			valid = true;
		}else if(iFPP!=null && started != null && finishedUnits!=-1 ) {
			int unit = iFPP.getUnits()-started.getUnits()+finishedUnits;
			iOPP = new IOProductProcess(unit);
			previousUnits = unit;
			startedAndFinishedUnits = finishedUnits - previousUnits;
			valid = true;
		}else if(iFPP != null && iOPP!=null && finishedUnits!=-1 ) {
			int unit = iFPP.getUnits()-iOPP.getUnits()+finishedUnits;
			started = new Started(unit);
			previousUnits = iOPP.getUnits();
			startedAndFinishedUnits = finishedUnits - previousUnits;
			valid = true;
		}else if(startedAndFinishedUnits != -1 && iOPP!=null && started != null) {
			previousUnits = iOPP.getUnits();
			finishedUnits = previousUnits + startedAndFinishedUnits;
			iFPP = new IFProductProcess(iOPP.getUnits() + started.getUnits() - finishedUnits);
			valid = true;
		}
	
		if(valid && (iOPP.getUnits() < 0 || iFPP.getUnits() < 0 || started.getUnits() < 0 || finishedUnits < 0 || previousUnits < 0 || startedAndFinishedUnits < 0))
			valid = false;
		return valid;
	}	
	
	/**
	 * @return the iOPP
	 */
	public IOProductProcess getiOPP() {
		return iOPP;
	}
	/**
	 * @param iOPP the iOPP to set
	 */
	public void setiOPP(IOProductProcess iOPP) {
		iOPP = iOPP;
	}
	/**
	 * @return the iFPP
	 */
	public IFProductProcess getiFPP() {
		return iFPP;
	}
	/**
	 * @param iFPP the iFPP to set
	 */
	public void setiFPP(IFProductProcess iFPP) {
		iFPP = iFPP;
	}
	/**
	 * @return the started
	 */
	public Started getStarted() {
		return started;
	}
	/**
	 * @param started the started to set
	 */
	public void setStarted(Started started) {
		this.started = started;
	}
	
	/**
	 * @param started the started to set
	 */
	public int getFinished() {
		return finishedUnits;
	}
	
	/**
	 * @param started the started to set
	 */
	public int getPrevious() {
		return previousUnits;
	}
	
	/**
	 * @param started the started to set
	 */
	public int getStartedFinished() {
		return startedAndFinishedUnits;
	}
}

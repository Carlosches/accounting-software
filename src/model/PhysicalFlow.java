package model;

public class PhysicalFlow {
	
	private IOProductProcess iOPP;
	private IFProductProcess iFPP;
	private Started started;
	
	private int finishedUnits;
	private int previousUnits;
	private int startedAndFinishedUnits;
	private boolean valid;
	/**
	 * @param iOPP
	 * @param iFPP
	 * @param started
	 */
	public PhysicalFlow(int iOPP, int iFPP, int st, int finished) {
		
		if(iOPP!=0)
			this.iOPP = new IOProductProcess(iOPP);
		if(iFPP!=0)
			this.iFPP = new IFProductProcess(iFPP);
		if(st != 0)
			started = new Started(st);
		if(finished != 0)
			this.finishedUnits = finished;
		else
			this.finishedUnits = -1
		;
	}
	
	public boolean check() {
		 valid = false;
		 if(iFPP != null && iOPP!=null && started != null && finishedUnits!=-1 ) {
			 valid = true;
			 previousUnits = iOPP.getUnits();
				startedAndFinishedUnits = finishedUnits-previousUnits;
		 }
		 else if(iFPP == null && iOPP!=null && started != null && finishedUnits!=-1 ) {
				int unit = iOPP.getUnits()+started.getUnits()-finishedUnits;
				iFPP = new IFProductProcess(unit);
				valid = true;
				previousUnits = iOPP.getUnits();
				startedAndFinishedUnits = finishedUnits-previousUnits;
			}else if(iOPP == null && iFPP!=null && started != null && finishedUnits!=-1 ) {
				int unit = iFPP.getUnits()-started.getUnits()+finishedUnits;
				iOPP = new IOProductProcess(unit);
				valid = true;
				previousUnits = iOPP.getUnits();
				startedAndFinishedUnits = finishedUnits-previousUnits;
			}else if(iFPP != null && iOPP!=null && started == null && finishedUnits!=-1 ) {
				int unit = iFPP.getUnits()-iOPP.getUnits()+finishedUnits;
				started = new Started(unit);
				valid = true;
				previousUnits = iOPP.getUnits();
				startedAndFinishedUnits = finishedUnits-previousUnits;
			}else if(iFPP != null && iOPP!=null && started != null && finishedUnits==-1 ) {
				int unit = iOPP.getUnits()+started.getUnits()-iFPP.getUnits();
				finishedUnits = unit;
				valid = true;
				previousUnits = iOPP.getUnits();
				startedAndFinishedUnits = finishedUnits-previousUnits;
			}

			
		if(valid && (iOPP.getUnits() < 0 || iFPP.getUnits() < 0 || started.getUnits() < 0 || finishedUnits < 0 || previousUnits < 0 || startedAndFinishedUnits < 0))
			valid = false;
		return valid;
	}	
	
	public boolean isValid() {
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

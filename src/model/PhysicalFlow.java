package model;

public class PhysicalFlow {
	
	private IOProductProcess IOPP;
	private IFProductProcess IFPP;
	private Started started;
	
	private int finished;
	private int previous;
	private int startedAndFinished;
	
	
	/**
	 * @param iOPP
	 * @param iFPP
	 * @param started
	 */
	public PhysicalFlow(int ioPP, int ifPP, int st, int finished) {
		
		if(ioPP!=-1)
			IOPP = new IOProductProcess(ioPP);
		if(ifPP!=-1)
			IFPP = new IFProductProcess(ifPP);
		if(st != -1)
			started = new Started(st);
		this.finished = finished;
	}
	
	
	
	
	public boolean check() {
		boolean valid = false;
		if(IFPP == null && IOPP!=null && started != null && finished!=-1 ) {
			int unit = IOPP.getUnits()+started.getUnits()-finished;
			IFPP = new IFProductProcess(unit);
			valid = true;
		}else if(IOPP == null && IFPP!=null && started != null && finished!=-1 ) {
			int unit = IFPP.getUnits()-started.getUnits()+finished;
			IOPP = new IOProductProcess(unit);
			valid = true;
		}else if(IFPP != null && IOPP!=null && started == null && finished!=-1 ) {
			int unit = IFPP.getUnits()-IOPP.getUnits()+finished;
			started = new Started(unit);
			valid = true;
			System.out.println("start " + unit);
		}else if(IFPP != null && IOPP!=null && started != null && finished==-1 ) {
			int unit = IOPP.getUnits()+started.getUnits()-IFPP.getUnits();
			finished = unit;
			valid = true;
		}
	
	
		return valid;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the iOPP
	 */
	public IOProductProcess getIOPP() {
		return IOPP;
	}
	/**
	 * @param iOPP the iOPP to set
	 */
	public void setIOPP(IOProductProcess iOPP) {
		IOPP = iOPP;
	}
	/**
	 * @return the iFPP
	 */
	public IFProductProcess getIFPP() {
		return IFPP;
	}
	/**
	 * @param iFPP the iFPP to set
	 */
	public void setIFPP(IFProductProcess iFPP) {
		IFPP = iFPP;
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
		return finished;
	}
	
}

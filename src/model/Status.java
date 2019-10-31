package model;

public class Status {
	
	private double MDpercentage;
	private double MODpercentage;
	private double CIFpercentage;
	
	private double MDvalue;
	private double MODvalue;
	private double CIFvalue;
	private double CTvalue;
	/**
	 * @param mDpercentage
	 * @param mODpercentage
	 * @param cIFpercentage
	 * @param mDvalue
	 * @param mODvalue
	 * @param cIFvalue
	 * @param cTvalue
	 */
	public Status(double mDpercentage, double mODpercentage, double cIFpercentage, double mDvalue, double mODvalue,
			double cIFvalue, double cTvalue) {
		MDpercentage = mDpercentage;
		MODpercentage = mODpercentage;
		CIFpercentage = cIFpercentage;
		MDvalue = mDvalue;
		MODvalue = mODvalue;
		CIFvalue = cIFvalue;
		CTvalue = cTvalue;
	}
	/**
	 * @return the mDpercentage
	 */
	public double getMDpercentage() {
		return MDpercentage;
	}
	/**
	 * @param mDpercentage the mDpercentage to set
	 */
	public void setMDpercentage(double mDpercentage) {
		MDpercentage = mDpercentage;
	}
	/**
	 * @return the mODpercentage
	 */
	public double getMODpercentage() {
		return MODpercentage;
	}
	/**
	 * @param mODpercentage the mODpercentage to set
	 */
	public void setMODpercentage(double mODpercentage) {
		MODpercentage = mODpercentage;
	}
	/**
	 * @return the cIFpercentage
	 */
	public double getCIFpercentage() {
		return CIFpercentage;
	}
	/**
	 * @param cIFpercentage the cIFpercentage to set
	 */
	public void setCIFpercentage(double cIFpercentage) {
		CIFpercentage = cIFpercentage;
	}
	/**
	 * @return the mDvalue
	 */
	public double getMDvalue() {
		return MDvalue;
	}
	/**
	 * @param mDvalue the mDvalue to set
	 */
	public void setMDvalue(double mDvalue) {
		MDvalue = mDvalue;
	}
	/**
	 * @return the mODvalue
	 */
	public double getMODvalue() {
		return MODvalue;
	}
	/**
	 * @param mODvalue the mODvalue to set
	 */
	public void setMODvalue(double mODvalue) {
		MODvalue = mODvalue;
	}
	/**
	 * @return the cIFvalue
	 */
	public double getCIFvalue() {
		return CIFvalue;
	}
	/**
	 * @param cIFvalue the cIFvalue to set
	 */
	public void setCIFvalue(double cIFvalue) {
		CIFvalue = cIFvalue;
	}
	/**
	 * @return the cTvalue
	 */
	public double getCTvalue() {
		return CTvalue;
	}
	/**
	 * @param cTvalue the cTvalue to set
	 */
	public void setCTvalue(double cTvalue) {
		CTvalue = cTvalue;
	}
	
	
}

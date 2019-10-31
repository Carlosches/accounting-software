package model;

public class EquivalentProduction {
	
	double[][] peps;
	double[][] pp;
	
	public EquivalentProduction() {
		peps = new double[4][4];
		pp = new double[3][4];
	}
	
	public void calculatePeps(Status io, Status iF, int ioPP, int startedAndFinishedUnits, int ifPP) {
		peps[0][0] = ioPP;
		peps[0][1] = ioPP*io.getMDpercentage();
		peps[0][2] = ioPP*io.getMODpercentage();
		peps[0][3] = ioPP*io.getCIFpercentage();
		peps[1][0] = peps[1][1] = peps[1][2] = peps[1][3] = startedAndFinishedUnits;
		peps[2][0] = ifPP;
		peps[2][1] = ifPP*iF.getMDpercentage();
		peps[2][2] = ifPP*iF.getMODpercentage();
		peps[2][3] = ifPP*iF.getCIFpercentage();
		peps[3][0] = peps[0][0] + peps[1][0] + peps[2][0];
		peps[3][1] = peps[0][1] + peps[1][1] + peps[2][1];
		peps[3][2] = peps[0][2] + peps[1][2] + peps[2][2];
		peps[3][3] = peps[0][3] + peps[1][3] + peps[2][3];
	}
	
	public void calculatePP(Status iF, int finished, int ifPP) {
		pp[0][0] = pp[0][1] = pp[0][2] = pp[0][3] = finished;
		pp[1][0] = ifPP;
		pp[1][1] = ifPP*iF.getMDpercentage();
		pp[1][2] = ifPP*iF.getMODpercentage();
		pp[1][3] = ifPP*iF.getCIFpercentage();
		pp[2][0] = pp[0][0] + pp[1][0];
		pp[2][1] = pp[0][1] + pp[1][1];
		pp[2][2] = pp[0][2] + pp[1][2];
		pp[2][3] = pp[0][3] + pp[1][3];
	}
	
	public double[][] getPeps(){
		return peps;
	}
	public double[][] getPP(){
		return pp;
	}
}

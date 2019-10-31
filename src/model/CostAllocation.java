package model;

public class CostAllocation {
	 double unitaryTCPeps;
	 double unitaryTCPP;
	 double unitaryMDPeps;
	 double unitaryMODPeps;
	 double unitaryCIFPeps;
	 double totalUnitaryCostPeps;
	 double unitaryMDPP;
	 double unitaryMODPP;
	 double unitaryCIFPP;
	 double totalUnitaryCostPP;
	 
	public CostAllocation(double unitaryTCPeps, double unitaryTCPP, double unitaryMDPeps, double unitaryMODPeps, double unitaryCIFPeps,
			double unitaryMDPP, double unitaryMODPP, double unitaryCIFPP) {
		this.unitaryTCPeps = unitaryTCPeps;
		this.unitaryTCPP = unitaryTCPP;
		this.unitaryMDPeps = unitaryMDPeps;
		this.unitaryMODPeps = unitaryMODPeps;
		this.unitaryCIFPeps = unitaryCIFPeps;
		this.unitaryMDPP = unitaryMDPP;
		this.unitaryMODPP = unitaryMODPP;
		this.unitaryCIFPP = unitaryCIFPP;
		totalUnitaryCostPeps = unitaryCIFPeps + unitaryMDPeps + unitaryMODPeps+unitaryTCPeps;
		totalUnitaryCostPP = unitaryCIFPP + unitaryMDPP + unitaryMODPP + unitaryTCPP;
	}
	 
	 public double[] calculateAssignedCostsPeps(double[][] peps, double ioPP) {
		 double[] ans = new double[13];
		 ans[0] = ioPP;
		 ans[1] = peps[0][1]*unitaryMDPeps;
		 ans[2] = peps[0][2]*unitaryMODPeps;
		 ans[3] = peps[0][3]*unitaryCIFPeps;
		 ans[4] = ans[0]+ ans[1] + ans[2] + ans[3];
		 ans[5] = peps[1][0]*totalUnitaryCostPeps;
		 ans[6] = ans[5] + ans[4];
		 ans[7] = peps[2][0] * unitaryTCPeps;
		 ans[8] = peps[2][1]*unitaryMDPeps;
		 ans[9] = peps[2][2]*unitaryMODPeps;
		 ans[10] = peps[2][3]*unitaryCIFPeps;
		 ans[11] = ans[7] + ans[8] + ans[9] + ans[10];
		 ans[12] = ans[6] + ans[11];
		 return ans;
	 }
	 
	 public double[] calculateAssignedCostsPP(double[][] pp) {
		 double[] ans = new double[6];
		 ans[0] = pp[0][0] * totalUnitaryCostPP ;
		 ans[1] = pp[1][0] * unitaryTCPP;
		 ans[2] = pp[1][1]*unitaryMDPP;
		 ans[3] = pp[1][2]*unitaryMODPP;
		 ans[4] = pp[1][3]*unitaryCIFPP;
		 ans[5] = ans[0] + ans[1] + ans[2] + ans[3] + ans[4];
		 return ans;
	 }
}

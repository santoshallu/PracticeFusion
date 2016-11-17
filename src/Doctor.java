
public class Doctor {
	
	private int doctorId;
	private String doctorName;
	private String specialization;
	private int areaCode;
	private double reviewScore;
	
	public Doctor(int doctorId,String doctorName,String specialization,int areaCode,double reviewScore) {
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.areaCode = areaCode;
		this.reviewScore = reviewScore;
		
	}
	
	
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public double getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(double reviewScore) {
		this.reviewScore = reviewScore;
	}

	

}

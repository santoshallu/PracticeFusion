import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindDoctor {

	private static Map<String, List<Doctor>> doctorsBySpecialization = new HashMap<String, List<Doctor>>();
	private static Map<Integer, List<Doctor>> doctorsByAreaCode = new HashMap<Integer, List<Doctor>>();
	private static Map<Integer, Doctor> doctorDetailMap = new HashMap<Integer, Doctor>();
	private static List<Doctor> doctorsList = new LinkedList<Doctor>();

	public static void main(String[] args) {

		createDoctors();
		groupDoctors();

		// this method has associated logic and prints recommended doctors for a
		// given doctor id which executes different types of test cases
		findSimilarDoctors();
	}

	private static void createDoctors() {
		Doctor doctor1 = new Doctor(1, "Phil Test", "Pediatrician", 123, 3.0);
		doctorsList.add(doctor1);
		Doctor doctor3 = new Doctor(3, "Phil Test3", "Pediatrician", 123, 3.5);
		doctorsList.add(doctor3);
		Doctor doctor6 = new Doctor(6, "Phil Test6", "Pediatrician", 123, 2.5);
		doctorsList.add(doctor6);
		Doctor doctor8 = new Doctor(8, "Phil Test8", "Pediatrician", 1234, 2.5);
		doctorsList.add(doctor8);

		Doctor doctor2 = new Doctor(2, "Phil Test2", "Cardiologist", 123, 4.0);
		doctorsList.add(doctor2);
		Doctor doctor4 = new Doctor(4, "Phil Test4", "Cardiologist", 1234, 4.5);
		doctorsList.add(doctor4);
		Doctor doctor10 = new Doctor(10, "Phil Test10", "Cardiologist", 1234,
				3.5);
		doctorsList.add(doctor10);

		Doctor doctor5 = new Doctor(5, "Phil Test5", "neurologist", 1234, 4);
		doctorsList.add(doctor5);
		Doctor doctor7 = new Doctor(7, "Phil Test7", "neurologist", 1234, 2.5);
		doctorsList.add(doctor7);
		Doctor doctor9 = new Doctor(9, "Phil Test9", "neurologist", 123, 5);
		doctorsList.add(doctor9);

		Doctor doctor11 = new Doctor(11, "Phil Test11", "ophthalmologist", 123,
				5);
		doctorsList.add(doctor11);

	}

	private static void groupDoctors() {
		for (Doctor doctor : doctorsList) {
			if (doctorsBySpecialization.get(doctor.getSpecialization()) == null) {
				List<Doctor> doctorList = new LinkedList<Doctor>();
				doctorList.add(doctor);
				doctorsBySpecialization.put(doctor.getSpecialization(),
						doctorList);
			} else {
				doctorsBySpecialization.get(doctor.getSpecialization()).add(
						doctor);
			}

			if (doctorsByAreaCode.get(doctor.getAreaCode()) == null) {
				List<Doctor> doctorList = new LinkedList<Doctor>();
				doctorList.add(doctor);
				doctorsByAreaCode.put(doctor.getAreaCode(), doctorList);

			} else {
				doctorsByAreaCode.get(doctor.getAreaCode()).add(doctor);
			}
			doctorDetailMap.put(doctor.getDoctorId(), doctor);
		}

	}

	//
	private static void findSimilarDoctors() {
		int[] doctorIdTests = { 2, 3, 9, 11 };

		for (Integer doctorIdTest : doctorIdTests) {
			List<Doctor> doctorList = new LinkedList<Doctor>();
			Doctor doctorDetails = doctorDetailMap.get(doctorIdTest);
			Set<Integer> doctorIdSet = new HashSet<Integer>();
			doctorIdSet.add(doctorDetails.getDoctorId());

			System.out.println("Current Doctor");
			printDoctor(doctorDetails);

			// first look for doctors with in that area code
			for (Doctor doctor : doctorsByAreaCode.get(doctorDetails
					.getAreaCode())) {
				if (doctor.getSpecialization().equals(
						doctorDetails.getSpecialization())) {
					if (!doctorIdSet.contains(doctor.getDoctorId())) {
						doctorIdSet.add(doctor.getDoctorId());
						doctorList.add(doctor);
					}
				}
			}

			if (doctorList.size() == 0) {
				System.out.println("No Recommended Doctors found with in your area");
			} else {
				System.out.println("Recommended Doctors with in your area");

				Collections.sort(doctorList, new reviewComparator());
				for (Doctor doctor : doctorList) {
					printDoctor(doctor);
				}
			}
			doctorList = new LinkedList<Doctor>();

			// Then look for doctors with in the specialization
			for (Doctor doctor : doctorsBySpecialization.get(doctorDetails
					.getSpecialization())) {
				if (!doctorIdSet.contains(doctor.getDoctorId())) {
					doctorList.add(doctor);
				}
			}
			if (doctorList.size() == 0) {
				System.out.println("No Recommended Doctors found outside your area");
			} else {
				System.out.println("Recommended Doctors outside your area");

				Collections.sort(doctorList, new reviewComparator());
				for (Doctor doctor : doctorList) {
					printDoctor(doctor);
				}
			}
			System.out.println("\n");
		}
	}

	private static void printDoctor(Doctor doctorDetails) {
		System.out.println("Doctor Name: " + doctorDetails.getDoctorName()
				+ " Doctor specialization:" + doctorDetails.getSpecialization()
				+ " Area code :" + doctorDetails.getAreaCode()
				+ " Review Score " + doctorDetails.getReviewScore());

	}

}

class reviewComparator implements Comparator<Doctor> {

	@Override
	public int compare(Doctor paramT1, Doctor paramT2) {

		if (paramT1.getReviewScore() > paramT2.getReviewScore()) {
			return -1;
		}
		if (paramT1.getReviewScore() < paramT2.getReviewScore()) {
			return 1;
		}
		return 0;
	}

}

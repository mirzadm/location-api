package rxss.pharmacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PharmacyService {
	private List<Pharmacy> pharmacies = new ArrayList<>(Arrays.asList(
			new Pharmacy("WALGREENS", "3696 SW TOPEKA BLVD", "TOPEKA", "KS", 66611, 39.00142300, -95.68695000),
			new Pharmacy("KMART PHARMACY   ", "1740 SW WANAMAKER ROAD", "TOPEKA", "KS", 66604, 39.03504000, -95.75870000)			
			));

	public Pharmacy getClosestPharmacies(double latitude, double longitude) {
		double lat1 = pharmacies.get(0).getLatitude();
		double lat2 = pharmacies.get(1).getLatitude();
		double lon1 = pharmacies.get(0).getLongitude();
		double lon2 = pharmacies.get(1).getLongitude();
		double distance = calculateDistance(lat1, lon1, lat2, lon2);
		System.out.println(distance);
		return pharmacies.get(0);
	}

	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.cos(Math.toRadians(theta));

			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return dist;
		}
	}
}

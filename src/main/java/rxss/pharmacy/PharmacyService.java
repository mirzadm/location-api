package rxss.pharmacy;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Service;

@Service
public class PharmacyService {
	private List<Pharmacy> pharmacies = parseCSV("pharmacies.csv");

	public Pharmacy getClosestPharmacies(double latitude, double longitude) {
		double min_distance = -1;
		Pharmacy closest_pharmacy = null;
		double distance;
		for (Pharmacy pharmacy : pharmacies) {
			distance = calculateDistance(latitude, longitude, pharmacy.getLatitude(), pharmacy.getLongitude());
			if (min_distance == -1 || distance < min_distance) {
				min_distance = distance;
				closest_pharmacy = pharmacy;
			}
		}
		System.out.println(min_distance);
		return closest_pharmacy;
	}

	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2))
			return 0;
		else {
			double theta = Math.toRadians(lon1 - lon2);
			lat1 = Math.toRadians(lat1);
			lat2 = Math.toRadians(lat2);
			double dist = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(theta);

			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return dist;
		}
	}

	private List<Pharmacy> parseCSV(String csvFileToRead) {

		BufferedReader br;
		List<Pharmacy> pharmacies = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(csvFileToRead));
			String line = br.readLine(); // Skip the header line
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				Pharmacy pharmacy = new Pharmacy();
				pharmacy.setName(tokens[0]);
				pharmacy.setAddress(tokens[1]);
				pharmacy.setCity(tokens[2]);
				pharmacy.setState(tokens[3]);
				pharmacy.setZipcode(Integer.parseInt(tokens[4]));
				pharmacy.setLatitude(Double.parseDouble(tokens[5]));
				pharmacy.setLongitude(Double.parseDouble(tokens[6]));
				pharmacies.add(pharmacy);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pharmacies;
	}

}

package rxss.pharmacy;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;


@Service
public class PharmacyService {
	private static List<Pharmacy> pharmacies = parseCSVToBeanList("pharmacies.csv");

	public HashMap<String, Object> getClosestPharmacies(double latitude, double longitude) {
		double minDistance = -1;
		Pharmacy closestPharmacy = null;
		double distance;
		// Find min distance pharmacy
		for (Pharmacy pharmacy : pharmacies) {
			distance = calculateDistance(latitude, longitude, pharmacy.getLatitude(), pharmacy.getLongitude());
			if (minDistance == -1 || distance < minDistance) {
				minDistance = distance;
				closestPharmacy = pharmacy;
			}
		}
		// Create a hash map for pharmacy info
		HashMap<String, Object> closestPharmacyMap = new HashMap<String, Object>();
		closestPharmacyMap.put("Name", closestPharmacy.getName());
		closestPharmacyMap.put("Address", closestPharmacy.getAddress());
		closestPharmacyMap.put("City", closestPharmacy.getCity());
		closestPharmacyMap.put("State", closestPharmacy.getState());
		closestPharmacyMap.put("Zip Code", closestPharmacy.getZipcode());
		closestPharmacyMap.put("Distance", minDistance);
		return closestPharmacyMap;
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

	private static List<Pharmacy> parseCSVToBeanList(String csvFileToRead) {
		HeaderColumnNameTranslateMappingStrategy<Pharmacy> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<Pharmacy>();
		beanStrategy.setType(Pharmacy.class);

		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("name", "name");
		columnMapping.put("address", "address");
		columnMapping.put("city", "city");
		columnMapping.put("state", "state");
		columnMapping.put("zip", "zipcode");
		columnMapping.put("latitude", "latitude");
		columnMapping.put("longitude", "longitude");

		beanStrategy.setColumnMapping(columnMapping);

		CsvToBean<Pharmacy> csvToBean = new CsvToBean<Pharmacy>();
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileToRead));
			List<Pharmacy> pharmacies = csvToBean.parse(beanStrategy, reader);
			return pharmacies;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

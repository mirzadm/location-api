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

	public Pharmacy getClosestharmacies() {
		return pharmacies.get(0);
	}

}

package rxss.pharmacy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;  
	
	@RequestMapping("/pharmacies")
	public Pharmacy getPharmacy(@RequestParam double lat, @RequestParam double lon) {
		return pharmacyService.getClosestharmacies();
	}

}

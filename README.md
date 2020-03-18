# Closest Pharmacy API
A simple API to find the closest pharmacy to a given location (latitude and longitude). The app reads pharmacy store location information from `pharmacies.csv`. It provides a `/pharmacies` endpoint that receives lattitude and longitude as query parameters `lat` and `lon`. It respondes with name, address, and distance of the closest pharmacy.


## Requirements
* Java (13)
* Maven

## Build and run the app
* Clone the repo and `cd` to repo's root
* Build: `mvn clean install`
* Run: `java -jar target/location-api-[...].jar`

## Example API call
* `curl "http://localhost:8080/pharmacies?lat=38.9&lon=-96"`

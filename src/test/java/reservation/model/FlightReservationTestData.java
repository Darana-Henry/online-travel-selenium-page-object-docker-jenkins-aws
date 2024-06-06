package reservation.model;

import lombok.Data;

@Data
public class FlightReservationTestData {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String street;
    private String city;
    private String zip;
    private String passengersCount;
    private String expectedPrice;
}



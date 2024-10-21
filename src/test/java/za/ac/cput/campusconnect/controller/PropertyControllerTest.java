package za.ac.cput.campusconnect.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.campusconnect.domain.Business;
import za.ac.cput.campusconnect.domain.Property;
import za.ac.cput.campusconnect.factory.BusinessFactory;
import za.ac.cput.campusconnect.factory.PropertyFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PropertyControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL= "http://localhost:8080/CampusConnectDb/property";
    private static Property property;
    private static Business business;


    @BeforeAll
    public static void setup() {
        business = BusinessFactory.buildBusiness("IvyProperties", "student accomodation", "0218130260", "ivyproperties@gmail.com", "13 Ntlazane Street", "Khayelitsha","Cape Town","7784");
        property = PropertyFactory.buildProperty(business, "JayProperty", "36 Cape Cape", 20, 20);

    }
    @Test
    void a_create() {
        String URL = BASE_URL + "/create";
        ResponseEntity<Property> postResponse = restTemplate.postForEntity(URL, property, Property.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Property PropertySaved = postResponse.getBody();
        assertEquals(property.getPropertyID(), PropertySaved.getPropertyID());
        System.out.println("Created property: " + PropertySaved);
    }

    @Test
    void b_read() {
        String URL = BASE_URL + "/read/" + property.getPropertyID();
        System.out.println("URL: " + URL);
        ResponseEntity<Property> response = restTemplate.getForEntity(URL, Property.class);
        assertNotNull(response.getBody());
        System.out.println("Read property: " + response.getBody());
        assertEquals(property.getPropertyID(), response.getBody().getPropertyID());
    }

    @Test
    void c_update() {
        String URL = BASE_URL + "/update";
        Property newProperty = new Property.Builder().copy(property).setPropertyName("ApheleleJoka ").build();
        ResponseEntity<Property> postResponse = restTemplate.postForEntity(URL, newProperty, Property.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Property propertyUpdated = postResponse.getBody();
        System.out.println("Updated property: " + propertyUpdated);
        assertEquals(newProperty.getPropertyID(), propertyUpdated.getPropertyID());
    }

    @Disabled
    @Test
    void d_delete() {
        String URL = BASE_URL + "/delete/" + property.getPropertyID();
        System.out.println("URL: " + URL);
        restTemplate.delete(URL);
        System.out.println("Success: Deleted property");
    }

    @Test
    void e_getAll() {
        String URL = BASE_URL + "/getAll";
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        System.out.println("Show All: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}


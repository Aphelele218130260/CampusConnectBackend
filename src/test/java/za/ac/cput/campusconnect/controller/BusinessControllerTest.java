package za.ac.cput.campusconnect.controller;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import za.ac.cput.campusconnect.domain.Business;
import za.ac.cput.campusconnect.factory.BusinessFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;
        private final String BASE_URL="http://localhost:8080/CampusConnectDb/business";
        private static Business business;

        @BeforeAll
        public static void setup() {
            business = BusinessFactory.buildBusiness("IvyProperties", "student accomodation", "0218130260", "ivyproperties@gmail.com", "13 Ntlazane Street", "Khayelitsha","Cape Town","7784");

        }
        @Test
        void a_create() {
            String URL = BASE_URL + "/create";
            ResponseEntity<Business> postResponse = restTemplate.postForEntity(URL, business, Business.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());
            Business BusinessSaved = postResponse.getBody();
            assertEquals(business.getBusinessID(), BusinessSaved.getBusinessID());
            System.out.println("Created Business: " + BusinessSaved);

        }

        @Test
        void b_read() {
            String URL = BASE_URL + "/read/" + business.getBusinessID();
            System.out.println("URL: " + URL);
            ResponseEntity<Business> response = restTemplate.getForEntity(URL, Business.class);
            assertNotNull(response.getBody());
            System.out.println("Read Business: " + response.getBody());
            assertEquals(business.getBusinessID(), response.getBody().getBusinessID());
        }

        @Test
        void c_update() {
            String URL = BASE_URL + "/update";
            Business newBusiness = new Business.Builder().copy(business).setBusinessName("ApheleleJoka ").build();
            ResponseEntity<Business> postResponse = restTemplate.postForEntity(URL, newBusiness, Business.class);
            assertNotNull(postResponse);
            assertNotNull(postResponse.getBody());
            Business businessUpdated = postResponse.getBody();
            System.out.println("Updated business: " + businessUpdated);
            assertEquals(newBusiness.getBusinessID(), businessUpdated.getBusinessID());
        }

        @Disabled
        @Test
        void d_delete() {
            String URL = BASE_URL + "/delete/" + business.getBusinessID();
            System.out.println("URL: " + URL);
            restTemplate.delete(URL);
            System.out.println("Success: Deleted business");
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


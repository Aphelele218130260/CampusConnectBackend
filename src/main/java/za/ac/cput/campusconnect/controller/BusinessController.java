package za.ac.cput.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.campusconnect.domain.Business;
import za.ac.cput.campusconnect.service.BusinessService;

import java.util.List;

/**
 * FileName.java
 * Class:
 * Author:Aphelele Zimkhita Joka 218130260
 * Completion date:
 */

@RestController
@RequestMapping("/business")
@CrossOrigin("http://localhost/5173")
public class BusinessController {

    @Autowired
    private BusinessService businessService;
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/create")
    public Business create(@RequestBody Business business){
        System.out.println("wee"+ business);
        return businessService.create(business);}
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/read/{businessID}")
    public Business read(@PathVariable Long businessID){return businessService.read(businessID);}
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/update")
    public Business update (@RequestBody Business business){return businessService.update(business);}
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){businessService.delete(id);}
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getAll")
    public List<Business> getall(){return businessService.getAll();}


}

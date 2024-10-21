package za.ac.cput.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.campusconnect.domain.Property;
import za.ac.cput.campusconnect.service.PropertyService;

import java.util.List;

/**
 * FileName.java
 * Class:
 * Author:Aphelele Zimkhita Joka 218130260
 * Completion date:
 */
@RestController
@RequestMapping("/property")
@CrossOrigin("http://localhost/5173")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/create")
    public Property create(@RequestBody Property property){return propertyService.create(property);}
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/read/{propertyID}")
    public Property read(@PathVariable Long propertyID){return propertyService.read(propertyID);}
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/update")
    public Property update (@RequestBody Property property){return propertyService.update(property);}
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){propertyService.delete(id);}
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getAll")
    public List<Property> getAll(){return propertyService.getAll();}


}


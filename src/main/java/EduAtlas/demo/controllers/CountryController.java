package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.Country;
import EduAtlas.demo.payloads.NewCountryDTO;
import EduAtlas.demo.servicies.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")

public class CountryController {
    @Autowired
    private CountryService countryService;

@GetMapping("/{countryId}")
    public Country getById(@PathVariable long countryId){
    return countryService.findById(countryId);
}

@PostMapping("/saveCountry")
    public Country save(@RequestBody NewCountryDTO countryDTO){
    return countryService.save(countryDTO);
}

}

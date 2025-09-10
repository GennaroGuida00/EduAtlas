package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.Country;
import EduAtlas.demo.entities.User;
import EduAtlas.demo.payloads.NewCountryDTO;
import EduAtlas.demo.servicies.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")

public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Country> filterCountry() {
        return countryService.findAll();
    }

@GetMapping("/{countryId}")
    public Country getById(@PathVariable long countryId){
    return countryService.findById(countryId);
}

@PostMapping("/saveCountry")
@PreAuthorize("hasAuthority('ADMIN')")
    public Country save(@RequestBody NewCountryDTO countryDTO){
    return countryService.save(countryDTO);
}

@DeleteMapping("/{countryId}")
@PreAuthorize("hasAuthority('ADMIN')")
    public void getByIdAndDelete(@PathVariable long countryId){
    countryService.findByIdAndDelete(countryId);
}

@PutMapping("/{countryId}")
@PreAuthorize("hasAuthority('ADMIN')")
    public Country getByIdAndUpdate(@PathVariable long countryId, @RequestBody NewCountryDTO countryDTO){
    return countryService.findByIdAndUpdate(countryId,countryDTO);
}
}

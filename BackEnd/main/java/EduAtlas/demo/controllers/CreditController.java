package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.Country;
import EduAtlas.demo.entities.Credit;
import EduAtlas.demo.payloads.NewCreditDTO;
import EduAtlas.demo.servicies.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
 @RequestMapping("credits")
public class CreditController {
    @Autowired
    CreditService creditService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Credit save(@RequestBody NewCreditDTO creditDTO){
        return creditService.save(creditDTO);
    }

    @GetMapping
    public List<Credit> filterCredit() {
        return creditService.findAll();
    }

    @GetMapping("/{creditId}")
    public Credit findById(@PathVariable long credit_id){
        return creditService.findById(credit_id);
    }

    @GetMapping("/country/{countryId}")
        public List<Credit> findCreditsByCountryId(@PathVariable long countryId){
            return creditService.findCreditsbyCountryId(countryId);
        }

    @PutMapping("/{creditId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Credit getByIdAndUpdate(@PathVariable long creditId, @RequestBody NewCreditDTO creditDTO){
        return creditService.findByIdAndUpdate(creditId,creditDTO);
    }

    @DeleteMapping("/{creditId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void getByIdAndDelete(@PathVariable long creditId){
        creditService.findByIdAndDelete(creditId);
    }

}

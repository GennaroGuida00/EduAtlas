package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.Credit;
import EduAtlas.demo.payloads.NewCreditDTO;
import EduAtlas.demo.servicies.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
 @RequestMapping("credits")
public class CreditController {
    @Autowired
    CreditService creditService;

    @PostMapping("/saveCredit")
    public Credit save(@RequestBody NewCreditDTO creditDTO){
        return creditService.save(creditDTO);
    }

    @GetMapping("/{creditId}")
    public Credit findById(@PathVariable long credit_id){
        return creditService.findById(credit_id);
    }
}

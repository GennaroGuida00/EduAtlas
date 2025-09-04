package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.Degree;
import EduAtlas.demo.payloads.NewDegreeDTO;
import EduAtlas.demo.servicies.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("degrees")
public class DegreeController {

    @Autowired
    DegreeService degreeService;

    @PostMapping
    public Degree save(@RequestBody NewDegreeDTO degreeDTO){
        return degreeService.save(degreeDTO);
    }
    @GetMapping("/{degree_id}")
    public Degree findById(long degree_id){
        return degreeService.findById(degree_id);
    }
}

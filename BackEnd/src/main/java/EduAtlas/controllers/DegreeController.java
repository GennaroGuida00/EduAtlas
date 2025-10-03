package EduAtlas.controllers;

import EduAtlas.entities.Degree;
import EduAtlas.payloads.NewDegreeDTO;
import EduAtlas.servicies.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("degrees")
public class DegreeController {

    @Autowired
    DegreeService degreeService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Degree save(@RequestBody NewDegreeDTO degreeDTO){
        return degreeService.save(degreeDTO);
    }

    @GetMapping
    public List<Degree> filterDegree() {
        return degreeService.findAll();
    }
    @GetMapping("/{degree_id}")
    public Degree findById(long degree_id){
        return degreeService.findById(degree_id);
    }

    @PutMapping("/{degree_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Degree getByIdAndUpdate(@PathVariable long degree_id, @RequestBody NewDegreeDTO degreeDTO){
        return degreeService.findByIdAndUpdate(degree_id,degreeDTO);
    }

    @DeleteMapping("/{degree_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void getByIdAndDelete(@PathVariable long degree_id){
        degreeService.findByIdAndDelete(degree_id);
    }
}

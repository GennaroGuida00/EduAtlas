package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.Credit;
import EduAtlas.demo.entities.Degree;
import EduAtlas.demo.entities.GradeScale;
import EduAtlas.demo.payloads.NewDegreeDTO;
import EduAtlas.demo.payloads.NewGradeScaleDTO;
import EduAtlas.demo.servicies.GradeScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gradescales")
public class GradeScaleController {

    @Autowired
    GradeScaleService gradeScaleService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public GradeScale save(@RequestBody NewGradeScaleDTO gradeScaleDTO){
        return gradeScaleService.save(gradeScaleDTO);
    }
    @GetMapping
    public List<GradeScale> filterGradeScale() {
        return gradeScaleService.findAll();
    }
    @GetMapping("/{gradescale_id}")
    public GradeScale findById(long gradescale_id){
        return gradeScaleService.findById(gradescale_id);
    }

    @PutMapping("/{gradescale_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GradeScale getByIdAndUpdate(@PathVariable long gradescale_id, @RequestBody NewGradeScaleDTO gradeScaleDTO){
        return gradeScaleService.findbyIdAndUpdate(gradescale_id,gradeScaleDTO);
    }

    @DeleteMapping("/{gradescale_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void getByIdAndDelete(@PathVariable long gradescale_id) {
        gradeScaleService.findByIdAndDelete(gradescale_id);
    }
}

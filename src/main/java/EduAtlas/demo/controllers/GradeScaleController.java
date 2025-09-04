package EduAtlas.demo.controllers;

import EduAtlas.demo.entities.GradeScale;
import EduAtlas.demo.payloads.NewGradeScaleDTO;
import EduAtlas.demo.servicies.GradeScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gradescales")
public class GradeScaleController {

    @Autowired
    GradeScaleService gradeScaleService;

    @PostMapping
    public GradeScale save(@RequestBody NewGradeScaleDTO gradeScaleDTO){
        return gradeScaleService.save(gradeScaleDTO);
    }

    @GetMapping("/{gradescale_id}")
    public GradeScale findById(long gradescale_id){
        return gradeScaleService.findById(gradescale_id);
    }
}

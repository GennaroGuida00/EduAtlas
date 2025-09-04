package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.GradeScale;
import EduAtlas.demo.excpetions.NotFoundExcpetions;
import EduAtlas.demo.payloads.NewGradeScaleDTO;
import EduAtlas.demo.repositories.CountryRepository;
import EduAtlas.demo.repositories.GradeScaleRpository;
import org.springframework.beans.factory.annotation.Autowired;

public class GradeScaleService {

    @Autowired
    GradeScaleRpository gradeScaleRpository;

    @Autowired
    CountryRepository countryRepository;



    public GradeScale save(NewGradeScaleDTO gradeScaleDTO){
        GradeScale gradeScale=new GradeScale();
        gradeScale.setMin_value(gradeScaleDTO.min_value());
        gradeScale.setMax_value(gradeScaleDTO.max_value());
        gradeScale.setCountry(countryRepository.findById(gradeScaleDTO.country_id()).orElseThrow(()->new NotFoundExcpetions(gradeScaleDTO.country_id())));
        return gradeScaleRpository.save(gradeScale);
    }

    public GradeScale findById(long id){
        return gradeScaleRpository.findById(id).orElseThrow(()->new NotFoundExcpetions(id));
    }

    public void findByIdAndDelete(long id){
        GradeScale found=gradeScaleRpository.findById(id).orElseThrow(()->new NotFoundExcpetions(id));
        gradeScaleRpository.delete(found);
    }
}

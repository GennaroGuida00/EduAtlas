package EduAtlas.servicies;

import EduAtlas.entities.GradeScale;
import EduAtlas.exceptions.NotFoundException;
import EduAtlas.payloads.NewGradeScaleDTO;
import EduAtlas.repositories.CountryRepository;
import EduAtlas.repositories.GradeScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeScaleService {

    @Autowired
    GradeScaleRepository gradeScaleRepository;

    @Autowired
    CountryRepository countryRepository;



    public GradeScale save(NewGradeScaleDTO gradeScaleDTO){
        GradeScale gradeScale=new GradeScale();
        gradeScale.setGrade_value(gradeScaleDTO.grade_value());
        gradeScale.setMin_value(gradeScaleDTO.min_value());
        gradeScale.setMax_value(gradeScaleDTO.max_value());
        gradeScale.setCountry(countryRepository.findById(gradeScaleDTO.country_id()).orElseThrow(()->new NotFoundException(gradeScaleDTO.country_id())));
        return gradeScaleRepository.save(gradeScale);
    }

    public List<GradeScale> findAll(){
        return gradeScaleRepository.findAll();
    }
    public GradeScale findById(long id){
        return gradeScaleRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void findByIdAndDelete(long id){
        GradeScale found=gradeScaleRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        gradeScaleRepository.delete(found);
    }

    public GradeScale findbyIdAndUpdate(long id, NewGradeScaleDTO gradeScaleDTO){
        GradeScale found=gradeScaleRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        found.setGrade_value(gradeScaleDTO.grade_value());
        found.setMin_value(gradeScaleDTO.min_value());
        found.setMax_value(gradeScaleDTO.max_value());
        found.setCountry(countryRepository.findById(gradeScaleDTO.country_id()).orElseThrow(()->new NotFoundException(gradeScaleDTO.country_id())));
        return gradeScaleRepository.save(found);
    }
}

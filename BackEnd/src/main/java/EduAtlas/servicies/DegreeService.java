package EduAtlas.servicies;

import EduAtlas.entities.Degree;
import EduAtlas.exceptions.NotFoundException;
import EduAtlas.payloads.NewDegreeDTO;
import EduAtlas.repositories.CountryRepository;
import EduAtlas.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeService {

    @Autowired
    DegreeRepository degreeRepository;
    @Autowired
    CountryRepository countryRepository;

    public Degree save(NewDegreeDTO degreeDTO){
        Degree degree=new Degree();
        degree.setName(degreeDTO.name());
        degree.setMin_years(degreeDTO.min_years());
        degree.setAdditional_years(degreeDTO.additional_years());
        degree.setEqf_level(degreeDTO.eqf_level());
        degree.setCountry(countryRepository.findById(degreeDTO.country_id()).orElseThrow(()->new NotFoundException(degreeDTO.country_id())));
        return degreeRepository.save(degree);
    }

    public List<Degree> findAll(){
        return degreeRepository.findAll();
    }
    public Degree findById(long id){
        Degree found=degreeRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        return found;
    }

    public void findByIdAndDelete(long id){
        Degree found=degreeRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        degreeRepository.delete(found);
    }

    public Degree findByIdAndUpdate(long id,NewDegreeDTO degreeDTO){
        Degree found=degreeRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        found.setName(degreeDTO.name());
        found.setEqf_level(degreeDTO.eqf_level());
        found.setMin_years(degreeDTO.min_years());
        found.setAdditional_years(degreeDTO.additional_years());
        found.setCountry(countryRepository.findById(degreeDTO.country_id()).orElseThrow(()->new NotFoundException(degreeDTO.country_id())));
        return degreeRepository.save(found);
    }
}

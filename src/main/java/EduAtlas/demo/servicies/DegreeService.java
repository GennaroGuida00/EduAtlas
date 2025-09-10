package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.Degree;
import EduAtlas.demo.entities.User;
import EduAtlas.demo.exceptions.NotFoundException;
import EduAtlas.demo.payloads.NewDegreeDTO;
import EduAtlas.demo.repositories.CountryRepository;
import EduAtlas.demo.repositories.DegreeRepository;
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
        degree.setDuration_years(degreeDTO.duration_year());
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
        found.setDuration_years(degreeDTO.duration_year());
        found.setCountry(countryRepository.findById(degreeDTO.country_id()).orElseThrow(()->new NotFoundException(degreeDTO.country_id())));
        return degreeRepository.save(found);
    }
}

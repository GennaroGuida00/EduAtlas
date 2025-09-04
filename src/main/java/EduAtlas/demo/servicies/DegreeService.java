package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.Degree;
import EduAtlas.demo.excpetions.NotFoundExcpetions;
import EduAtlas.demo.payloads.NewDegreeDTO;
import EduAtlas.demo.repositories.CountryRepository;
import EduAtlas.demo.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        degree.setCountry(countryRepository.findById(degreeDTO.country_id()).orElseThrow(()->new NotFoundExcpetions(degreeDTO.country_id())));
        return degreeRepository.save(degree);
    }

    public Degree findById(long id){
        Degree found=degreeRepository.findById(id).orElseThrow(()->new NotFoundExcpetions(id));
        return found;
    }

    public void findByIdAndDelete(long id){
        Degree found=degreeRepository.findById(id).orElseThrow(()->new NotFoundExcpetions(id));
        degreeRepository.delete(found);
    }
}

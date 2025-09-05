package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.Country;
import EduAtlas.demo.exceptions.NotFoundExceptions;
import EduAtlas.demo.payloads.NewCountryDTO;
import EduAtlas.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

     @Autowired
    private CountryRepository countryRepository;

     public Country save(NewCountryDTO newCountryDTO){
         Country newCountry=new Country();
         newCountry.setName(newCountryDTO.name());
         newCountry.setYears_compulsary_schooling(newCountryDTO.years_compulsary_schooling());
         return countryRepository.save(newCountry);
     }

     public Country findById(long id){
         return countryRepository.findById(id).orElseThrow(()->new NotFoundExceptions(id));
     }

     public void findByIdAndDelete(long id){
         Country found=findById(id);
         countryRepository.delete(found);
     }
}

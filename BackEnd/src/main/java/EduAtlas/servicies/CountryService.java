package EduAtlas.servicies;

import EduAtlas.entities.Country;
import EduAtlas.exceptions.NotFoundException;
import EduAtlas.payloads.NewCountryDTO;
import EduAtlas.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Country> findAll(){
        return countryRepository.findAll();
    }
     public Country findById(long id){
         return countryRepository.findById(id).orElseThrow(()->new NotFoundException(id));
     }

     public void findByIdAndDelete(long id){
         Country found=findById(id);
         countryRepository.delete(found);
     }

     public Country findByIdAndUpdate (long id, NewCountryDTO countryDTO){
         Country found=countryRepository.findById(id).orElseThrow(()->new NotFoundException(id));
         found.setName(countryDTO.name());
         found.setYears_compulsary_schooling(countryDTO.years_compulsary_schooling());
         return countryRepository.save(found);
     }
}

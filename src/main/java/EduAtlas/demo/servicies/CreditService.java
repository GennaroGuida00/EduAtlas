package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.Credit;
import EduAtlas.demo.entities.User;
import EduAtlas.demo.exceptions.NotFoundException;
import EduAtlas.demo.payloads.NewCreditDTO;
import EduAtlas.demo.repositories.CreditRepository;
import EduAtlas.demo.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    public Credit save(NewCreditDTO creditDTO){
        Credit credit=new Credit();
        credit.setYear_1_credits(creditDTO.year_1_credits());
        credit.setYear_2_credits(creditDTO.year_2_credits());
        credit.setYear_3_credits(creditDTO.year_3_credits());
        credit.setYear_4_is_additional(creditDTO.year_4_is_additional());
        credit.setYear_4_credits(creditDTO.year_4_credits());
        credit.setYear_5_is_additional(creditDTO.year_5_is_additional());
        credit.setYear_5_credits(creditDTO.year_5_credits());
        credit.setDegree(degreeRepository.findById(creditDTO.degree_id()).orElseThrow(()->new NotFoundException(creditDTO.degree_id())));
        return creditRepository.save(credit);
    }

    public List<Credit> findAll(){
        return creditRepository.findAll();
    }

    public Credit findById(long id){
        return creditRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void findByIdAndDelete(long id){
        Credit found=creditRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        creditRepository.delete(found);
    }

    public Credit findByIdAndUpdate(long id, NewCreditDTO creditDTO){
        Credit found=creditRepository.findById(id).orElseThrow(()->new NotFoundException(id));
        found.setYear_1_credits(creditDTO.year_1_credits());
        found.setYear_2_credits(creditDTO.year_2_credits());
        found.setYear_3_credits(creditDTO.year_3_credits());
        found.setYear_4_is_additional(creditDTO.year_4_is_additional());
        found.setYear_4_credits(creditDTO.year_4_credits());
        found.setYear_5_is_additional(creditDTO.year_5_is_additional());
        found.setYear_5_credits(creditDTO.year_5_credits());
        found.setDegree(degreeRepository.findById(creditDTO.degree_id()).orElseThrow(()->new NotFoundException(creditDTO.degree_id())));
        return creditRepository.save(found);
    }

    public List<Credit> findCreditsbyCountryId(long id){
        return creditRepository.findCreditsByCountryId(id);
    }
}

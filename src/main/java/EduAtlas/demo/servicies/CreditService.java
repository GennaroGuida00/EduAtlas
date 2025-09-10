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
        credit.setCredit_value(creditDTO.credit_value());
        credit.setYear(creditDTO.year());
        credit.setOptional_year(creditDTO.optional_year());
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
        found.setCredit_value(creditDTO.credit_value());
        found.setYear(creditDTO.year());
        found.setOptional_year(creditDTO.optional_year());
        found.setDegree(degreeRepository.findById(creditDTO.degree_id()).orElseThrow(()->new NotFoundException(creditDTO.degree_id())));
        return creditRepository.save(found);
    }
}

package EduAtlas.demo.servicies;

import EduAtlas.demo.entities.Credit;
import EduAtlas.demo.exceptions.NotFoundExceptions;
import EduAtlas.demo.payloads.NewCreditDTO;
import EduAtlas.demo.repositories.CreditRepository;
import EduAtlas.demo.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        credit.setDegree(degreeRepository.findById(creditDTO.degree_id()).orElseThrow(()->new NotFoundExceptions(creditDTO.degree_id())));
        return creditRepository.save(credit);
    }

    public Credit findById(long id){
        return creditRepository.findById(id).orElseThrow(()->new NotFoundExceptions(id));
    }

    public void findByIdAndDelete(long id){
        Credit found=creditRepository.findById(id).orElseThrow(()->new NotFoundExceptions(id));
        creditRepository.delete(found);
    }
}

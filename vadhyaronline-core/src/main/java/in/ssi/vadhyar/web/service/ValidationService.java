package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import in.ssi.vadhyar.web.repository.jdbc.ValidationRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private ValidationRepository validationRepository;

    public ValidationService(ValidationRepository validationRepository) {
        this.validationRepository = validationRepository;
    }

    public Boolean isUniqueMobile(String mobileNumber) {
        return validationRepository.isUniqueMobile(mobileNumber);
    }

    public Boolean isUniqueEmail(String emailAddress) {
        return validationRepository.isUniqueEmail(emailAddress);
    }

    public Boolean isUniqueUserName(String userName) {
        return validationRepository.isUniqueUserName(userName);
    }

}

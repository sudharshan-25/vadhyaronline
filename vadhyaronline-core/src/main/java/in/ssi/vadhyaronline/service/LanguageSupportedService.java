package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.LanguageSupportedRepository;
import in.ssi.vadhyaronline.domain.LanguageSupported;
import in.ssi.vadhyaronline.entity.LanguageSupportedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LanguageSupportedService {

    @Autowired
    private LanguageSupportedRepository langRepository;

    @Transactional(readOnly = true)
    public List<LanguageSupported> getSupportedLanguages() {
        return langRepository.findAll().stream().map(LanguageSupportedEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addLanguageSupport(LanguageSupported languageSupported) {
        LanguageSupportedEntity entity = new LanguageSupportedEntity();
        entity.setId(languageSupported.getId());
        entity.setKey(languageSupported.getKey());
        entity.setValue(languageSupported.getValue());
        langRepository.save(entity);
    }
}

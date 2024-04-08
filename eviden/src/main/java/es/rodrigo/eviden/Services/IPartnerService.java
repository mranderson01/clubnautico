package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IPartnerInterface;
import es.rodrigo.eviden.Models.Partner;
import es.rodrigo.eviden.Repositories.IPartnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IPartnerService implements IPartnerInterface {

    @Autowired
    IPartnerRepository iPartnerRepository;
    @Override
    public List<Partner> getAll() {
        return iPartnerRepository.findAll();
    }

    @Override
    public Optional<Partner> getById(int id) {
        return iPartnerRepository.findById(id);
    }

    @Override
    public void deletePartner(int id) {
        iPartnerRepository.deleteById(id);
    }

    @Override
    public void createPartner(Partner partner) {
        iPartnerRepository.save(partner);
    }
}

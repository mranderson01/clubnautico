package es.rodrigo.eviden.Services;

import es.rodrigo.eviden.Interfaces.IPartnerInterface;
import es.rodrigo.eviden.Models.Partner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPartnerService implements IPartnerInterface {
    @Override
    public List<Partner> getAll() {
        return null;
    }

    @Override
    public Partner getById(int id) {
        return null;
    }

    @Override
    public void deletePartner(int id) {

    }

    @Override
    public void createPartner(Partner partner) {

    }
}

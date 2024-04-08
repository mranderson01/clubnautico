package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Partner;

import java.util.List;
import java.util.Optional;

public interface IPartnerInterface {
    List<Partner> getAll();
    Optional<Partner> getById(int id);
    void deletePartner(int id);
    void createPartner(Partner partner);
}

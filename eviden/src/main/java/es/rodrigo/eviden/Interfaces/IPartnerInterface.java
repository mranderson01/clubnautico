package es.rodrigo.eviden.Interfaces;

import es.rodrigo.eviden.Models.Partner;

import java.util.List;

public interface IPartnerImpl {
    List<Partner> getAll();
    Partner getById(int id);
    void deletePartner(int id);
    void createPartner(Partner partner);
}

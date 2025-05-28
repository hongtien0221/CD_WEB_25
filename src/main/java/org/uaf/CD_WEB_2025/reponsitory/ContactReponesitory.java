package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.CD_WEB_2025.entity.Contact;
import java.util.List;
@Repository
public interface ContactReponesitory extends JpaRepository<Contact,String> {
    public List<Contact> findByIdContact(String idContact);
}

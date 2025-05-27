package org.uaf.CD_WEB_2025.services.IServices;
import org.uaf.CD_WEB_2025.entity.User;
public interface IContactService {
    int getSumContact();
    public int getSumCustomers();
    public void addContact( User user, String content, String nameuser, String phone, String email);
}

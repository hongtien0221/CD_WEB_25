package org.uaf.CD_WEB_2025.services.IServices;

public interface ILoveService {
    long getCount();

    void insertLove(String idUser, String idPr);

    boolean checkLiked(String idUser, String idPr);

}

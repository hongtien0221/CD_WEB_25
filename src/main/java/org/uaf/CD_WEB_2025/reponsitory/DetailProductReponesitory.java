package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.CD_WEB_2025.entity.Detail_Pr;

import java.util.List;

@Repository
public interface  DetailProductReponesitory extends JpaRepository<Detail_Pr, String> {
    @Query("SELECT d, p FROM Detail_Pr d join Product  p on p.idPr= d.idPr WHERE d.idPr = :idPro ")
    List<Detail_Pr> getSingleProduct(String idPro);


}

package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.CD_WEB_2025.entity.FeedBack;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface FeedBackReponesitory extends JpaRepository<FeedBack, Integer> {
    @Query("SELECT f FROM FeedBack f WHERE f.idPr = :idPr")
    List<FeedBack> listFeedback(String idPr);
}

package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.CD_WEB_2025.entity.Social;

import java.util.List;

@Repository
public interface SocialReponesitory extends JpaRepository<Social, Integer> {

    List<Social> findAll();


}

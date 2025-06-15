package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uaf.CD_WEB_2025.entity.Menu;

@Repository
public interface MenuReponesitory extends JpaRepository<Menu, String> {

}

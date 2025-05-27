package org.uaf.CD_WEB_2025.services.IServices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uaf.CD_WEB_2025.entity.Menu;

public interface IMenuService {
    Menu getMenuById(String idmenu);
}

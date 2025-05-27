package org.uaf.CD_WEB_2025.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.CD_WEB_2025.entity.Menu;
import org.uaf.CD_WEB_2025.reponsitory.MenuReponesitory;
import org.uaf.CD_WEB_2025.services.IServices.IMenuService;

@Service
public class MenuServiceImp implements IMenuService {
    private final MenuReponesitory menuReponesitory;

    @Autowired
    public MenuServiceImp(MenuReponesitory menuReponesitory) {
        this.menuReponesitory = menuReponesitory;
    }

    @Override
    public Menu getMenuById(String idmenu) {
        return menuReponesitory.getReferenceById(idmenu);
    }
}

package org.uaf.CD_WEB_2025.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.CD_WEB_2025.entity.Sold_Pr;
import org.uaf.CD_WEB_2025.reponsitory.SoldPrReponesitory;
import org.uaf.CD_WEB_2025.services.IServices.ISoldPrService;

@Service
public class SoldPrServiceImp implements ISoldPrService {
    private final SoldPrReponesitory soldPrReponesitory;

    @Autowired
    public SoldPrServiceImp(SoldPrReponesitory soldPrReponesitory) {
        this.soldPrReponesitory = soldPrReponesitory;
    }

    @Override
    public void addToSoldProd(Sold_Pr soldPr) {
        soldPrReponesitory.insertSoldPr(soldPr.getProduct().getIdPr(), soldPr.getTimeSold(), soldPr.getAmount(), soldPr.getOrders().getIdOrders(), soldPr.getPriceHere());

    }
}

package org.uaf.CD_WEB_2025.services.IServices;

import org.uaf.CD_WEB_2025.entity.Discount;

import java.util.List;

public interface IDiscountService {
    List<Discount> getListDiscount();

    Discount getDiscountByCode(String code);

    void subttractQuantity(String idDiscount);
}

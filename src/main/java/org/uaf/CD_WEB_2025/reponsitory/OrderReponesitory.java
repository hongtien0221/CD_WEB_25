
package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.CD_WEB_2025.entity.Orders;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderReponesitory extends JpaRepository<Orders, String>, PagingAndSortingRepository<Orders, String> {
    @Query("select s.product.idPr from Sold_Pr s where s.orders.idOrders =: idOder")
    String[] getListIdPr(String idOder);

    @Query("SELECT ord, p,s FROM Orders ord JOIN Sold_Pr s on ord.idOrders= s.orders.idOrders JOIN Product p on s.product.idPr= p.idPr  WHERE (ord.status =0 or ord.status=1) and ord.user.idUser=:idUser")
    List<Orders> getManageOrders(String idUser);

    @Query("select sum(s.priceHere) from Orders o join Sold_Pr s on o.idOrders=s.orders.idOrders where year(o.timePickup)=:year and  month(o.timePickup)= :month")
    Integer getTurnOver(int month, int year);

    @Query("select sum(s.priceHere) as turnover   from Orders o join Sold_Pr s on o.idOrders=s.orders.idOrders ")
    Integer getAllTurnOver();

    @Query("select sum(s.amount) as turnover   from Orders o join Sold_Pr s on o.idOrders=s.orders.idOrders where year(o.timePickup)= :year  and  month(o.timePickup)= :month")
    Integer getSalePr(int month, int year);

    @Query("select sum(s.amount) as turnover   from Orders o join Sold_Pr s on o.idOrders=s.orders.idOrders ")
    Integer getAllSalePr();

    List<Orders> findOrdersByIdOrders(String keyword);

    @Query("SELECT o FROM Orders o WHERE o.timePickup = :date OR o.timeOrders = :date")
    List<Orders> findOrdersByDate(LocalDate date);

    @Query("SELECT MAX(CAST(REPLACE(o.idOrders, 'orders', '') AS INTEGER )) FROM Orders o")
    int getMaxId();

    @Transactional
    @Query("SELECT o from Orders o where o.idOrders=:id")
    Orders getOrdersByIdOrders(String id);

    @Modifying
    @Transactional
    @Query("UPDATE Orders o set o.status=:status where o.idOrders=:idOrder")
    void setConditionOrder(String idOrder, int status);
}

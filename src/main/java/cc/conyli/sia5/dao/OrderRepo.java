package cc.conyli.sia5.dao;

import cc.conyli.sia5.entity.Order;
import cc.conyli.sia5.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByUser(User user);


    List<Order> findOrdersByUserOrderByIdDesc(User user);

}

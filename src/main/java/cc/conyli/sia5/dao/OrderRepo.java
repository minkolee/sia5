package cc.conyli.sia5.dao;

import cc.conyli.sia5.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Order, Integer> {


}

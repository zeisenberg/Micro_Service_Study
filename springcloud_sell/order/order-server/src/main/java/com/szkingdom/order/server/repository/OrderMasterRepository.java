package com.szkingdom.order.server.repository;

import com.szkingdom.order.server.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author zhaizhengwei
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}

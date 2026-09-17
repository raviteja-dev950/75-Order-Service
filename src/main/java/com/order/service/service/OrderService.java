package com.order.service.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.service.model.Order;
import com.order.service.model.OrderStatus;
import com.order.service.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        
        double total = 0;
        if(order.getOrderItems() != null) {
            for(var item : order.getOrderItems()) {
                item.setOrder(order);
                total += item.getPrice() * item.getQuantity();
            }
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        if(order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }
    
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
package org.training.turkcell.order.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.training.turkcell.order.rest.models.OrderDto;
import org.training.turkcell.order.services.models.Order;

import java.util.List;

@Mapper
public interface IOrderMappings {

    IOrderMappings ORDER_MAPPINGS = Mappers.getMapper(IOrderMappings.class);

    Order toOrder(OrderDto order);

    OrderDto toOrderDto(Order order);

    List<Order> toOrders(List<OrderDto> order);

    List<OrderDto> toOrderDtos(List<Order> order);


}

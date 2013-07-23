package com.harsha.samples;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/META-INF/spring/app-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testOrderRepository() {
		Order order = new Order();
		order.setCustomer("My Self");
		Item item1 = new Item();
		item1.setPrice(24.55);
		item1.setProduct("Book");
		item1.setQuantity(1);
		Item item2 = new Item();
		item2.setPrice(24.55);
		item2.setProduct("TOY STORY");
		item2.setQuantity(2);

		order.setItems(Arrays.asList(item1, item2));

		Order savedOrder = orderRepository.save(order);

		assertEquals(1, orderRepository.count());

		Order foundOrder = orderRepository.findOne(savedOrder.getId());

		assertEquals("My Self", foundOrder.getCustomer());

		List<Order> orderByCustomer = orderRepository.findByCustomer("My Self");

		assertEquals(1, orderByCustomer.size());
	}

}

package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.data.OrderRepository;
import javax.validation.Valid;
import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		orderRepo.save(order);
		// 주문 객체가 데이터베이스에 저장된 후에는 더 이상 세션에 보존할 필요가 없다.
		// 그러나 만일 제거하지 않으면 이전 주문 및 이것과 연관한 타코가 세션에 남게 되어
		// 다음 주문이 이전 주문에 포함되었던 타코 객체를 가지고 시작하게 된다.
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}
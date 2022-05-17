package tacos.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	
	// 특정 우편번호 코드로 배달된 모든 주문 데이터도 조회가 되어야 한다고 할 때,
	// 다음과 같이 메서드를 선언하면 쉽게 해결될 수 있다.
	// 스프링 데이터는 일종의 DSL을 정의하고 있어서 퍼시스턴스에 관한 내용이 리포지토리 메서드의 시그니처에 해당한다.
	List<Order> findByDeliveryZip(String deliveryZip);
	
	// 지정된 일자 범위 내에서 특정 Zip 코드로 배달된 모든 주문을 쿼리해야 한다고 가정할 때 다음과 같이 작성할 수 있다.
	List<Order> readOrderByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
	
	// 모든 String 비교에서 대소문자를 무시하기 위해 IgnoringCase와 AllIgnoringCase 또는 AllIgnoresCase를 메서드 이름으로 사용할 수 있다.
	List<Order> findByDeliveryCityAllIgnoresCase(String deliveryTo, String deliveryCity);
	
	// 결괏값을 기준으로 결과를 정렬하기 위해서 OrderBy를 추가할 수도 있다.
	List<Order> findByDeliveryCityOrderByDeliveryTo(String city);
	
	// 쿼리를 쓸 수도 있다.
	@Query("Order o where o.deliveryCity='Seattle'")
	List<Order> readOrdersDevliveredInSeattle();
	
}
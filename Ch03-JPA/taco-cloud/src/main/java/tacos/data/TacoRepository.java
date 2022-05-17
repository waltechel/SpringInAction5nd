package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Taco;

//첫 번째 매개변수는 리포지토리에 저장되는 개체 타입이며
//두 번째 매개변수는 개체 ID 속성의 타입이다.
public interface TacoRepository extends CrudRepository<Taco, Long> {
	
}
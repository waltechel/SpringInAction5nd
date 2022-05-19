package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

// lombok이라는 좋은 라이브러리를 사용해서 그런 메서드들을 런타임 시에 자동으로 생성하기 때문이다.
@Data
@RequiredArgsConstructor
public class Ingredient {

	private final String id;
	private final String name;
	private final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
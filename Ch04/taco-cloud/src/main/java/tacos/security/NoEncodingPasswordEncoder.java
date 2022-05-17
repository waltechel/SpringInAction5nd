package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.security.NoEncodingPasswordEncoder;

public class NoEncodingPasswordEncoder implements PasswordEncoder {
	
	// 인코딩이 사실 toString이므로 결국 matches는 평문을 비교학 ㅔ된다.
	@Override
	public String encode(CharSequence rawPwd) {
		return rawPwd.toString();
	}
	
	@Override
	public boolean matches(CharSequence rawPwd, String encodedPwd) {
		return rawPwd.toString().equals(encodedPwd);
	}
}
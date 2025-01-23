package my.adg.backend.global.pattern;

public class Patterns {
	public static final String PASSWORD_REGEXP = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}:\";'<>?,./]).{6,16}$|^(?=.*[a-zA-Z])(?=.*[0-9]).{6,16}$|^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-={}:\";'<>?,./]).{6,16}$|^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}:\";'<>?,./]).{6,16}$";
	public static final String PASSWORD_MESSAGE = "비밀번호는 6~16자 영문 대 소문자, 숫자, 특수문자 중 2개 이상을 포함해야 합니다.";
}

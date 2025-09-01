package domain;

public enum Code {
	ERR_ATTEMPT ("406"),
	WIN_ATTEMPT ("200"),
	COR_ATTEMPT ("100"),
	END_GAME ("201"),
	NO_EX_WORD("404");
	String code;
	Code(String code){
		this.code = code;
	}
}
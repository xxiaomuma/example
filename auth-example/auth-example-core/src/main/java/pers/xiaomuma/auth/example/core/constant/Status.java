package pers.xiaomuma.auth.example.core.constant;

import java.util.Objects;

public enum Status {

	DELETED(-1, "删除"),
	DISABLE(0, "禁用"),
	ENABLE(1, "开启");

	private int code;
	private String desc;

	Status(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static Status of(int code) {
		for (Status status : values()) {
			if (status.code == code) {
				return status;
			}
		}
		return DELETED;
	}

	public boolean isEnable() { return Objects.equals(this, Status.ENABLE); }

	public boolean isDelete() {
		return Objects.equals(this, Status.DELETED);
	}

	public boolean visible() {
		return this != Status.DELETED;
	}

	public static boolean isDeleted(int code) {
		return of(code) == Status.DELETED;
	}

}

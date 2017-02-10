package com.vzwcorp.pricinglab.loader.util;

public class CpfContextHolder {
	private static final ThreadLocal<CpfContext> contextHolder = new ThreadLocal<CpfContext>();

	public static void setCpfContext(CpfContext CpfContext) {
		contextHolder.set(CpfContext);
	}

	public static CpfContext getCpfContext() {
		return (CpfContext) contextHolder.get();
	}

	public static void clearCpfContext() {
		contextHolder.remove();
	}

}


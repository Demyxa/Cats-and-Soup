package com.demyxa.catsoup.core.util;

import java.util.UUID;

public class Util {
	
	private static final long UUID_BASE = 109506000905L;
	private static long UUIDAdd = 1L;

	public static UUID generateNewUUID()
	{
		UUID uuid = new UUID(UUID_BASE, UUIDAdd);
		UUIDAdd++;
		return uuid;
	}

}

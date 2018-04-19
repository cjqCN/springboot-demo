package com.example.demo.util.id;

/**
 * 雪花 ID 生成器，eg：417454619141214208（18位）
 *
 * @author zch
 */
public class GenerateIdUtil {

	private static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);

	public static Long getId() {
		return snowflakeIdWorker.nextId();
	}
}


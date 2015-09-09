package br.com.wgengenharia.manager.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date updateDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		return c.getTime();
	}
}

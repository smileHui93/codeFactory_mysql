package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 */
	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getTime());
	}

}

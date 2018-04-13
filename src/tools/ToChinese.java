package tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ToChinese {
	public  static String convert(String str) throws UnsupportedEncodingException{
		int counts = str.length() / 3;
		String temp = null;
		String result = "";
		for (int i = 0; i < counts; i++) {
			temp = str.substring(i * 3, (i + 1) * 3);
			int oct = Integer.parseInt(temp, 8);
			String hex = Integer.toHexString(oct);
			result = result.concat("%" + hex);
		}
		String strUTF8 = URLDecoder.decode(result, "UTF-8");
		System.out.println(strUTF8);
		return strUTF8;
	}
}

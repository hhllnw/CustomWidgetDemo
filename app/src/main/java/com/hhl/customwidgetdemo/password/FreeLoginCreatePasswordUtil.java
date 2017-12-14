package com.hhl.customwidgetdemo.password;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhl on 2017/10/26.
 */

public class FreeLoginCreatePasswordUtil {

	public static void main(String[] args) {
		String password1 = getPassword("");
		String password2 = getPassword(password1);
		String password3 = getPassword(password2);
		String password4 = getPassword(password3);
		StringBuffer sb = new StringBuffer();
		sb.append(password1);
		sb.append(password2);
		sb.append(password3);
		sb.append(password4);
		System.out.print("password:" + sb.toString().toUpperCase());
	}

	private static FreeLoginCreatePasswordUtil instance;

	private FreeLoginCreatePasswordUtil() {
	}

	public static FreeLoginCreatePasswordUtil getInstance() {
		if (instance == null) {
			instance = new FreeLoginCreatePasswordUtil();
		}
		return instance;
	}

	private static String sha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());

			byte[] md = mdTemp.digest();
			int j = md.length;
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < j; i++) {
				s.append("" + byteToBit(md[i]));
			}
			return s.toString();
		} catch (Exception e) {
			return null;
		}
	}

	private static String byteToBit(byte b) {
		return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
				+ (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
				+ (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
				+ (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
	}

	private static String getN(List<Integer> ns) {
		int N = 0;
		if (ns != null && ns.size() == 6) {
			for (int i = 0; i < ns.size(); i++) {
				N += Math.pow(2, ns.size() - 1 - i) * ns.get(i);
			}
		}
		// Logger.d("sha1", "N:" + N);
		if (N >= 0 && N <= 9) {
			return "9";
		} else if (N == 10) {
			return "a";
		} else if (N >= 11 && N <= 36) {
			return (char) (N + 54) + "";
		} else if (N == 37) {
			return "9";
		} else if (N >= 38 && N <= 63) {
			return (char) (N + 59) + "";
		}
		return null;
	}

	/**
	 * 获取认证密码
	 * 
	 * @param data
	 * @throws Exception
	 */
	public static String getPassword(String data) {
		String sha1 = sha1(data);
		List<String> list = new ArrayList<>();
		StringBuilder password = new StringBuilder();
		if (sha1 != null && sha1.length() == 160) {
			for (int i = 0; i < 16; i++) {
				list.add(sha1.substring(10 * i, 10 * (i + 1)));
			}
		}

		for (String s : list) {
			if (s != null && s.length() == 10) {
				List<String> schilds = new ArrayList<>();
				for (int i = 0; i < 10; i++) {
					schilds.add(s.substring(i, i + 1));
				}
				List<Integer> Ns = new ArrayList<>();
				for (int i = 0; i < schilds.size(); i++) {
					String sc = schilds.get(i);
					if (i == 0 || i == 2 || i == 4 || i == 5 || i == 7
							|| i == 9) {
						int b = Integer.valueOf(sc);
						Ns.add(b);
					}
				}
				String NValue = getN(Ns);
				password.append(NValue);
			}
		}
		return password.toString();
	}
	//testPassword:P5JNYwfgdNt6gxVo9xeiNqkR5si7bDLzLi6EpzVko6dNW
}

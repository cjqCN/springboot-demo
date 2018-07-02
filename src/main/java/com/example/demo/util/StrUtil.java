package com.example.demo.util;


import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import javax.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.*;
import java.util.regex.Pattern;


public class StrUtil extends org.apache.commons.lang3.StringUtils {


	public static void main(String[] args) {
		System.out.println(stringToBase64StringByGuava("hello world"));
		System.out.println(base64StringToSourceStringByGuava(stringToBase64StringByCodec("hello world")));
		System.out.println(checkIsEmail("123@122.com"));
	}


	/**
	 * 检查字符串是否为空。null、“” 是空字符
	 * 如果为空则抛出NullPointerException异常
	 *
	 * @param reference
	 * @return reference
	 */
	public static String checkNotEmpty(final String reference) {
		return checkNotEmpty(reference, "参数为NULL");
	}

	/**
	 * 检查字符串是否为空。null、“” 是空字符
	 * 如果为空则抛出NullPointerException异常
	 *
	 * @param reference
	 * @param errorMessage 异常信息
	 * @return reference
	 */
	public static String checkNotEmpty(final String reference, @Nullable Object errorMessage) {
		if (isEmpty(reference)) {
			throw new NullPointerException(String.valueOf(errorMessage));
		}
		return reference;
	}

	/**
	 * 检查字符串是否为NULL
	 * 如果为空则抛出NullPointerException异常
	 *
	 * @param reference
	 * @return reference
	 */
	public static String checkNotNull(final String reference) {
		return checkNotNull(reference, "参数为NULL");
	}

	/**
	 * 检查字符串是否为NULL
	 * 如果为空则抛出NullPointerException异常
	 *
	 * @param reference
	 * @param errorMessage 异常信息
	 * @return reference
	 */
	public static String checkNotNull(final String reference, @Nullable Object errorMessage) {
		if (null == reference) {
			throw new NullPointerException(String.valueOf(errorMessage));
		}
		return reference;
	}

	//=================================================================================================

	/**
	 * 编码
	 *
	 * @param text            需要编码的内容
	 * @param charsetEncoding 指定编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeURL(final String text, String charsetEncoding) {

		if (isBlank(text)) {
			return null;
		}

		if (isBlank(charsetEncoding)) {
			charsetEncoding = "UTF-8";
		}

		try {
			return URLEncoder.encode(text, charsetEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 解码
	 *
	 * @param text            需要解码的内容
	 * @param charsetEncoding 指定编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeURL(final String text, String charsetEncoding) {
		if (isBlank(text)) {
			return null;
		}

		if (isBlank(charsetEncoding)) {
			charsetEncoding = "UTF-8";
		}
		try {
			return URLDecoder.decode(text, charsetEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}


	//==============================================================================================================

	/**
	 * 检查是否以指定字符开头
	 *
	 * @param string
	 * @param prefixString
	 * @return
	 */
	public static boolean checkStartsWithString(final String string, final String prefixString) {
		if (isBlank(string) || isBlank(prefixString)) {
			return false;
		}
		return string.startsWith(prefixString);
	}

	/**
	 * 检查是否以指定字符结尾
	 *
	 * @param string
	 * @param suffixString
	 * @return
	 */
	public static boolean checkEndsWithString(final String string, final String suffixString) {
		if (isBlank(string) || isBlank(suffixString)) {
			return false;
		}
		return string.endsWith(suffixString);
	}

	/**
	 * 检查是否包含指定字符
	 *
	 * @param string
	 * @param containsString 被包含字符
	 * @return
	 */
	public static boolean checkContainsString(final String string, final String containsString) {
		if (isBlank(string) || isBlank(containsString)) {
			return false;
		}
		return string.contains(containsString);
	}


	//===========================================================================================================

	/**
	 * 字符串匹配
	 *
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean stringMatching(final String regex, final String str) {
		if (isBlank(regex) || isBlank(str)) {
			return false;
		}
		return Pattern.matches(regex, str);
	}


	/**
	 * 检查字符串是否包含汉字
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsIncludeChineseCharacter(final String string) {
		final String regex = "[\\u4e00-\\u9fa5]";
		return stringMatching(regex, string);
	}


	/**
	 * 检查字符串是否包含字母
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsIncludeEnglishLetters(final String string) {
		final String regex = "[A-Za-z]+";
		return stringMatching(regex, string);
	}


	/**
	 * 检查字符串是否包含数字
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsIncludeNumber(final String string) {
		final String regex = "[0-9]+";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否是字母和数字组成(可以是全部数字/字母或是两种混合)
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsNumberAndLetter(final String string) {
		final String regex = "[A-Za-z0-9]+";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合邮箱规则
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsEmail(final String string) {
		final String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\" +
				".)" +
				"+[\\w](?:[\\w-]*[\\w])?";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合手机规则
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsMobile(final String string) {
		final String regex = "1(\\d{10})";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合身份证规则(由于目前国内15位的身份证已经越来少了,所以忽略不算)
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsIdCard(final String string) {
		final String regex = "(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合身份证是否符合成年人18岁规则
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsIdCardAndExceed18(final String string) {
		if (checkIsIdCard(string)) {
			Calendar calendar = Calendar.getInstance();
			Integer birthYear = Integer.parseInt(string.substring(6, 10));
			Integer newYear = calendar.get(Calendar.YEAR);
			if ((newYear - birthYear) >= 18) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 检查字符串是否符合固定电话规则
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsTelephone(final String string) {
		final String regex = "\\d{1,}-\\d{1,}-?\\d*";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合邮政编码规则
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsPostCode(final String string) {
		final String regex = "[1-9]\\d{5}";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合IP地址规则
	 *
	 * @param string
	 * @return true符合要求
	 */
	public static boolean checkIsIpV4(final String string) {
		final String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1," +
				"2})?))";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合URL地址规则(必须包含http开头)
	 *
	 * @param string
	 * @return true符合要求
	 */
	public static boolean checkIsUrl(final String string) {
		final String regex = "(http(s)?)://((w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?" +
				"(&" +
				".+=.*)?)?";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合URL地址规则(不必须包含http开头)
	 *
	 * @param string
	 * @return true符合要求
	 */
	public static boolean checkIsUrlCanNoIncludeHttp(final String string) {
		final String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=" +
				".*)?)?";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合QQ号规则
	 *
	 * @param string
	 * @return true符合要求
	 */
	public static boolean checkIsQQNum(final String string) {
		final String regex = "[1-9][0-9]{4,11}";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否含有HTML标签
	 *
	 * @param string
	 * @return true包含html标签
	 */
	public static boolean checkIsIncludeHtml(final String string) {
		final String regex = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合正整数规则
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsPositiveInteger(final String string) {
		final String regex = "[1-9]\\d*";
		return stringMatching(regex, string);
	}


	/**
	 * 检查字符串是否符合整数规则(可以正负)
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsInteger(final String string) {
		final String regex = "-?[1-9]\\d*";
		return stringMatching(regex, string);
	}

	/**
	 * 检查字符串是否符合正浮点数规则
	 *
	 * @param string
	 * @return
	 */
	public static boolean checkIsPositiveFloat(final String string) {
		final String regex = "[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*";
		return stringMatching(regex, string);
	}

	/**
	 * 把一个字符串通过特定字符,拆分成一个 Iterable
	 *
	 * @param separator     根据此间隔符拆分
	 * @param stringContent 被拆分的字符串
	 *                      被拆分的字符串如果有空字符会被过滤;
	 *                      元素前后有空格会把空格去掉
	 * @return
	 */
	public static Iterable<String> splitStringToIterableByGuava(final char separator, final String stringContent) {
		if (isNotBlank(stringContent)) {
			return Splitter.on(separator).trimResults().omitEmptyStrings().split(stringContent);
		}
		return null;
	}


	/**
	 * 把一个map数据通过特定字符,拼接成一个字符串
	 *
	 * @param keyValueSeparator map的key=value的间隔符
	 * @param elementSeparator  map元素之间的间隔符
	 * @param map               map的key或是value都不能有null,不然会报错
	 *                          如果有null,要转成指定字符串是多加个一个useForNull("")方法即可
	 * @return
	 */
	public static String joinStringFromMapByGuava(final String keyValueSeparator, final char elementSeparator, Map
			map) {
		if (map != null) {
			return Joiner.on(elementSeparator).withKeyValueSeparator(keyValueSeparator).join(map);
		}
		return null;
	}


	/**
	 * 把一个list数据通过特定字符,拼接成一个字符串
	 *
	 * @param separator 拼接间隔字符
	 * @param list      被拼接数据
	 *                  会跳过null,但是空字符不会被跳过
	 * @return
	 */
	public static String joinStringFromListByGuava(final char separator, final List list) {
		if (list != null && list.size() > 0) {
			return Joiner.on(separator).skipNulls().join(list);
		}
		return null;
	}


	/**
	 * 把字符串编码为 MD5 字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String stringToMD5StringByGuava(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return Hashing.md5().hashString(sourceString, Charset.forName(Charsets.UTF_8.toString())).toString();
		}
		return null;
	}

	/**
	 * 把 byte 类型的信息 md5 成 byte 类型信息
	 *
	 * @param context
	 * @return
	 */
	private static byte[] byteToMD5Byte(byte[] context) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md.digest(context);
	}

	/**
	 * 把字符串编码为 sha1 字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String stringToSha1StringByGuava(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return Hashing.sha1().hashString(sourceString, Charset.forName(Charsets.UTF_8.toString())).toString();
		}
		return null;
	}

	/**
	 * 把字符串编码为 sha256 字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String stringToSha256StringByGuava(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return Hashing.sha256().hashString(sourceString, Charset.forName(Charsets.UTF_8.toString())).toString();
		}
		return null;
	}

	/**
	 * 把字符串编码为 sha512 字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String stringToSha512StringByGuava(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return Hashing.sha512().hashString(sourceString, Charset.forName(Charsets.UTF_8.toString())).toString();
		}
		return null;
	}


	/**
	 * 把字符串编码为base16进制字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String stringToBase16StringByGuava(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return BaseEncoding.base16().encode(sourceString.getBytes());
		}
		return null;
	}

	/**
	 * 把base16字符串解码为源字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String base16StringToSourceStringByGuava(final String base16String) {
		if (isNotBlank(base16String)) {
			return new String(BaseEncoding.base16().decode(base16String));
		}
		return null;
	}


	/**
	 * 把字符串编码为base32进制字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String stringToBase32StringByGuava(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return BaseEncoding.base32().encode(sourceString.getBytes());
		}
		return null;
	}

	/**
	 * 把base32字符串解码为源字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String base32StringToSourceStringByGuava(final String base32String) {
		if (isNotBlank(base32String)) {
			return new String(BaseEncoding.base32().decode(base32String));
		}
		return null;
	}


	/**
	 * 把字符串编码为base64进制字符串(通过Google guava)
	 * 这种 base64 出来的结果会带等号，比如：aHR0cDovL3d3dy5Zb3VNZWVrLmNvbT9zZGZhPTIzMjE0JmRkZD0zMw==
	 *
	 * @return
	 */
	public static String stringToBase64StringByGuava(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return BaseEncoding.base64().encode(sourceString.getBytes());
		}
		return null;
	}

	/**
	 * 把字符串编码为base64进制字符串(通过 commons codec)
	 * 这种 base64 出来的结果不会带等号，推荐使用，比如：aHR0cDovL3d3dy5Zb3VNZWVrLmNvbT9zZGZhPTIzMjE0JmRkZD0zMw
	 *
	 * @return
	 */
	public static String stringToBase64StringByCodec(final String sourceString) {
		if (isNotBlank(sourceString)) {
			return Base64.encodeBase64URLSafeString(sourceString.getBytes());
		}
		return null;
	}

	/**
	 * 把字节编码为base64进制字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String byteToBase64StringByGuava(final byte[] bytes) {
		if (null != bytes) {
			return BaseEncoding.base64().encode(bytes);
		}
		return null;
	}

	/**
	 * 把base64字符串解码为源字符串(通过Google guava)
	 *
	 * @return
	 */
	public static String base64StringToSourceStringByGuava(final String base64String) {
		if (isNotBlank(base64String)) {
			return new String(BaseEncoding.base64().decode(base64String));
		}
		return null;
	}

	/**
	 * 把base64字符串解码为源字符串(通过 commons codec) （推荐）
	 *
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String base64StringToSourceStringByCodec(final String base64String) {
		if (isNotBlank(base64String)) {
			return new String(Base64.decodeBase64(base64String));
		}
		return null;
	}

	/**
	 * 把base64字符串解码为源字符串数组(通过Google guava)
	 *
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static byte[] base64StringToSourceByteByGuava(final String base64String) {
		if (isNotBlank(base64String)) {
			return BaseEncoding.base64().decode(base64String);
		}
		return null;
	}


	//============================================================================================================

	private final static String[] hexDigits = {
			"0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F"};

	/**
	 * 转换字节数组为16进制字串
	 *
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(final byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (byte temp : b) {
			resultSb.append(byteToHexString(temp));
		}
		return resultSb.toString();
	}

	/**
	 * 转换字符串为16进制字串
	 *
	 * @param bString 字符串
	 * @return 16进制字串
	 */
	public static String stringToHexString(final String bString) {
		byte[] b = bString.getBytes();
		StringBuilder resultSb = new StringBuilder();
		for (byte temp : b) {
			resultSb.append(byteToHexString(temp));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(final byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 获取 URL 后缀
	 * 比如："/template/abc/login.jsp"，得到的结果是：.jsp
	 *
	 * @param url
	 * @return
	 */
	public static String getUrlSuffix(final String url) {
		if (isNotBlank(url)) {
			return substring(url, indexOf(url, "."), url.length());
		}
		return null;
	}

	/**
	 * 把带有指定分隔符的字符串转换成Long类型的List
	 *
	 * @param ids
	 * @param separatorChars
	 * @return
	 */
	public static List<Long> stringToLongListByIds(final String ids, final String separatorChars) {
		if (isNotBlank(ids) && isNotBlank(separatorChars)) {
			String[] splitArray = split(ids, separatorChars);
			List<Long> idsByLong = new ArrayList<Long>();
			for (String temp : splitArray) {
				//校验是否其下还有未删除的子节点
				Long idByLong = Long.valueOf(temp);
				idsByLong.add(idByLong);
			}
			return idsByLong;
		}
		return null;
	}


	/**
	 * 驼峰命名法工具
	 *
	 * @return toCamelCase(hello_world) == "helloWorld"
	 */
	public static String toCamelCase(String s) {
		final char SEPARATOR = '_';
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 *
	 * @return toCapitalizeCamelCase(hello_world) == "HelloWorld"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 *
	 * @return toUnderScoreCase(helloWorld) = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		final char SEPARATOR = '_';
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 判断文件 contentType 是否是图片的格式
	 * 使用方法：
	 * <p>
	 * MultipartFile picFileInfo;
	 * String contentType = picFileInfo.getContentType();
	 *
	 * @param contentType
	 * @return
	 */
	public static Boolean judgePictureFormat(String contentType) {
		if (isBlank(contentType)) {
			return false;
		}
		return contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/jpeg") || contentType
				.equalsIgnoreCase("image/jpg") || contentType.equalsIgnoreCase("image/gif");
	}


	/**
	 * 根据中文首字母排序
	 *
	 * @param stringList
	 * @return
	 */
	public static List sortChineseStringList(List<String> stringList) {
		if (stringList.size() == 0) {
			return null;
		}

		//把list转换成数组
		String[] arrStrings = stringList.toArray(new String[stringList.size()]);

		// Collator 类是用来执行区分语言环境的 String 比较的，这里选择使用CHINA
		Comparator comparator = Collator.getInstance(Locale.CHINA);
		// 使根据指定比较器产生的顺序对指定对象数组进行排序。
		Arrays.sort(arrStrings, comparator);
		//把数组转换成List（不过Arrays.asList()方法返回的List不能add对象，因为该方法的实现是使用参数引用的数组的大小来new的一个ArrayList，会报异常：java.lang
		// .UnsupportedOperationException）
		return Arrays.asList(arrStrings);
	}

	/**
	 * 拼接字符串，把多个字符串拼接成一个字符串
	 *
	 * @param separator 拼接符，可以为空字符，但是不能为空格
	 * @param rest      需要拼接的字符串内容
	 * @return
	 */
	public static String joinString(String separator, Object... rest) {
		if (isBlank(separator)) {
			separator = "";
		}
		return Joiner.on(separator).skipNulls().join(rest);
	}

	/**
	 * 把字符串转换成 List（通过 Guava）
	 *
	 * @param separator
	 * @param target
	 * @return
	 */
	public static List<String> stringToList(String separator, String target) {
		return Splitter.on(separator).trimResults().splitToList(target);
	}

	/**
	 * 把 List 转换成字符串
	 *
	 * @param separator
	 * @param target
	 * @return
	 */
	public static String listToString(String separator, List target) {
		return Joiner.on(separator).skipNulls().join(target);
	}

	/**
	 * 把字符串转换成 Map
	 *
	 * @param separator
	 * @param keyValueSeparator
	 * @param target            需要转换的字符串，建议有类似这样的格式："John=first,Adam=second";
	 * @return
	 */
	public static Map<String, String> stringToMap(String separator, String keyValueSeparator, String target) {
		return Splitter.on(separator).withKeyValueSeparator(keyValueSeparator).split(target);
	}

	/**
	 * 把 Map 转换成字符串
	 *
	 * @param separator
	 * @param keyValueSeparator
	 * @param target
	 * @return
	 */
	public static String mapToString(String separator, String keyValueSeparator, Map target) {
		//key和value不能有null
		return Joiner.on(separator).withKeyValueSeparator(keyValueSeparator).join(target);

	}

	/**
	 * 寻找两个字符串之间共同的前缀，也就是从一个位置开始算
	 * eg：
	 * <p>
	 * String param1 = ",13,2,3,4,5,6,";
	 * String param2 = ",134,222,3,44,5,666,";
	 * String result = StrUtils.findCommonPrefix(param1, param2);
	 * 结果为：,13
	 *
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String findCommonPrefix(String param1, String param2) {
		return Strings.commonPrefix(param1, param2);
	}

	/**
	 * 寻找两个字符串之间共同的后缀
	 * eg：
	 * <p>
	 * String param1 = ",13,2,3,4,5,6,";
	 * String param2 = ",134,222,3,44,5,666,";
	 * String result = StrUtils.findCommonSuffix(param1, param2);
	 * 结果为：6,
	 *
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String findCommonSuffix(String param1, String param2) {
		return Strings.commonSuffix(param1, param2);
	}


}

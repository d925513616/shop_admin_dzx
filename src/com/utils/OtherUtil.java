package com.utils;

import java.util.List;

public class OtherUtil {
	//����ĸ��д
	public static String FirstToUpper(String str,String gs) {
		return gs+str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	//�ַ����Ƿ�Ϊ��
	public static boolean StrIsNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	//�ַ�����תint
	public static int[] StrToInt(String[] strArr) {
		int[] intArr = new int[strArr.length];
		for(int a=0;a<strArr.length;a++){
			intArr[a] = Integer.valueOf(strArr[a]);
		}
		return intArr;
	}
}

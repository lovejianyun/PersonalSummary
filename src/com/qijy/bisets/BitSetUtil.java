package com.qijy.bisets;

import java.util.BitSet;

/**
 * 二进制位转换工具
 * @author: jiangcy
 * @date:   2019年12月13日 上午9:09:02
 */
public class BitSetUtil {
	
	/**
	 *   将十进制转换为二进制，获取对应位的二进制的值
	 * @param: @param value10
	 * @param: @param index
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getForBitIndex(Long value10,int index) {
		if(index < 0) {
			return 0;
		}
		BitSet bitset = BitSet.valueOf(new long[]{value10});
		boolean res = bitset.get(index);
		return res?1:0;
	}
	
	/**
	 *  获取二进制所有为1的数量
	 * @param: @param value10
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getBitCount(Long value10) {
		BitSet bitset = BitSet.valueOf(new long[]{value10});
		return bitset.cardinality();
	}
	
	/**
	 * 修改二进制位数数值，获取新的10进制值
	 * @param: @param value10	原十进制值
	 * @param: @param index     二进制下标
	 * @param: @param setValue	设置值0或1
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public static long setBit(Long value10, int index, int setValue) {
		if(value10 == 0 && setValue==0) {
			return 0L;
		}
		if(index < 0) {
			return 0L;
		}
		BitSet bitset = BitSet.valueOf(new long[]{value10});
		bitset.set(index, setValue>0?true:false);
		long[] res = bitset.toLongArray();
		return res.length>0?res[0]:0;
	}
	
	public static int getBitLength(Long value10) {
	    BitSet bitset = BitSet.valueOf(new long[]{value10});
	    return bitset.length();
	}
	
	public static void main(String[] args) {
		long xx = 65535;
		long l = setBit(xx, 0, 0);
		System.out.println(l);
	}
}

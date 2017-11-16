package com.mxj.dstructure;

import java.util.Arrays;


/**
 * 数据结构，数组
 * @author haima
 * @date 2017年11月16日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class CH02_01 {
	public static void main(String[] args) {
		int intCreate=100000;
		int intRand;
		int[][]intArray=new int[2][42];
		
		while(intCreate-->0){
			intRand=(int)(Math.random()*42);
			intArray[0][intRand]++;
			intArray[1][intRand]++;
		}
		
		
		
		
		Arrays.sort(intArray[0]);
		for (int int0 : intArray[0]) {
			System.out.print(int0+",");
		}
		System.out.println("");
		for (int int0 : intArray[1]) {
			System.out.print(int0+",");
		}
		System.out.println("");
		
		
		for(int i=41;i>(41-6);i--){
			
			for(int j=41;j>=0;j--){
				//当次数符合时打印
				if(intArray[0][i]==intArray[1][j]){
					String outcome = String.format("随机数%s,出现次数%s", j+1,intArray[0][i]);
					System.out.println(outcome);
					
					intArray[1][j]=0;
					break;
				}
			}
		}
		
	}
}

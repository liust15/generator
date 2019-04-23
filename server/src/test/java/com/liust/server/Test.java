package com.liust.server;

/**
 * @program: generator
 * @description:
 * @author: liust
 * @create: 2019-04-21 08:55
 **/
public class Test {
    public static void main(String[] args) {
        int a=11;
        int b=4;
        int i=0,j=0;
        int[] aa=new int[a];
        int[] ba=new int[b];
        while (a>0){
            aa[i++]=a;
            a/=2;
        }
        while (b>0){
            ba[j++]=b;
            b/=2;
        }
        int min=Math.min(i--,j--);
        int result=0;
        while (min-->0){
            if(aa[i]==ba[j]){
                result=aa[i];
            }else {
                System.out.println(result);
                return;
            }
            i--;j--;
        }
        System.out.println(Math.min(aa[0],ba[0]));
    }
}

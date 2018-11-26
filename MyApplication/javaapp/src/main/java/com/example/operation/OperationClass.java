package com.example.operation;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/11
 */
public class OperationClass {

    public static final void main(String[] args){

        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || i == 1){
                arr[i] = i;
                System.out.printf("%d => %d \n", i, arr[i]);
                continue;
            }

            arr[i] = arr[i -2] + arr[i-1];

            System.out.printf("%d => %d \n", i, arr[i]);
        }
    }


}

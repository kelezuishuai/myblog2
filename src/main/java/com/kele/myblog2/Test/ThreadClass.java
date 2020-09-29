package com.kele.myblog2.Test;

/**
 * @Version 2019
 * @Author:kele
 * @Date:2020/9/24
 * @Content:
 */
public class ThreadClass {

    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }


}


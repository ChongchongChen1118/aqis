package com.neu.grid.service.Impl;

import com.neu.grid.service.JudgmentService;
import org.springframework.stereotype.Service;

@Service
public class JudgmentServiceImpl implements JudgmentService {
    @Override
    public int judgmentSO(double a) {
        if(a>=0&&a<=50){
            return 1;
        }else if(a>=51&&a<=150){
            return 2;
        }else if(a>=151&&a<=475){
            return 3;
        }else if(a>=476&&a<=800){
            return 4;
        }else if(a>=801&&a<=1600){
            return 5;
        }else if(a>=1600){
            return 6;
        }else {
            return -1;
        }
    }

    @Override
    public int judgmentCO(double a) {
        if(a>=0&&a<=5){
            return 1;
        }else if(a>=6&&a<=10){
            return 2;
        }else if(a>=11&&a<=35){
            return 3;
        }else if(a>=36&&a<=60){
            return 4;
        }else if(a>=61&&a<=90){
            return 5;
        }else if(a>=91&&a<=150){
            return 6;
        }else {
            return -1;
        }
    }

    @Override
    public int judgmentPM(double a) {
        if(a>=0&&a<=35){
            return 1;
        }else if(a>=36&&a<=75){
            return 2;
        }else if(a>=76&&a<=115){
            return 3;
        }else if(a>=116&&a<=150){
            return 4;
        }else if(a>=151&&a<=250){
            return 5;
        }else if(a>=251&&a<=500){
            return 6;
        }else {
            return -1;
        }
    }

    @Override
    public int max(int a,int b,int c){
        int max = 0;                      //分3种情况进行讨论
        if(a >= b && a >= c){
            max =a;                    //num1可能为最大值
        }else if(b >= a && b >= c){
            max = b;                    //num2可能为最大值
        }else{
            max = c;                    //num3可能为最大值
        }
        return max;
    }
}

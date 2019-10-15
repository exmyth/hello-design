package com.exmyth.hello.design.principle.demeter;

public class BossPositive {
    public void commandTecherCountCours(){
        TecherPositive teacher = new TecherPositive();
        teacher.countCourse();
    }
}
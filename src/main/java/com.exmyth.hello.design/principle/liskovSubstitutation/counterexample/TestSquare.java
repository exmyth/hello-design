package com.exmyth.hello.design.principle.liskovSubstitutation.counterexample;

/**
 * 确定继承关系的时候一定要判断好，是否父类的方法子类可以继承。传统意义的正方形是矩形的子类，在这里是不适用的。
 *
 * 子类行为规则应与父类行为规则一致，如果子类达不到这一点，则会违背里氏替换原则，违背里氏替换原则会怎样？继承逻辑混乱，代码不便于维护
 */
public class TestSquare {
    public static void resize(Rectangle rectangle){
        while(rectangle.getWidth()<=rectangle.getLength()){
            rectangle.setWidth(rectangle.getWidth()+1);
            System.out.println("长为"+rectangle.getLength()+"****************宽为"+rectangle.getWidth());
        }
        
    }
    public static void main(String [] args){
       Rectangle rectangle = new Rectangle();
       rectangle.setWidth(10);
       rectangle.setLength(20);
       resize(rectangle);
    }

//    public static void main(String [] args){
//        Square square = new Square();
//        square.setLength(10);
//        resize(square);
//    }
}
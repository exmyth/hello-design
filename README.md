# hello-design

The Way To Architect

## 设计模式

[创建型模式](https://github.com/exmyth/hello-design/blob/master/README_creational.md)：
工厂方法模式，抽象工厂模式，建造者模式，单例模式，原型模式

[结构型模式](https://github.com/exmyth/hello-design/blob/master/README_structural.md)：
适配器模式，装饰者模式，代理模式，外观模式，桥接模式，组合模式，享元模式

行为型模式：策略模式，观察者模式，责任链模式，备忘录模式，模版方法模式，迭代器模式，中介者模式，命令模式，访问者模式，解释器模式，状态模式

## 七大设计原则

不要追求设计原则过度，根据业务场景，成本预算等进行取舍
```text
开闭原则
依赖倒置原则
单一职责原则
接口隔离原则
迪米特法则（最少知道原则）
里氏替换原则
合成/复用原则（组合/复用原则）
```

## UML(Unified Modeling Language, 统一建模语言)

基础，UML类图，UML时序图，UML类关系，UML记忆技巧

### 特点

> UML是一种开放的方法

> 用于说明、可视化、构建和编写一个正在开发的面向对象的、软件密集系统的制品的开放方法

> UML展现了一系列最佳工程实践这些最佳实践, 在对大规模，复杂系统进行建模方面, 特别是在软件架构层次已经被验证有效

### 分类

UML2.2中一共定义了14种图示，分为三大类：

> 结构式图形：强调的是系统式的建模

> 行为式图形：强调系统模型中触发的事件

> 交互式图形：属于行为式图形子集合，强调系统模型中资料流程

结构式图形
```text
静态图（类图，对象图，包图）
实现图（组件图，部署图）
剖面图
复合结构图
```

行为式图形
```text
活动图
状态图
用例图
```

交互式图形
```text
通信图
交互概述图（UML2.0）
时序图（UML2.0）
时间图（UML2.0）
```

## UML类图

uml包括类，接口，权限，属性，方法
```text
uml箭头：从子类指向父类，只有知道对方信息时才能指向对方方向
空心三角形：继承或实现
实线：继承   积极的，强关联，关联，通常一个类中有一个类的对象做属性 is a
虚线：实现   消极的，弱关联，依赖，方法入参或出参

空心菱形：聚合，（注：可以看作一个盘子，可以放很多相同的东西）弱关联 has a
实心菱形：组合，（注：代表器皿里有实体结构存在，生死与共）强关联 contains a
```

数字表达含义

常见数字表达及含义，假设有A类和B类，数字标记在A类侧
```text
0..1：0或1个实例。
0..*：0或多个实例　　在生命周期的某一刻，b的实例可以与0个或多个A实例相关
1..1：1个实例.
1：只能有一个实例.
1..*：至少有一个实例.　　b实例可以与一个或多个A实例相关
```
类图说明
```text
+       公共方法
-       private权限
#       protected权限 包内和包外继承的子类都能引用
~       default权限（包权限）只有包内能引用
下划线　  静态 Static
斜体　　  抽象类（或抽象方法）（包含抽象方法的必是抽象类）（类和至少一个方法都是斜体）
方法　　  可以带参，可以不带参; 返回值写到冒号后边，void不用加
<<interface>>    接口
```

关联和依赖的对比
```text
实现接口有两种方式，一种是棒棒糖的形式，另一种是虚线空心三角形的方式
关联是a类中存在b类对象，企鹅类中有气候类的属性
依赖是a类成员方法中有b类的属性，动物新陈代谢方法中有水和空气的属性，只有调这个方法的时候，才可能临时用一下
```

组合和聚合的对比
```text
组合有相同的生命周期，鸟有翅膀，鸟死了，翅膀不复存在
大雁群有大雁，一直大雁挂了，大雁群不会消失
```

继承和实现的对比
```text
实线：继承
虚线：实现
```

## 时序图(Sequence Diagram)

对象, 生命线, 控制焦点 消息

```text
竖线代表生命线
对象：代表实例
消息：箭头代表的元素(open,work等)
竖矩形代表实例处于某种活动中，
实现实箭头：代表方法调用，同步调用
实现虚箭头：代表异步调用
虚线：代表返回
```

## 开闭原则

定义: 一个软件实体如类、模块和函数应该对扩展开放，对修改关闭。

优点: 提高软件系统的可复用性和可维护性

核心思想: 用抽象构建框架，用实现拓展细节。面向抽象编程，（因为抽象是稳定的）

理解: 不改变原先的业务逻辑，新增的功能点通过重写复用的方法进行编程

> com.exmyth.hello.design.principle.openclose.Test

## 依赖倒置原则

定义：高层模块不应该依赖低层模块，二者都应该依赖其抽象

优点：降低耦合，提高内聚，增强代码的可维护性。

细节描述: 抽象不应该依赖细节, 细节应该依赖抽象; 针对接口编程，不要针对实现编程

注意: 每个类尽量实现接口或者抽象类，或者继承抽象类并且实现接口

## 单一职责原则讲解

定义：不要存在多于一个导致类变更的原因; 单一职责规定 一个类，接口或者方法，只有一个变化的原因

优点：降低类的复杂度，增加可读性，降低变更引起的系统风险，提高系统可维护性

理解：一个类/接口/方法只负责一项职责; 一个类只负责一个职责，假如负责两个职责A客户和B合同，如果A变更，修改A职责的时候，有引起B变更的风险

注意：实际应用中，类不采用单一职责，接口和方法采用单一职责。实际应用，受依赖，组合，聚合这些关系影响，同时受控于项目规模，项目周期，技术人员水平，对进度把控等影响。适当的应用单一职责原则。

## 接口隔离原则

定义：用多个专门的接口，而不使用单一的总接口，客户端不应该依赖它不需要的接口;
一个类对一个类的依赖应该建立在最小的接口上建立单一接口，不要建立庞大臃肿的接口
尽量细化接口，接口中的方法尽量少

优点：符合我们常说的高内聚低耦合的设计思想从而使得类具有很好的可读性、可扩展性和可维护性

总结：细粒度可以进行再组装，粗粒度不可再拆分，所以接口设计的时候尽可能适度的拆分 

## 迪米法特原则

定义：一个对象应该对其他对象保持最少的了解。又叫最少知道原则。

核心：降低类的耦合

优点：降低类的耦合

重点：只和朋友交流，不和陌生人说话

朋友：出现在成员变量、方法的输入、输出参数中的类称为成员朋友类，而出现在方法体内部的类不属于朋友类。

## 里氏替换原则

定义：如果对每一个类型为T1的对象o1，都有类型为T2的对象02，使得以T1定义的所有程序P在所有的对象o1都替换成o2时，程序P的行为没有发生变化，那么类型T2是类型T1的子类型。

扩展：一个软件实体如果适用一个父类的话，那一定适用于其子类，所有引用父类的地方必须能透明地使用其子类的对象，子类对象能够替换父类对象，而程序逻辑不变。（反对子类重写父类）

优点：约束了继承泛滥，很多非子类父类关系的类，没必要使用继承关系；加强程序的可维护性，降低需求变更时引起的风险。
引申意义：子类可以扩展父类的功能，但不能改变父类原有的功能。
```text
含义1：子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法。
含义2：子类中可以增加自己特有的方法。
含义3：当子类的方法重载父类的方法时，方法的前置条件（即方法的输入/入参）要比父类方法的输入参数更宽松。（入参宽松）
含义4：当子类的方法实现父类的方法时（重写/重载或实现抽象方法），方法的后置条件（即方法的输出/返回值）要比父类更严格或相等。（出参严谨）
```

（前两条，约定子类最好不要重写父类的方法，如果一定要重写的话，可以使用组合聚合等方法实现）

（后两条举例：人可以生娃，非洲人欧洲人都可以生娃。反例：变形金刚机器人不可以生娃）

## 合成复用原则

尽量使用组合，聚合，而不是继承关系达到复用软件的目的。

组合聚合（黑箱复用）
```text
优点：降低耦合，提高系统的灵活性。使一个类的变化对其他类造成的影响较小。
缺点：会生成较多的对象进行管理。
```

继承（白箱复用）
```text
优点：新的扩展性容易实现，修改和扩展相对容易。
缺点：父类的方法侵入性的带给子类，父类方法的改变，子类也必须改变，相比耦合较高。
```
组合聚合区别：关系强弱，组合强，聚合弱。







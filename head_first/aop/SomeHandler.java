package head_first.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Yang on 2015/4/2.
 */

/*
众所周知，AOP（面向切面编程）是Spring框架的特色功能之一。通过设置横切关注点（cross cutting concerns），AOP提供了极高的扩展性。那AOP在Spring中是怎样运作的呢？当你只能使用core java，却需要AOP技术时，这个问题的解答变得极为关键。不仅如此，在高级技术岗位的面试中，此类问题也常作为考题出现。这不，我的朋友最近参加了一个面试，就被问到了这样一个棘手的问题——如何在不使用Spring及相关库，只用core Java的条件下实现AOP。因此，我将在本文中提供一份大纲，帮助大家了解如何只用core Java实现一个AOP（当然啦，这种AOP在功能上有一定的局限性）。注意，本文不是一篇有关Spring AOP与Java AOP的对比研究，而是有关在core Java中借助固有的设计模式实现AOP的教程。

想必读者已经知道AOP是什么，也知道在Spring框架中如何使用它，因此本文只着眼于如何在不用Spring的前提下实现AOP。首先，我们得知道，Spring是借助了JDK proxy和CGlib两种技术实现AOP的。JDK dynamic proxy提供了一种灵活的方式来hook一个方法并执行指定的操作，但执行操作时得有一个限制条件：必须先提供一个相关的接口以及该接口的实现类。实践出真知，让我们透过一个案例来理解这句吧！现在有一个计算器程序，用于完成一些数学运算。让我们来考虑下除法功能，此时的问题是：如果core framework 已经具备了一份实现除法的代码，我们能否在代码执行时劫持（highjack）它并执行额外的校验呢？答案是肯定的，我将用下面提供的代码片段来证明这点。首先来看基础接口的代码：

*/

interface Calculator {
    public int calculate(int a, int b);
}

class CalculatorImpl implements Calculator{
    @Override
    public int calculate(int a, int b) {
        return a/b;
    }
}
public class SomeHandler implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Object result = method.invoke(targetObject,params);
        //return result;
        return 0;
    }

    Logger logger = LogManager.getLogManager().getLogger(LogManager.LOGGING_MXBEAN_NAME);
}

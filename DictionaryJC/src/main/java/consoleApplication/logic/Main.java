/**
 * Класс Main - точка входа программы.
 * В классе создаётся объект BeanFactory, ответственный за создание объектов всех классов в и запуск прилржения в методе start()
 * */
package main.java.consoleApplication.logic;

public class Main {

    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.start(beanFactory);
    }
}
package io.github.bluething.playground.java.lambda.pluralsight_lambdaexpressionjava8;

import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Supplier<String> supplier = () -> {
            System.out.println("Inside supplier");
            return "Hii";
        };
        System.out.println("supplier: " + supplier.get());

        Consumer<String> consumer = s -> {
            System.out.println("Inside consumer");
            System.out.println("consume: " + s);
        };
        consumer.accept(supplier.get());

        IntSupplier intSupplier = () -> 10;
        System.out.println("intSupplier: " + intSupplier.getAsInt());

        DoubleToIntFunction doubleToIntFunction = value -> (int) Math.floor(value);
        System.out.println("pii: " + doubleToIntFunction.applyAsInt(Math.PI));

        /**
         * a lambda is an instance of a functional interface
         *
         * lambda expressions have been very aggressively optimized in Java
         *  How fast lambda expressions are?
         *      Lambda expressions are compiled using specific bytecode instructions called invokedynamic introduced in Java 7
         *  What you want to avoid when writing lambda expressions is a mechanism introduced in Java 5 called autoboxing
         *
         *  when you're dealing with numbers, whether they are ints, longs, and doubles,
         *      you need to remember that you have specialized lambda expressions that are going to be much faster than the regular ones
        * */
    }
}
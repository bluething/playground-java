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
    }
}
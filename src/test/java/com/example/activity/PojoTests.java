package com.example.activity;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class PojoTests {
    @Test
    public void Should_Pass_All_Pojo_Tests_Using_All_Testers() {
        assertPojoMethodsFor(Comment.class)
                .testing(Method.GETTER, Method.SETTER)
                .testing(Method.CONSTRUCTOR)
                .areWellImplemented();
    }
}

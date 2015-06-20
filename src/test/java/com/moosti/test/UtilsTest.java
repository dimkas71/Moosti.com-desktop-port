package com.moosti.test;

import com.moosti.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ������������ on 17.06.2015.
 */
@RunWith(value = JUnit4.class)
public class UtilsTest {

    @Test
    public void test1() {
        String actual = Utils.toString("", 20);
        assertThat(actual, is("(20)"));
    }

    @Test
    public void test2() {
        String actual = Utils.toString(".focus", 30);
        assertThat(actual, is(".focus(30)"));
    }

    @Test
    public void test3() {
        String actual = Utils.toString(".shrt brk", 5);
        assertThat(actual, is(".shrt brk(5)"));
    }

    @Test
    public void test4() {
        String actual = Utils.toString(null, 30);
        assertThat(actual, is("(30)"));
    }

    @Test
    public void test5() {
        String actual = Utils.fromMillis(1000);
        assertThat(actual, is("00:01"));
    }

    @Test
    public void test6() {
        String actual = Utils.fromMillis(60000);
        assertThat(actual, is("01:00"));
    }

    @Test
    public void test7() {
        String actual = Utils.fromMillis(135000);
        assertThat(actual, is("02:15"));
    }

    @Test
    public void test8() {
        String actual = Utils.fromMillis(3600000);
        assertThat(actual, is("60:00"));
    }






}

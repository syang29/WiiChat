package com.example.android.wiichat;

import android.os.Bundle;
import android.view.View;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test1() {
        MainActivity m = new MainActivity();
    }
    @Test
    public void test2(){Chatroom c = new Chatroom(); assertFalse(c.checkMessage(""));}
    @Test
    public void test3(){Chatroom c = new Chatroom(); assertFalse(c.checkMessage("   "));}
    @Test
    public void test4(){Chatroom c = new Chatroom(); assertTrue(c.checkMessage("hi"));}
}
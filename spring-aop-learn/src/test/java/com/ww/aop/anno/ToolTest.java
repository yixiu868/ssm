package com.ww.aop.anno;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-20 23:38:26
 */
public class ToolTest {

    @Test
    public void tool() {
        Class<ForumService> clazz = ForumService.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            NeedTest nt = method.getAnnotation(NeedTest.class);
            if (null != nt) {
                if (nt.value()) {
                    System.out.println(method.getName() + "()需要测试");
                } else {
                    System.out.println(method.getName() + "()不需要测试");
                }
            }
        }
    }
}

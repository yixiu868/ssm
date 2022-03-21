package com.ww.aop.anno;

/**
 * TODO
 *
 * @author wanggw
 * @date 2022-03-20 23:35:41
 */
public class ForumService {

    @NeedTest(value = true)
    public void deleteForum(int forumId) {
        System.out.println("删除论坛模块：" + forumId);
    }

    @NeedTest(value = false)
    public void deleteTopic(int topicId) {
        System.out.println("删除论坛主题：" + topicId);
    }
}

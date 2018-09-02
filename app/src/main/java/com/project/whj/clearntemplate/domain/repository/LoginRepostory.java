package com.project.whj.clearntemplate.domain.repository;


/**
 * 包含接口，让外层实现。一般用storage层实现
 * 登录
 */
public interface LoginRepostory {
    /**
     * 提交登录数据到后台服务器
     */
    void TiJiaoLoginData(String userName,String passwd);

    /**
     * 这里后台返回的数据：这里采用接口回调(观察者模式)
     * 1.在Storage层imp下的LoginRepostoryImp设置接口
     * 2.在domain层usecase的LoginUseCaseImp得到观察数据
     *
     * 扩展：
     *      1.可以根据在Storage层下的imp的LoginRepostoryImp中得到后台
     *     返回的数据，直接保存到shp(偏好文件）
     *      2.这里可以定义一个方法获取数据
     */


}

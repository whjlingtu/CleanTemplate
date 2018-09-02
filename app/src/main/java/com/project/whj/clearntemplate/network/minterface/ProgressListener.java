package com.project.whj.clearntemplate.network.minterface;

/**
 * 进度监听：
 *      1.批量上传图片的进度监听
 *      2.文件监听
 */
public interface ProgressListener {
    /**
     * 单文件上传，就不必根据字节去计算，直接在自定义的
     * requestbody中计算好进度，直接返回
     * @param progress 进度
     * @param tag  标识，是哪一个文件正上传
     */
    void onProgress(int progress, String tag);

    /**
     * 处理多文件，需要获取每个文件的即时上传量，来计算整体的上传进度
     * @param wirrten
     * @param total
     * @param tag
     */
    void onDetailProgress(long wirrten, long total, String tag);
}

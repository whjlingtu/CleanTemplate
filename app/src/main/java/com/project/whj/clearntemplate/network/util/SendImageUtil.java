package com.project.whj.clearntemplate.network.util;



import com.project.whj.clearntemplate.network.component.UpLoadFileRequestBody;
import com.project.whj.clearntemplate.network.minterface.ProgressListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * 发送图片工具类
 */
public class SendImageUtil {
    /**
     * 创建多个上传文件
     */
    private static List<File> createUpLoadFile(List<String> path){
        List<File> fileList=new ArrayList<>();
        for(String i:path){
            File file=new File(i);
            fileList.add(file);

        }
        return fileList;
    }


    /**
     * 创建多个自定义RequesBody
     */
    private static List<UpLoadFileRequestBody> createRequseBody(List<File> files, ProgressListener listener){
        List<UpLoadFileRequestBody> bodyList=new ArrayList<>();
        for(int i=0;i<files.size();i++){
            UpLoadFileRequestBody body=new UpLoadFileRequestBody(files.get(i), "" + (i+1),listener);
            bodyList.add(body);
        }
        return bodyList;
    }


    /**
     * 上传图片网络参数设置
     * @param path---图片路径
     * @param canShu---提交个服务器的网络参数
     * @param listener ---进度监听
     * @return
     */
    public static MultipartBody.Part[] setNetCanShu(List<String> path, String canShu, ProgressListener listener){
        if(path==null){
            return null;
        }
        List<File> list=createUpLoadFile(path);
        List<UpLoadFileRequestBody> bodyList=createRequseBody(list,listener);
        int size=bodyList.size();
       // Toast.makeText(context,"RepodySize"+size,Toast.LENGTH_SHORT).show();
        MultipartBody.Part[] parts=new MultipartBody.Part[size];
        for(int i=0;i<size;i++){
            parts[i]=MultipartBody.Part.createFormData(canShu,list.get(i).getName(),bodyList.get(i));
        }
        return parts;
    }
}

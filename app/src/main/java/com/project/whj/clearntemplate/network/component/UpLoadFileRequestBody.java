package com.project.whj.clearntemplate.network.component;

import android.support.annotation.Nullable;

import com.project.whj.clearntemplate.network.minterface.ProgressListener;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * 采用retrofit网络框架：主要实现上传文件进度监听
 * 观察者模式----获取上传文件进度
 */
public class UpLoadFileRequestBody extends RequestBody {

    private RequestBody body;
    private ProgressListener listener;  //注册观察者
    //每个RequestBody对应一个tag,存在map中，保证计算的时候不会重复
    private String tag;

    private BufferedSink bufferedSink;

    //--------------<构造方法>
    public UpLoadFileRequestBody(File file,String tag,ProgressListener listener){
        //属性初始化
        this.body=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        this.listener=listener;
        this.tag=tag;
    }



    public UpLoadFileRequestBody(RequestBody requestBody,ProgressListener listener,String tag){
        this.body=requestBody;
        this.listener=listener;
        this.tag=tag;
    }

    /**
     * @return RequestBody的类型
     */
    @Nullable
    @Override
    public MediaType contentType() {
        return body.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return body.contentLength();
    }

    /**
     *将上传数据写到RequestBody
     * @param sink
     * @throws IOException
     */
    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if(bufferedSink==null){
            bufferedSink= Okio.buffer(mSink(sink));
        }
        body.writeTo(bufferedSink); //RequseBody写入上传数据
        bufferedSink.flush();   //必须调用flush,否则最后一部分数据可能不会被写入
    }

    private Sink mSink(Sink sink){
        return new ForwardingSink(sink) {
            //当前写入字节数
            long byteWritten=0L;
            //总字节数
            long contentLength=0L;
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength==0){
                    contentLength=contentLength();
                }
                //增加当前写入的字节数
                byteWritten+=byteCount;
                BigDecimal b1=new BigDecimal(Double.toString(byteWritten));
                BigDecimal b2=new BigDecimal(Double.toString(contentLength));

                //单文件上传----监听
                //百分比
                listener.onProgress((int) (b1.divide(b2,BigDecimal.ROUND_HALF_DOWN).doubleValue()*100),tag);

                //多文件上传--进度监听
                listener.onDetailProgress(byteWritten,contentLength,tag);
            }
        };
    }
}

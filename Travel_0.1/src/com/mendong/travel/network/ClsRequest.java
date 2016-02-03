/**
 * 项目名：     SystemService
 * 文件名：     ClsRequest.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2012-8-28
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

import java.util.Map;


/**
 * 类名称：     ClsRequest
 * 作者：         Administrator
 * 创建时间：  2012-8-28 下午12:56:55
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-8-28 下午12:56:55
 *
 */
public final class ClsRequest
{
    private int reqCode;

    private byte[] data;

    private boolean useThread;

    private String method;

    private ICallBack callBack;

    private String url;

    private Map<String, String> heads;

    private boolean isEncry;

    private boolean isZip;

    private boolean isNBase64 = true;;

    private String key;

    private int timeout;

    private boolean isXmlParser;

    private String apnst;


    /**
     * 方法名称：  getReqCode
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  reqCode
     * 备注：
     */
    public final int getReqCode()
    {
        return reqCode;
    }

    /**
     * 方法名称： setReqCode
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 reqCode 给 reqCode
     * 备注：
     */
    public final void setReqCode(int reqCode)
    {
        this.reqCode = reqCode;
    }

    //    /**
    //     * 方法名称：  getReqParam
    //     * 作者：          Administrator
    //     * 方法描述：  
    //     * 字段类型：  Object
    //     * 返回字段：  reqParam
    //     * 备注：
    //     */
    //    public final String getBody()
    //    {
    //        return body;
    //    }
    //
    //    /**
    //     * 方法名称： setReqParam
    //     * 作者：         Administrator
    //     * 方法描述：  
    //     * 字段类型：  Object
    //     * 设置字段：  设置 reqParam 给 reqParam
    //     * 备注：
    //     */
    //    public final void setBody(String body)
    //    {
    //        this.body = body;
    //    }

    /**
     * 方法名称：  getData
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  byte[]
     * 返回字段：  data
     * 备注：
     */
    public final byte[] getData()
    {
        return data;
    }

    /**
     * 方法名称： setData
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  byte[]
     * 设置字段：  设置 data 给 data
     * 备注：
     */
    public final void setData(byte[] data)
    {
        this.data = data;
    }

    /**
     * 方法名称：  isUseThread
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 返回字段：  useThread
     * 备注：
     */
    public final boolean isUseThread()
    {
        return useThread;
    }

    /**
     * 方法名称： setUseThread
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 设置字段：  设置 useThread 给 useThread
     * 备注：
     */
    public final void setUseThread(boolean useThread)
    {
        this.useThread = useThread;
    }

    /**
     * 方法名称：  getReqMethod
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  reqMethod
     * 备注：
     */
    public final String getMethod()
    {
        return method;
    }

    /**
     * 方法名称： setReqMethod
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 reqMethod 给 reqMethod
     * 备注：
     */
    public final void setMethod(String method)
    {
        this.method = method;
    }

    /**
     * 方法名称：  getCallBack
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  ICallBack
     * 返回字段：  callBack
     * 备注：
     */
    public final ICallBack getCallBack()
    {
        return callBack;
    }

    /**
     * 方法名称： setCallBack
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  ICallBack
     * 设置字段：  设置 callBack 给 callBack
     * 备注：
     */
    public final void setCallBack(ICallBack callBack)
    {
        this.callBack = callBack;
    }

    /**
     * 方法名称：  getUrl
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  url
     * 备注：
     */
    public final String getUrl()
    {
        return url;
    }

    /**
     * 方法名称： setUrl
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 url 给 url
     * 备注：
     */
    public final void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * 方法名称：  getHeads
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  Map<String,String>
     * 返回字段：  heads
     * 备注：
     */
    public final Map<String, String> getHeads()
    {
        return heads;
    }

    /**
     * 方法名称： setHeads
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  Map<String,String>
     * 设置字段：  设置 heads 给 heads
     * 备注：
     */
    public final void setHeads(Map<String, String> heads)
    {
        this.heads = heads;
    }

    /**
     * 方法名称：  isEncry
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 返回字段：  isEncry
     * 备注：
     */
    public final boolean isEncry()
    {
        return isEncry;
    }

    /**
     * 方法名称： setEncry
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 设置字段：  设置 isEncry 给 isEncry
     * 备注：
     */
    public final void setEncry(boolean isEncry)
    {
        this.isEncry = isEncry;
    }

    /**
     * 方法名称：  isZip
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 返回字段：  isZip
     * 备注：
     */
    public final boolean isZip()
    {
        return isZip;
    }

    /**
     * 方法名称： setZip
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 设置字段：  设置 isZip 给 isZip
     * 备注：
     */
    public final void setZip(boolean isZip)
    {
        this.isZip = isZip;
    }

    /**
     * 方法名称：  isNBase64
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 返回字段：  isNBase64
     * 备注：
     */
    public final boolean isNBase64()
    {
        return isNBase64;
    }

    /**
     * 方法名称： setNBase64
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 设置字段：  设置 isNBase64 给 isNBase64
     * 备注：
     */
    public final void setNBase64(boolean isNBase64)
    {
        this.isNBase64 = isNBase64;
    }

    /**
     * 方法名称：  getKey
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  key
     * 备注：
     */
    public final String getKey()
    {
        return key;
    }

    /**
     * 方法名称： setKey
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 key 给 key
     * 备注：
     */
    public final void setKey(String key)
    {
        this.key = key;
    }

    /**
     * 方法名称：  getTimeout
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  timeout
     * 备注：
     */
    public final int getTimeout()
    {
        return timeout;
    }

    /**
     * 方法名称： setTimeout
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 timeout 给 timeout
     * 备注：
     */
    public final void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }

    /**
     * 方法名称：  isXmlParser
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 返回字段：  isXmlParser
     * 备注：
     */
    public final boolean isXmlParser()
    {
        return isXmlParser;
    }

    /**
     * 方法名称： setXmlParser
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  boolean
     * 设置字段：  设置 isXmlParser 给 isXmlParser
     * 备注：
     */
    public final void setXmlParser(boolean isXmlParser)
    {
        this.isXmlParser = isXmlParser;
    }

    /**
     * 方法名称：  getApnst
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  apnst
     * 备注：
     */
    public final String getApnst()
    {
        return apnst;
    }

    /**
     * 方法名称： setApnst
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 apnst 给 apnst
     * 备注：
     */
    public final void setApnst(String apnst)
    {
        this.apnst = apnst;
    }


}

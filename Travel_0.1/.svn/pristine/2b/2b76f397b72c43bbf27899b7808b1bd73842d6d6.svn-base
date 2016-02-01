/**
 * 项目名：       SystemService
 * 文件名：       ClsUrlConnect.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-21
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.mendong.travel.utils.log.ClsLog;



/**
 * 类名称：       ClsUrlConnect
 * 作者：            Administrator
 * 创建时间：  2012-6-21 下午4:37:21
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-6-21 下午4:37:21
 *
 */
public final class ClsUrlConnect
{
    private static final String TAG = "ClsUrlConnect";

    private HttpURLConnection connection = null;

    private HttpClient httpClient = null;

    private HttpUriRequest httpRequest = null;

    private static final String TYPE_WIFI = "WIFI";

    private static final String TYPE_MOBILE = "MOBILE";

    private ClsResponse openHttpURLConnectionPost(Context mContext,
            ClsRequest req) throws IOException
    {
        connection = getHttpConnection(mContext, req.getUrl());

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        byte[] data = req.getData();
        if (null == req.getHeads())
        {
            connection.setRequestProperty("Content-type", "text/xml");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            if (null != data)
            {
                connection.setRequestProperty("Content-length", data.length
                        + "");
            }
            if (req.isNBase64())
            {
                ClsLog.toast(mContext, "connection add Content-b64");
                connection.setRequestProperty("Content-b64", "nb64");
            }
        }
        else
        {
            //            connection.setRequestProperty("User-Agent", "Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328t Build/IML74K)");
            //                        connection.setRequestProperty("Connection", "keep-alive");
            //                        connection.setRequestProperty("Accept", "*/*");
            //                        connection.setRequestProperty("Accept-Language", "zh-cn,en");
            //                        connection.setRequestProperty("Accept-Charset", "GB2312,utf-8");
            Map<String, String> heads = req.getHeads();
            Iterator<String> it = heads.keySet().iterator();
            while (it.hasNext())
            {
                String key = it.next();
                String value = heads.get(key);
                if (null != key && !"".equals(key) && null != value
                        && !"".equals(value))
                {
                    ClsLog.toast(mContext, "http头信息 key==" + key + ", value=="
                            + value);
                    connection.setRequestProperty(key, value);
                }
            }
        }
        if (req.isZip())
        {
            ClsLog.toast(mContext, "connection add Content-zip");
            connection.setRequestProperty("Content-zip", "gzip");
        }

        if (req.isEncry())
        {
            ClsLog.toast(mContext, "connection add Content-ver");
//            connection.setRequestProperty("Content-ver",
//                    ClsSysManaPro.getPV(mContext));
//            connection.setRequestProperty("Content-inf",
//                    URLEncoder.encode(req.getKey(), "UTF-8"));
        }
        connection.setRequestMethod(req.getMethod());
        connection.setConnectTimeout(req.getTimeout()/*120000*/);
        connection.connect();

        if (null != data)
        {

            OutputStream outStrm = connection.getOutputStream();
            outStrm.write(data);
            //            ClsLog.E("send 明文", t);
            //            ClsLog.E("send 密文", body);
            outStrm.flush();
            outStrm.close();
        }

        int rspCode = connection.getResponseCode();
        //        ClsLog.E("rspCode " + req.getReqCode(), rspCode + "");
        ClsLog.toast(mContext, "rspCode " + req.getReqCode() + " ==  "
                + rspCode);
        //        if (rspCode == 200)
        //        {
        //            mIsPostConnect = true;
        //        }
        if (rspCode == 200)
        {
            ClsResponse rsp = new ClsResponse();
            if (null == req.getHeads())
            {
                String b64 = connection.getHeaderField("Content-b64");
                if (null == b64 || "nb64".equals(b64.toLowerCase()))
                {
                    rsp.setCancelBase64(false);
                }
            }
            else
            {
                rsp.setCancelBase64(true);
            }
            Map<String, List<String>> map = connection.getHeaderFields();
            Map<String, String> heads = new HashMap<String, String>();
            if (null != map)
            {
                for (String key : map.keySet())
                {
                    if (null != key && key.length() > 0)
                    {
                        StringBuilder sb = new StringBuilder();
                        List<String> l = map.get(key);
                        for (int i = 0; i < l.size(); ++i)
                        {
                            String v = l.get(i);
                            if (null != v && v.length() > 0)
                            {
                                sb.append(v);
                            }
                        }
                        String value = sb.toString();
                        if (!TextUtils.isEmpty(value))
                        {
                            heads.put(key, sb.toString());
                        }
                    }
                }
            }
            //            StringBuffer sb = new StringBuffer();
            //            for (String key : map.keySet())
            //            {
            //                if(null != key && key.length() > 0)
            //                {
            //                    sb.append("<hd>");
            //                    sb.append(key).append(":");
            //                    List<String> d = map.get(key);
            //                    for (int i = 0; i < d.size(); ++i)
            //                    {
            //                        String value = d.get(i);
            //                        if (null != value && value.length() > 0)
            //                        {
            //                            sb.append(value);
            //                        }
            //                    }
            //                    sb.append("</hd>");
            //                }
            //            }
            //            String s = sb.toString();
            //            for(String key:map.keySet()){
            //                System.out.print(key+" : ");
            //                List<String> d = map.get(key);
            //                for(String value:d){
            //                    System.out.print(value);
            //                }
            //                System.out.println();
            //            }
            rsp.setHeads(heads);
            rsp.setInputStream(connection.getInputStream());
            return rsp;
        }
        else
        {
            throw new IOException();
        }
        //        return connection.getInputStream();
    }

    private ClsResponse openHttpClientPost(Context mContext, ClsRequest req)
            throws IOException
    {
        httpClient = getHttpClient(mContext);
        
        //测试
//        req.setMethod("GET");
//        req.setNBase64(false);
//        req.setZip(false);
//        req.setData(null);
//        req.setEncry(false);
        
        String method = req.getMethod();
        ClsLog.logToFile("http method ------------> " + method);
        if ("GET".equals(method) || "get".equals(method))
        {
            httpRequest = new HttpGet(req.getUrl());
        }
        else if ("POST".equals(method) || "post".equals(method))
        {
            httpRequest = new HttpPost(req.getUrl());
        }

        if (null == req.getHeads())
        {
            httpRequest.setHeader("Content-type", "text/xml");
            httpRequest.setHeader("Connection", "Keep-Alive");
            httpRequest.setHeader("Charset", "UTF-8");
            //测试
//            httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us)"
//            + " AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0"
//            + " Safari/530.17");
//            httpRequest.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//            httpRequest.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpRequest.setHeader("Cache-Control", "max-age=0");
//            httpRequest.setHeader("Accept-Encoding", "gzip, deflate");
//            httpRequest.setHeader("Referer", "http://shop102667959.taobao.com/category-689789169.htm?spm=a1z10.1.w1015573117.3.tnuGu9&search=y&catName=%B5%B1%BC%BE%C8%C8%C2%F4"
//                    + "Cookie: t=a111367bed4c950010739c4a6e1843ec; cna=ySoRCfIadAsCAd3i27IxS1Bg; tg=0; _cc_=VT5L2FSpdA%3D%3D; tracknick=it206; l=it206::1363665710330::11; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; __utma=6906807.1987776805.1363492945.1363570338.1363662394.3; __utmz=6906807.1363662394.3.3.utmcsr=mtop.taobao.com|utmccn=(referral)|utmcmd=referral|utmcct=/; miid=1538651087471370703; mt=ci=0_0; cookie2=99f73bea45ddc64a4d1873e1efce541f; v=0; uc1=cookie14=UoLa%2BqJrEdD%2FTQ%3D%3D; mpp=t%3D0%26m%3D%26h%3D0%26l%3D0; swfstore=5149");
//            httpRequest.setHeader("DNT", "1");
            
            if (req.isNBase64())
            {
                ClsLog.toast(mContext, "connection add Content-b64");
                httpRequest.setHeader("Content-b64", "nb64");
            }
        }
        else
        {
            Map<String, String> heads = req.getHeads();
            Iterator<String> it = heads.keySet().iterator();
            while (it.hasNext())
            {
                String key = it.next();
                String value = heads.get(key);
                if (null != key && !"".equals(key) && null != value
                        && !"".equals(value))
                {
                    ClsLog.toast(mContext, "http头信息 key==" + key + ", value=="
                            + value);
                    httpRequest.setHeader(key, value);
                }
            }
        }

        if (req.isZip())
        {
            ClsLog.toast(mContext, "connection add Content-zip");
            httpRequest.setHeader("Content-zip", "gzip");
        }

        if (req.isEncry())
        {
            ClsLog.toast(mContext, "connection add Content-ver");
//            httpRequest.setHeader("Content-ver", ClsSysManaPro.getPV(mContext));
//            httpRequest.setHeader("Content-inf",
//                    URLEncoder.encode(req.getKey(), "UTF-8"));
        }

        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
                req.getTimeout());
        HttpConnectionParams.setSoTimeout(httpClient.getParams(),
                req.getTimeout());

        byte[] data = req.getData();
        if (null != data)
        {
            //            String s = new String(data, "UTF-8");
            ((HttpPost) httpRequest).setEntity(new ByteArrayEntity(data));
            //            httpRequest.setHeader("Content-length", data.length + "");
        }

        HttpResponse response = httpClient.execute(httpRequest);

        if (null != response)
        {
            ClsLog.toast(
                    mContext,
                    "响应码-----------------> "
                            + (response.getStatusLine().getStatusCode()));
        }
        if (null != response
                && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {

            ClsResponse rsp = new ClsResponse();
            if (null == req.getHeads())
            {
                Header nb64Header = response.getFirstHeader("Content-b64");
                String b64 = null;
                if (null != nb64Header)
                {
                    b64 = nb64Header.getValue();
                }
                if (null == b64 || "nb64".equals(b64.toLowerCase()))
                {
                    rsp.setCancelBase64(false);
                }
            }
            else
            {
                rsp.setCancelBase64(true);
            }
            Map<String, String> map = new HashMap<String, String>();
            Header[] headers = response.getAllHeaders();
            for (int i = 0; i < headers.length; i++)
            {
                map.put(headers[i].getName(), headers[i].getValue());
            }
            rsp.setHeads(map);
            rsp.setInputStream(response.getEntity().getContent());
            return rsp;
        }
        else
        {
            throw new IOException();
        }
    }

    /**
     * 方法名称：  openPost
     * 作者：            Administrator
     * 方法描述：  打开post连接
     * 输入参数：  @param urlstr
     * 输入参数：  @param mContext
     * 输入参数：  @param obj
     * 输入参数：  @return
     * 输入参数：  @throws Exception
     * 返回类型：  InputStream
     */
    //    String t = "";
    public ClsResponse openPost(Context mContext, ClsRequest req)//String urlstr, Context mContext, String obj, String obj2, String method, String heads)
            throws IOException
    {
        //return openHttpURLConnectionPost(mContext, req);
        return openHttpClientPost(mContext, req);
    }

    /**
     * 方法名称：  getHttpConnection
     * 作者：            Administrator
     * 方法描述：  getHttpConnection
     * 输入参数：  @param mContext
     * 输入参数：  @param mReqUrl
     * 输入参数：  @return
     * 返回类型：  HttpURLConnection
     */
    private HttpURLConnection getHttpConnection(Context mContext, String mReqUrl)
            throws IOException
    {
        // 说明：联网时优先选择WIFI联网，如果WIFI没开或不可用，则使用移动网络
        HttpURLConnection httpURLConn = null;

        // 获取当前可用网络信息
        ConnectivityManager connMng = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInf = connMng.getActiveNetworkInfo();

        // 如果当前是WIFI连接
        if (null != netInf
                && TYPE_WIFI.toLowerCase().equals(
                        netInf.getTypeName().toLowerCase()))
        {
            httpURLConn = (HttpURLConnection) new URL(mReqUrl).openConnection();
            ClsLog.toast(mContext, "当前是WIFI连接");
        }
        // 非WIFI联网
        else if (null != netInf
                && TYPE_MOBILE.toLowerCase().equals(
                        netInf.getTypeName().toLowerCase()))
        {
            String proxyHost = android.net.Proxy.getDefaultHost();
            ClsLog.I(TAG, "proxyHost : " + proxyHost);
            ClsLog.toast(mContext, "proxyHost : " + proxyHost);

            // 代理模式
            if (null != proxyHost)
            {
                java.net.Proxy p = new java.net.Proxy(java.net.Proxy.Type.HTTP,
                        new InetSocketAddress(
                                android.net.Proxy.getDefaultHost(),
                                android.net.Proxy.getDefaultPort()));

                httpURLConn = (HttpURLConnection) new URL(mReqUrl)
                        .openConnection(p);
                ClsLog.toast(mContext, "非WIFI代理");
            }

            // 直连模式
            else
            {
                httpURLConn = (HttpURLConnection) new URL(mReqUrl)
                        .openConnection();
                ClsLog.toast(mContext, "非WIFI直连");
            }
        }
        else
        {
            //"未发现可用网络"
            throw new IOException();
        }
        httpURLConn.setInstanceFollowRedirects(true);
        return httpURLConn;
    }

    private HttpClient getHttpClient(Context mContext) throws IOException
    {
        HttpClient httpClient = null;
        // 获取当前可用网络信息
        ConnectivityManager connMng = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInf = connMng.getActiveNetworkInfo();
        // 如果当前是WIFI连接
        if (null != netInf
                && TYPE_WIFI.toLowerCase().equals(
                        netInf.getTypeName().toLowerCase()))
        {
            httpClient = new DefaultHttpClient();
        }
        // 非WIFI联网
        else if (null != netInf
                && TYPE_MOBILE.toLowerCase().equals(
                        netInf.getTypeName().toLowerCase()))
        {
            String proxyHost = android.net.Proxy.getDefaultHost();
            ClsLog.I(TAG, "proxyHost : " + proxyHost);
            ClsLog.toast(mContext, "proxyHost : " + proxyHost);

            // 代理模式
            if (null != proxyHost)
            {
                //获取代理信息  
                String host = android.net.Proxy.getDefaultHost();
                int port = android.net.Proxy.getDefaultPort();
                HttpHost httpHost = new HttpHost(host, port);
                httpClient = new DefaultHttpClient();
                httpClient.getParams().setParameter(
                        ConnRouteParams.DEFAULT_PROXY, httpHost);
                ClsLog.toast(mContext, "非WIFI代理");
            }
            else
            {
                httpClient = new DefaultHttpClient();
            }
        }
        else
        {
            //"未发现可用网络"
            throw new IOException();
        }
        return httpClient;
    }

    /**
     * 方法名称：  cancelPost
     * 作者：            Administrator
     * 方法描述：  取消post连接
     * 输入参数：  
     * 返回类型：  void
     */
    public void cancelPost()
    {
        try
        {
            //                if (null != connection)
            //                {
            //                    connection.disconnect();//断开连接
            //                    connection = null;
            //                }
            cancelHttpClient();
        }
        catch (Exception e)
        {
        }
    }

    private void cancelHttpClient()
    {
        try
        {
            if (null != httpRequest)
            {
                httpRequest.abort();
                httpRequest = null;
            }
        }
        catch (Exception e)
        {
        }
        try
        {

            if (null != httpClient)
            {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        }
        catch (Exception e)
        {
        }
    }
}

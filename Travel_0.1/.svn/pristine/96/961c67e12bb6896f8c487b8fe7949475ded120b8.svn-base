/**
 * 项目名：     Travel
 * 文件名：     BZBInfoXml.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control.bzbinfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import com.mendong.travel.Bean.bzbinfo.BZBInfoBean;
import com.mendong.travel.Bean.bzbinfo.BZBInfoReqBean;
import com.mendong.travel.Bean.bzbinfo.BZBInfoRspBean;
import com.mendong.travel.control.ClsXml;

/**
 * 类名称：     BZBInfoXml
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午2:11:16
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午2:11:16
 *
 */
public final class BZBInfoXml extends ClsXml
{

    private static final String TAG = "BZBInfoXml";

    public String create(Object req)
    {
        if (null == req)
        {
            return null;
        }

        if (!(req instanceof BZBInfoReqBean))
        {
            return null;
        }

        BZBInfoReqBean bean = (BZBInfoReqBean) req;

        StringBuilder sb = new StringBuilder();

        sb.append("<rt><opType>");
        sb.append(getString(bean.getOpType()));
        sb.append("</opType><id>");
        sb.append(getString(bean.getId()));
        sb.append("</id></rt>");

        return sb.toString();
    }

    private List<String> parseBanner(XmlPullParser xmlParser, int eventType)
            throws XmlPullParserException, IOException
    {

        String tagName = null;
        String text = null;
        List<String> banners = new ArrayList<String>();
        while (eventType != XmlPullParser.END_TAG)
        {
            text = null;

            tagName = xmlParser.getName().toLowerCase(Locale.getDefault());
            if ("banner".equals(tagName))
            {
                text = xmlParser.nextText();
                if (null != text && !"".equals(text))
                {
                    banners.add(text);
                }
            }

            eventType = xmlParser.next();
        }
        return banners;
    }

    private String test = "<rs><rt>0</rt><id>1</id><name>牌坊</name><info>街区入口</info><pic><banner>tttt</banner></pic><lat>956.000000</lat><lng>210.000000</lng></rs>";

    public BZBInfoRspBean parse(byte[] data) throws XmlPullParserException,
            IOException
    {
        BZBInfoRspBean result = null;
        if (null != data && data.length > 0)
        {
            try
            {
                String sss = new String(data, "UTF-8");
                Log.e(TAG, sss);
            }
            catch(OutOfMemoryError e)
            {
                data = null;
                System.gc();
                XmlPullParserException e1 = new XmlPullParserException("返回数据构建字符串出错, 造成内存溢出");
                throw e1;
            }
            catch(Exception e)
            {
                XmlPullParserException e1 = new XmlPullParserException("构建字符串出错");
                throw e1;
            }
            
//            data = test.getBytes();
            
            XmlPullParser xmlParser = XmlPullParserFactory.newInstance()
                    .newPullParser();
            ByteArrayInputStream bai = new ByteArrayInputStream(data);
            xmlParser.setInput(bai, "UTF-8");
            int eventType = xmlParser.getEventType();

            String tagName = null;
            BZBInfoBean mBzbInfo = null;
            List<String> banners = null;
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    tagName = xmlParser.getName().toLowerCase(
                            Locale.getDefault());

                    if ("rt".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && "0".equals(text))
                        {
                            result = new BZBInfoRspBean();
                            mBzbInfo = new BZBInfoBean();
                        }
                        result.setRstCode(text);
                    }
                    else if ("id".equals(tagName))
                    {
                        String text = xmlParser.nextText();

                        if (null != text && !"".equals(text))
                        {
                            mBzbInfo.setId(text);
                        }
                    }
                    else if ("name".equals(tagName))
                    {
                        String text = xmlParser.nextText();

                        if (null != text && !"".equals(text))
                        {
                            mBzbInfo.setName(text);
                        }
                    }
                    else if ("info".equals(tagName))
                    {
                        String text = xmlParser.nextText();

                        if (null != text && !"".equals(text))
                        {
                            mBzbInfo.setInfo(text);
                        }
                    }
                    else if ("pic".equals(tagName))
                    {
                        banners = new ArrayList<String>();
                    }
                    else if ("banner".equals(tagName))
                    {
                        List<String> bannerList = parseBanner(xmlParser, eventType);
                        if(null != bannerList && !bannerList.isEmpty())
                        {
                            banners.addAll(bannerList);
                        }
     
                    }
                    else if ("lat".equals(tagName))
                    {
                        String text = xmlParser.nextText();

                        if (null != text && !"".equals(text))
                        {
                            mBzbInfo.setLatitude(Double.valueOf(text));
                        }
                    }
                    else if ("lng".equals(tagName))
                    {
                        String text = xmlParser.nextText();

                        if (null != text && !"".equals(text))
                        {
                            mBzbInfo.setLongitude(Double.valueOf(text));
                        }
                    }
                }

                eventType = xmlParser.next();
            }

            mBzbInfo.setBanners(banners);
            result.setmBzbInfo(mBzbInfo);
        }

        return result;
    }

}

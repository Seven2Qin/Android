/**
 * 项目名：     Travel
 * 文件名：     CheckVersionXml.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control.checkversion;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import com.mendong.travel.Bean.bzbinfo.BZBInfoBean;
import com.mendong.travel.Bean.bzbinfo.BZBInfoReqBean;
import com.mendong.travel.Bean.bzbinfo.BZBInfoRspBean;
import com.mendong.travel.Bean.checkversion.CheckVersionBean;
import com.mendong.travel.Bean.checkversion.CheckVersionReqBean;
import com.mendong.travel.Bean.checkversion.CheckVersionRspBean;
import com.mendong.travel.control.ClsXml;

/**
 * 类名称：     CheckVersionXml
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午10:34:42
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午10:34:42
 *
 */
public final class CheckVersionXml extends ClsXml
{
    
    private static final String TAG = "CheckVersionXml";
    
    @Override
    public String create(Object req)
    {
        if (null == req)
        {
            return null;
        }

        if (!(req instanceof CheckVersionReqBean))
        {
            return null;
        }

        CheckVersionReqBean bean = (CheckVersionReqBean) req;

        StringBuilder sb = new StringBuilder();

        sb.append("<rt><opType>");
        sb.append(getString(bean.getOpType()));
        sb.append("</opType><ctype>");
        sb.append(getString(bean.getCtype()));
        sb.append("</ctype><vid>");
        sb.append(getString(bean.getVid()));
        sb.append("</vid><lat>");
        sb.append(getString(bean.getLat()));
        sb.append("</lat><lng>");
        sb.append(getString(bean.getLng()));
        sb.append("</lng></rt>");

        return sb.toString();
    }

    @Override
    public CheckVersionRspBean parse(byte[] data)
            throws XmlPullParserException, IOException
    {
        String sss = new String(data);
        Log.e(TAG, sss);
        CheckVersionRspBean result = null;
        if (null != data && data.length > 0)
        {
            XmlPullParser xmlParser = XmlPullParserFactory.newInstance()
                    .newPullParser();
            ByteArrayInputStream bai = new ByteArrayInputStream(data);
            xmlParser.setInput(bai, "UTF-8");
            int eventType = xmlParser.getEventType();

            String tagName = null;

            CheckVersionBean mCheckVersionBean = null;

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
                            result = new CheckVersionRspBean();
                            mCheckVersionBean = new CheckVersionBean();
                            result.setRstCode(text);
                        }
                        else
                        {
                            return null;
                        }
                    }
                    else if ("flag".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && !"".equals(text))
                        {
                            mCheckVersionBean.setUpflag(text);
                        }
                    }
                    else if ("url".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && !"".equals(text))
                        {
                            mCheckVersionBean.setUrl(text);
                        }
                    }
                    else if ("lat".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && !"".equals(text))
                        {
                            mCheckVersionBean.setLatitude(Double.valueOf(text));
                        }
                    }
                    else if ("lng".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && !"".equals(text))
                        {
                            mCheckVersionBean
                                    .setLongitude(Double.valueOf(text));
                        }
                    }
                    else if ("rang".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && !"".equals(text))
                        {
                            mCheckVersionBean.setRang(text);
                        }
                    }
                }

                eventType = xmlParser.next();
            }

            result.setmCheckVersionBean(mCheckVersionBean);
        }

        return result;
    }

}

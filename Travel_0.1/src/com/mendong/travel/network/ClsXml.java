/**
 * 项目名：       SystemService
 * 文件名：       ClsXml.java
 * 文件描述： 
 * 作者：            Administrator
 * 创建时间：  2012-6-25
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.mendong.travel.network.entity.ClsReqInfo;
import com.mendong.travel.network.entity.ClsRspInfo;

/**
 * 类名称：       ClsXml
 * 作者：            Administrator
 * 创建时间：  2012-6-25 下午2:12:48
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2012-6-25 下午2:12:48
 *
 */
public final class ClsXml
{

    private String getString(String value)
    {
        if (null == value || "null".equals(value))
        {
            value = "";
        }
        return value;
    }

    /**
     * 方法名称：  parseXml
     * 作者：            Administrator
     * 方法描述：  解析响应的XML
     * 输入参数：  @param in
     * 输入参数：  @return
     * 输入参数：  @throws XmlPullParserException
     * 输入参数：  @throws IOException
     * 返回类型：  ClsRspInfo
     */
    public ClsRspInfo parseXml(byte[] data) throws Exception
    {
        ClsRspInfo result = null;
//                                data = sss.getBytes();
        if (null != data && data.length > 0)
        {
            String s = new String(data);
            System.out.println("------------" + s);
            result = new ClsRspInfo();

            XmlPullParser xmlParser = XmlPullParserFactory.newInstance()
                    .newPullParser();
            ByteArrayInputStream bai = new ByteArrayInputStream(data);
            xmlParser.setInput(bai, "UTF-8");
            int eventType = xmlParser.getEventType();
            String tagName = null;

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    tagName = xmlParser.getName().toLowerCase();
                    
                }
                eventType = xmlParser.next();
            }
        }
        return result;
    }

}

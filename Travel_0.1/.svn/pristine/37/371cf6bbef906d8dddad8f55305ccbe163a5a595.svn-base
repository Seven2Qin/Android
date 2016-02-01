package com.mendong.travel.control.map;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.Bean.bzbinfo.BZBInfoRspBean;
import com.mendong.travel.Bean.map.MapBean;
import com.mendong.travel.Bean.map.MapReqBean;
import com.mendong.travel.Bean.map.MapRspBean;
import com.mendong.travel.control.ClsXml;

public class MapXml extends ClsXml
{

    private static final String TAG = "MapXml";

    @Override
    public String create(Object req)
    {
        if (null == req)
        {
            return null;
        }

        if (!(req instanceof MapReqBean))
        {
            return null;
        }

        MapReqBean bean = (MapReqBean) req;

        StringBuilder sb = new StringBuilder();

        sb.append("<rt><opType>");
        sb.append(getString(bean.getOpType()));
        sb.append("</opType><pixel><x>");
        sb.append(getString(bean.getPointX()));
        sb.append("</x><y>");
        sb.append(bean.getPointY());
        sb.append("</y></pixel></rt>");

        return sb.toString();
    }

    private MapBean parseShop(XmlPullParser xmlParser, int eventType)
            throws XmlPullParserException, IOException
    {
        String tagName = null;
        String text = null;
        MapBean shop = null;
        while (eventType != XmlPullParser.END_TAG)
        {
            text = null;

            tagName = xmlParser.getName().toLowerCase(Locale.getDefault());
            if ("shop".equals(tagName))
            {
                shop = new MapBean();
            }
            else if ("id".equals(tagName))
            {
                text = xmlParser.nextText();
                if (null != text && text.length() > 0)
                {
                    shop.setId(text);
                }
            }
            else if ("name".equals(tagName))
            {
                text = xmlParser.nextText();
                if (null != text && text.length() > 0)
                {
                    shop.setName(text);
                }
            }
            else if ("area".equals(tagName))
            {
                text = xmlParser.nextText();
                if (null != text && text.length() > 0)
                {
                    shop.setArea(text);
                }
            }
            eventType = xmlParser.next();
        }

        return shop;
    }
    
    String test = "<rs>"+
"<rt>0</rt>"+
"<shops>"+
"<shop>"+
"<id>1</id>"+
"<name>牌坊</name>"+
"<area>1600,400,2000,400,2000,600,1600,600</area>"+
"</shop>"+
"</shops>"+
"</rs>";

    @Override
    public MapRspBean parse(byte[] data) throws XmlPullParserException,
            IOException
    {
        MapRspBean result = null;
        if (null != data && data.length > 0)
        {
            String sss = new String(data);
            Log.e(TAG, sss);
            
//            data = test.getBytes();

            XmlPullParser xmlParser = XmlPullParserFactory.newInstance()
                    .newPullParser();
            ByteArrayInputStream bai = new ByteArrayInputStream(data);
            xmlParser.setInput(bai, "UTF-8");
            int eventType = xmlParser.getEventType();

            String tagName = null;
            List<MapBean> shops = null;

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
                            result = new MapRspBean();
                            result.setRstCode(text);
                        }
                        else
                        {
                            return null;
                        }

                    }
                    else if ("shops".equals(tagName))
                    {
                        shops = new ArrayList<MapBean>();
                    }
                    else if ("shop".equals(tagName))
                    {
                        MapBean shop = parseShop(xmlParser, eventType);
                        if(null != shop)
                        {
                        shops.add(shop);
                        }
                    }
                }

                eventType = xmlParser.next();
            }
            
            result.setPoi(shops);
        }

        return result;
    }

}

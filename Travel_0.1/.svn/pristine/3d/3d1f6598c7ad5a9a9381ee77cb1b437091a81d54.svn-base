/**
 * 项目名：     Travel
 * 文件名：     BZBXml.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月20日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control.bzb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.mendong.travel.Bean.bzb.BZBBean;
import com.mendong.travel.Bean.bzb.BZBReqBean;
import com.mendong.travel.Bean.bzb.BZBRspBean;
import com.mendong.travel.control.ClsXml;
import com.mendong.travel.utils.ClsSysUtils;

/**
 * 类名称：     BZBXml
 * 作者：         Administrator
 * 创建时间：  2013年7月20日 下午10:45:11
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月20日 下午10:45:11
 *
 */
public final class BZBXml extends ClsXml
{

    @Override
    public String create(Object req)
    {
        if (null == req)
        {
            return null;
        }

        BZBReqBean bean = (BZBReqBean) req;

        StringBuilder sb = new StringBuilder();

        sb.append("<rt><opType>");
        sb.append(getString(bean.getOpType()));
        sb.append("</opType><id>");
        sb.append(getString(bean.getId()));
        sb.append("</id><page>");
        sb.append(bean.getPage());
        sb.append("</page><size>");
        sb.append(bean.getSize());
        sb.append("</size></rt>");

        return sb.toString();
    }

    private BZBBean parseShop(XmlPullParser xmlParser, int eventType)
            throws XmlPullParserException, IOException
    {
        String tagName = null;
        String text = null;
        BZBBean shop = null;
        while (eventType != XmlPullParser.END_TAG)
        {
            text = null;

            tagName = xmlParser.getName().toLowerCase(Locale.getDefault());
            if ("shop".equals(tagName))
            {
                shop = new BZBBean();
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
            else if ("logo".equals(tagName))
            {
                text = xmlParser.nextText();
                if (null != text && text.length() > 0)
                {
                    shop.setLogoUrl(text);
                }
            }
            eventType = xmlParser.next();
        }

        return shop;
    }

//    private String test = "<rs>" + "<rt>0</rt>" + "<total>13</total>"
//            + "<shops>" + "<shop>" + "<id>10</id>" + "<name>德云社</name>"
//            + "<logo></logo>" + "</shop>" + "<shop>" + "<id>11</id>"
//            + "<name>星巴克</name><logo>sb_logo.png</logo>" + "</shop>" + "<shop>"
//            + "<id>23</id>" + "<name>OJ</name>" + "<logo></logo>" + "</shop>"
//            + "<shop>" + "<id>24</id>" + "<name>白鹭洲啤酒吧</name><logo></logo>"
//            + "</shop>" + "<shop>" + "<id>25</id>"
//            + "<name>博纳</name><logo></logo>" + "</shop>" + "<shop>"
//            + "<id>26</id>" + "<name>布兰迪</name><logo>" + "</logo>" + "</shop>"
//            + "<shop>" + "<id>27</id>" + "<name>韩复兴</name><logo>" + "</logo>"
//            + "</shop>" + "<shop>" + "<id>28</id>" + "<name>金唐年画</name><logo>"
//            + "</logo>" + "</shop>" + "<shop>" + "<id>29</id>"
//            + "<name>上上禅品</name>" + "<logo>" + "</logo>" + "</shop>" + "<shop>"
//            + "<id>30</id>" + "<name>石唯玉</name>" + "<logo>" + "</logo>"
//            + "</shop>" + "</shops>" + "</rs>";
    
//    private String test = "<rs><rt>0</rt><id>1</id><name>牌坊</name><info>街区入口</info><pic><banner>tttt</banner></pic><lat>956.000000</lat><lng>210.000000</lng></rs>";


    @Override
    public synchronized BZBRspBean parse(byte[] data)
            throws XmlPullParserException, IOException
    {
        BZBRspBean result = null;

//        data = test.getBytes();
        String str = new String(data);
        System.out.println(str);

        if (null != data && data.length > 0)
        {

            XmlPullParser xmlParser = XmlPullParserFactory.newInstance()
                    .newPullParser();
            ByteArrayInputStream bai = new ByteArrayInputStream(data);
            xmlParser.setInput(bai, "UTF-8");
            int eventType = xmlParser.getEventType();
            
            String tagName = null;
            List<BZBBean> shops = null;

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    tagName = xmlParser.getName().toLowerCase(
                            Locale.getDefault());

                    if ("rt".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && !"".equals(text)
                                && "0".equals(text))
                        {
                            result = new BZBRspBean();
                        }
                        result.setRstCode(text);
                    }
                    else if ("total".equals(tagName))
                    {
                        String text = xmlParser.nextText();
                        if (null != text && !"".equals(text))
                        {
                            int t = ClsSysUtils.str2Int(text);
                            result.setTotal(t);
                        }
                    }
                    else if ("shops".equals(tagName))
                    {
                        shops = new ArrayList<BZBBean>();

                    }
                    else if ("shop".equals(tagName))
                    {
                        BZBBean shop = parseShop(xmlParser, eventType);

                        if (null != shop)
                        {
                            shops.add(shop);
                        }
                    }
                }
                eventType = xmlParser.next();
            }

            result.setShops(shops);
        }
        return result;

    }

//    String sss = "<items>"
//            + "<item id=\"1\" name=\"牌坊\" info=\"街区入口\" banner=\"\"></item>"
//            + "<item id=\"2\" name=\"箍桶巷A\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"3\" name=\"箍桶巷B\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"4\" name=\"箍桶巷C\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"5\" name=\"箍桶巷D\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"6\" name=\"箍桶巷E\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"7\" name=\"箍桶巷F\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"8\" name=\"箍桶巷G\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"9\" name=\"箍桶巷H\" info=\"主街路标\" banner=\"\"></item>"
//            + "<item id=\"10\" name=\"德云社\" info=\"德云社是中国的一个相声社团，全称北京德云社文化传播有限公司，成立于1995年，以“让相声回归剧场”，做“真正的相声”为旗帜。2011年7月3日，德云社在北展剧场开辟新形式，用话剧和相声混搭方式，为观众演绎从清朝、民国到新中国成立后各种风格的相声。\" banner=\"dys.jpg\"></item>"
//            + "<item id=\"11\" name=\"星巴克\" info=\"星巴克（Starbucks）是美国一家连锁咖啡公司的名称，1971年成立，为全球最大的咖啡连锁店，其总部坐落美国华盛顿州西雅图市。除咖啡外，星巴克亦有茶、馅皮饼及蛋糕等商品。星巴克在全球范围内已经有近12,000间分店遍布北美、南美洲、欧洲、中东及太平洋区。 \" banner=\"starbucks.png\"></item>"
//            + "<item id=\"12\" name=\"全球生活馆\" info=\"生活馆是生活方式的一种体验。生活馆有两类，一类是提供某种功能性服务端场所，如：养生、保健、美容等；另外一种是体验式营销场所，如家居生活馆、布艺生活馆、完全实景布置，消费者可以身临其境的感受，来决定是否购买等。\" banner=\"\"></item>"
//            + "<item id=\"13\" name=\"三条营A\" info=\"三条营路标\" banner=\"\"></item>"
//            + "<item id=\"14\" name=\"三条营B\" info=\"三条营路标\" banner=\"\"></item>"
//            + "<item id=\"15\" name=\"三条营C\" info=\"三条营路标\" banner=\"\"></item>"
//            + "<item id=\"16\" name=\"三条营D\" info=\"三条营路标\" banner=\"\"></item>"
//            + "<item id=\"17\" name=\"三条营E\" info=\"三条营路标\" banner=\"\"></item>"
//            + "<item id=\"18\" name=\"陶家巷A\" info=\"陶家巷路标\" banner=\"\"></item>"
//            + "<item id=\"19\" name=\"陶家巷B\" info=\"陶家巷路标\" banner=\"\"></item>"
//            + "<item id=\"20\" name=\"陶家巷C\" info=\"陶家巷路标\" banner=\"\"></item>"
//            + "<item id=\"21\" name=\"公司\" info=\"三条营路标\" banner=\"\"></item>"
//            + "</items>";

    //    public synchronized BZBRspBean parse(byte[] data)
    //            throws XmlPullParserException, IOException
    //    {
    //        BZBRspBean result = null;
    //
    //        String str = new String(data);
    //        System.out.println(str);
    //        
    //        data = sss.getBytes();
    //
    //        if (null != data && data.length > 0)
    //        {
    //            XmlPullParser xmlParser = XmlPullParserFactory.newInstance()
    //                    .newPullParser();
    //            ByteArrayInputStream bai = new ByteArrayInputStream(data);
    //            xmlParser.setInput(bai, "UTF-8");
    //            int eventType = xmlParser.getEventType();
    //            String tagName = null;
    //
    //            List<BZBBean> shops = null;
    //
    //            while (eventType != XmlPullParser.END_DOCUMENT)
    //            {
    //                if (eventType == XmlPullParser.START_TAG)
    //                {
    //                    tagName = xmlParser.getName().toLowerCase(
    //                            Locale.getDefault());
    //
    //                    if ("items".equals(tagName))
    //                    {
    //                        result = new BZBRspBean();
    //                    }
    //                    else if ("item".equals(tagName))
    //                    {
    //                        
    //                        BZBBean bean = new BZBBean();
    //                        String text = xmlParser.getAttributeValue(null, "id");
    //
    //                        if (null != text && !"".equals(text))
    //                        {
    //                            bean.setId(text);
    //                        }
    //                        
    //                        text = xmlParser.getAttributeValue(null, "name");
    //
    //                        if (null != text && !"".equals(text))
    //                        {
    //                            bean.setName(text);
    //                        }
    //                        
    //                        text = xmlParser.getAttributeValue(null, "info");
    //
    //                        if (null != text && !"".equals(text))
    //                        {
    //                            bean.setInfo(text);
    //                        }
    //                        
    //                        text = xmlParser.getAttributeValue(null, "banner");
    //
    //                        if (null != text && !"".equals(text))
    //                        {
    //                            bean.setLogoUrl(text);
    //                        }
    //                        
    //                        if(shops == null)
    //                        {
    //                            shops = new ArrayList<BZBBean>();
    //                        }
    //                        
    //                        shops.add(bean);
    //                        result.setTotal(shops.size());
    //                        result.setShops(shops);
    //                        xmlParser.next();
    //                    }
    //                }
    //                eventType = xmlParser.next();
    //            }
    //        }
    //        return result;
    //
    //    }

    public BZBBean parse(InputStream inputStream)
            throws XmlPullParserException, IOException
    {
        return null;

    }

}

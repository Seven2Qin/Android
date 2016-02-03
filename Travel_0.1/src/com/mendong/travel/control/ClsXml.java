/**
 * 项目名：     Travel
 * 文件名：     ClsXml.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.control;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

/**
 * 类名称：     ClsXml
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 上午11:22:37
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 上午11:22:37
 *
 */
public abstract class ClsXml
{
    public String getString(String value)
    {
        if (null == value || "null".equals(value))
        {
            value = "";
        }
        return value;
    }
    
    public abstract String create(Object req);
    
    public abstract Object parse(byte[] data)throws XmlPullParserException, IOException;
}

/**
 * 项目名：     Travel
 * 文件名：     BZBBean.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月20日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.Bean.bzb;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类名称：     BZBBean
 * 作者：         Administrator
 * 创建时间：  2013年7月20日 下午2:26:25
 * 类描述：     储存商家的基本信息,用于数据的读取
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月20日 下午2:26:25
 *
 */
public final class BZBBean implements Parcelable
{

    /**
     * 位置序列节点
     */
    private String shop;

    /**
     * 商家ID
     */
    private String id;

    /**
     * 商家名称
     */
    private String name;
    
    /**
     * 商家信息
     */
    private String info;

    /**
     * 商家图片URL
     */
    private String logoUrl;

    /**
     * 方法名称：  getShop
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  shop
     * 备注：
     */
    public String getShop()
    {
        return shop;
    }

    /**
     * 方法名称： setShop
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 shop 给 shop
     * 备注：
     */
    public void setShop(String shop)
    {
        this.shop = shop;
    }

    /**
     * 方法名称：  getId
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  id
     * 备注：
     */
    public String getId()
    {
        return id;
    }

    /**
     * 方法名称： setId
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 id 给 id
     * 备注：
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * 方法名称：  getName
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  name
     * 备注：
     */
    public String getName()
    {
        return name;
    }

    /**
     * 方法名称： setName
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 name 给 name
     * 备注：
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 方法名称：  getInfo
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  info
     * 备注：
     */
    public String getInfo()
    {
        return info;
    }

    /**
     * 方法名称： setInfo
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 info 给 info
     * 备注：
     */
    public void setInfo(String info)
    {
        this.info = info;
    }

    /**
     * 方法名称：  getLogoUrl
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  logoUrl
     * 备注：
     */
    public String getLogoUrl()
    {
        return logoUrl;
    }

    /**
     * 方法名称： setLogoUrl
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 logoUrl 给 logoUrl
     * 备注：
     */
    public void setLogoUrl(String logoUrl)
    {
        this.logoUrl = logoUrl;
    }

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * (覆盖方法)
     * 方法名称：  toString
     * 作者：            Administrator
     * 方法描述：    
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        if ((null != id))
        {
            sb.append("id=").append(id).append(',');
        }
        if ((null != name))
        {
            sb.append("name=").append(name).append(',');
        }
        if ((null != logoUrl))
        {
            sb.append("logoUrl=").append(logoUrl).append(',');
        }

        return sb.toString().substring(0, sb.toString().length());
    }
}

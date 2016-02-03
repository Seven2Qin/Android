/**
 * 项目名：     Travel
 * 文件名：     BZBInfoBean.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.Bean.bzbinfo;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类名称：     BZBInfoBean
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午2:12:45
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午2:12:45
 *
 */
public final class BZBInfoBean implements Parcelable
{
    /**
     * 商家编号
     */
    private String id;
    
    /**
     * 商家名称
     */
    private String name;
    
    /**
     * 商家简介
     */
    private String info;
    
    /**
     * 商家照片列表
     */
    private List<String> banners;
    
    /**
     * 纬度
     */
    private double latitude = 0.0;

    /**
     * 经度
     */
    private double longitude = 0.0;
    
    
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
     * 方法名称：  getBanners
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  List<String>
     * 返回字段：  photoUrls
     * 备注：
     */
    public List<String> getBanners()
    {
        return banners;
    }

    /**
     * 方法名称： setBanners
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  List<String>
     * 设置字段：  设置 photoUrls 给 photoUrls
     * 备注：
     */
    public void setBanners(List<String> banners)
    {
        this.banners = banners;
    }

    /**
     * 方法名称：  getLatitude
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  double
     * 返回字段：  latitude
     * 备注：
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * 方法名称： setLatitude
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  double
     * 设置字段：  设置 latitude 给 latitude
     * 备注：
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * 方法名称：  getLongitude
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  double
     * 返回字段：  longitude
     * 备注：
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * 方法名称： setLongitude
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  double
     * 设置字段：  设置 longitude 给 longitude
     * 备注：
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
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

}

/**
 * 项目名：     Travel
 * 文件名：     CheckVersionBean.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.Bean.checkversion;

/**
 * 类名称：     CheckVersionBean
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午10:35:49
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午10:35:49
 *
 */
public final class CheckVersionBean
{
    /**
     * 升级标志
     * 0：不升级
     * 1：推荐升级
     * 2：强制升级
     */
    private String upflag;

    /**
     * 如果是强制升级和推荐升级，需要带升级下载文件的URL
     */
    private String url;

    /**
     * 纬度
     */
    private double latitude = 0.0;

    /**
     * 经度
     */
    private double longitude = 0.0;

    /**
     * 是否进入园区
     */
    private String rang;

    /**
     * 方法名称：  getUpflag
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  upflag
     * 备注：
     */
    public String getUpflag()
    {
        return upflag;
    }

    /**
     * 方法名称： setUpflag
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 upflag 给 upflag
     * 备注：
     */
    public void setUpflag(String upflag)
    {
        this.upflag = upflag;
    }

    /**
     * 方法名称：  getUrl
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  url
     * 备注：
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * 方法名称： setUrl
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 url 给 url
     * 备注：
     */
    public void setUrl(String url)
    {
        this.url = url;
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

    /**
     * 方法名称：  getRang
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  rang
     * 备注：
     */
    public String getRang()
    {
        return rang;
    }

    /**
     * 方法名称： setRang
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 rang 给 rang
     * 备注：
     */
    public void setRang(String rang)
    {
        this.rang = rang;
    }




}

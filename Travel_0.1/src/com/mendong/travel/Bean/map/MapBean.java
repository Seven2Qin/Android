/**
 * 项目名：     Travel
 * 文件名：     MapBean.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.Bean.map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类名称：     MapBean
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午12:43:23
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午12:43:23
 *
 */
public final class MapBean implements Parcelable
{

    /**
     * 商家ID
     */
    private String id;
    
    /**
     * 商家名称
     */
    private String name;
    
    /**
     * 范围
     */
    private String area;
    
    
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
     * 方法名称：  getArea
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  area
     * 备注：
     */
    public String getArea()
    {
        return area;
    }

    /**
     * 方法名称： setArea
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 area 给 area
     * 备注：
     */
    public void setArea(String area)
    {
        this.area = area;
    }

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1)
    {
        // TODO Auto-generated method stub
        
    }

}

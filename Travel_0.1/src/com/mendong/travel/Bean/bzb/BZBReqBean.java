/**
 * 项目名：     Travel
 * 文件名：     BZBReqBean.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月21日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.Bean.bzb;

/**
 * 类名称：     BZBReqBean
 * 作者：         Administrator
 * 创建时间：  2013年7月21日 上午8:44:08
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月21日 上午8:44:08
 *
 */
public final class BZBReqBean
{
    private String opType;
    
    private String id;
    
    private int page;
    
    private int size;

    /**
     * 方法名称：  getOpType
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  opType
     * 备注：
     */
    public String getOpType()
    {
        return opType;
    }

    /**
     * 方法名称： setOpType
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 opType 给 opType
     * 备注：
     */
    public void setOpType(String opType)
    {
        this.opType = opType;
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
     * 方法名称：  getPage
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  page
     * 备注：
     */
    public int getPage()
    {
        return page;
    }

    /**
     * 方法名称： setPage
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 page 给 page
     * 备注：
     */
    public void setPage(int page)
    {
        this.page = page;
    }

    /**
     * 方法名称：  getSize
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  int
     * 返回字段：  size
     * 备注：
     */
    public int getSize()
    {
        return size;
    }

    /**
     * 方法名称： setSize
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  int
     * 设置字段：  设置 size 给 size
     * 备注：
     */
    public void setSize(int size)
    {
        this.size = size;
    }
    
    
}

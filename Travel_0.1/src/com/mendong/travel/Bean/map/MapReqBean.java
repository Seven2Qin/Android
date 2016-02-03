/**
 * 项目名：     Travel
 * 文件名：     MapReqBean.java
 * 文件描述： 
 * 作者：         Administrator
 * 创建时间：  2013年7月22日
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 *
 */
package com.mendong.travel.Bean.map;

/**
 * 类名称：     MapReqBean
 * 作者：         Administrator
 * 创建时间：  2013年7月22日 下午12:44:05
 * 类描述：    
 * 版权声明 ： Copyright (C) 2008-2010 RichPeak
 * 修改时间：  2013年7月22日 下午12:44:05
 *
 */
public final class MapReqBean
{
    /**
     * 请求类型
     */
    private String opType;

    /**
     * 屏幕转化为地图坐标的X
     */
    private String pointX;

    /**
     * 屏幕转化为地图坐标的Y
     */
    private String pointY;

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
     * 方法名称：  getPointX
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  pointX
     * 备注：
     */
    public String getPointX()
    {
        return pointX;
    }

    /**
     * 方法名称： setPointX
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 pointX 给 pointX
     * 备注：
     */
    public void setPointX(String pointX)
    {
        this.pointX = pointX;
    }

    /**
     * 方法名称：  getPointY
     * 作者：          Administrator
     * 方法描述：  
     * 字段类型：  String
     * 返回字段：  pointY
     * 备注：
     */
    public String getPointY()
    {
        return pointY;
    }

    /**
     * 方法名称： setPointY
     * 作者：         Administrator
     * 方法描述：  
     * 字段类型：  String
     * 设置字段：  设置 pointY 给 pointY
     * 备注：
     */
    public void setPointY(String pointY)
    {
        this.pointY = pointY;
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

        if ((null != opType))
        {
            sb.append("opType=").append(opType).append(',');
        }
        if ((null != pointX))
        {
            sb.append("pointX=").append(pointX).append(',');
        }
        if ((null != pointY))
        {
            sb.append("pointY=").append(pointY).append(',');
        }

        return sb.toString().substring(0, sb.toString().length());
    }
}

package com.mendong.travel.requestdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;





import android.util.Log;

public class DataXml {

	private static final int BUFFER_SIZE = 1000;
	private final static String URL = "http://www.xiaooo.cn/mobile/client/mdFunc.php";
	private final static int PAGE =1;
	private final static int SIZE =20;
	
	
	public String createRoutePositionIdXml(int startId,int endId)
	{
	   final String opType ="showRoute";    

	   StringBuilder sb = new StringBuilder();

	   sb.append("<rt><opType>");
	   sb.append(opType);
	   sb.append("</opType><src>");
	   sb.append(startId);
	   sb.append("</src><dest>");
	   sb.append(endId);
	   sb.append("</dest></rt>");
	        
	   return sb.toString();
	}
	/**
	 * 封装请求数据标志id
	 */
	public String goodsXml(int itemId)
	{
	   final String opType ="showItem";    

	   StringBuilder sb = new StringBuilder();

	   sb.append("<rt><opType>");
	   sb.append(opType);
	   sb.append("</opType><item_id>");
	   sb.append(itemId);
	   sb.append("</item_id></rt>");
	        
	   return sb.toString();
	}
	
	/**
	 * 商家接口
	 * @param locId:22
	 * @return
	 */
	public String shopXml(int locId)
	{
		final String opType ="showItemList";    
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<rt><opType>");
		sb.append(opType);
		sb.append("</opType><loc_id>");
		sb.append(locId);
		sb.append("</loc_id><page>");
		sb.append(PAGE);
		sb.append("</page><size>");
		sb.append(SIZE);
		sb.append("</size></rt>");
		
		return sb.toString();
	}
	
	/**
	  * 从服务器上获取数据流
	  * @param Url
	  * @param opType
	  * @param spots
	  * @return
	  * @throws IllegalStateException
	  * @throws Exception
	  */
	public InputStream getDataStream(String strXML) throws Exception
	{
		byte[]data = new byte[BUFFER_SIZE];
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		try 
		{
		    StringEntity entity = new StringEntity(strXML);
		    post.setEntity(entity);
	    	HttpResponse httpResponse = client.execute(post);
	    	int ret = httpResponse.getStatusLine().getStatusCode();
	    	
	    	if(ret == HttpStatus.SC_OK)
	    	{
	    		//输出--打印
	    		//Log.i("sysout",InputStream2String(httpResponse.getEntity().getContent()));
	    		
	    		return httpResponse.getEntity().getContent();
	    	}
	    	else
	    	{
	    		//Toast.makeText(context, "数据上传失败！", Toast.LENGTH_SHORT).show();
	    	}
		} 
		catch (IOException e) {e.printStackTrace();}
		
		return null;
	}
	
	
	 /**
	  * 从服务器上获取数据流
	  * @param Url
	  * @param opType
	  * @param spots
	  * @return
	  * @throws IllegalStateException
	  * @throws Exception
	  */
	public InputStream getRoutePositionDataStream(int startId,int endId) throws Exception
	{
		byte[]data = new byte[BUFFER_SIZE];
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		try 
		{
		    StringEntity entity = new StringEntity(createRoutePositionIdXml(startId,endId));
		    post.setEntity(entity);
	    	HttpResponse httpResponse = client.execute(post);
	    	int ret = httpResponse.getStatusLine().getStatusCode();
	    	
	    	if(ret == HttpStatus.SC_OK)
	    	{
	    		/*httpResponse.getEntity().getContent().read(data);
	    		Log.i("sysout", new String(data));*/
	    		return httpResponse.getEntity().getContent();
	    	}
	    	else
	    	{
	    		//Toast.makeText(context, "数据上传失败！", Toast.LENGTH_SHORT).show();
	    	}
		} 
		catch (IOException e) {e.printStackTrace();}
		
		return null;
	}
	

	
	
	 public List<PointLatLng> PointPullParseXML(InputStream inputStream){
	        
	        List<PointLatLng> list=null;
	        PointLatLng point = null;
	        
	        //构建XmlPullParserFactory
	        try {
	            XmlPullParserFactory pullParserFactory=XmlPullParserFactory.newInstance();
	            //获取XmlPullParser的实例
	            XmlPullParser xmlPullParser=pullParserFactory.newPullParser();
	            //设置输入流 
	            xmlPullParser.setInput(inputStream,"UTF-8");
	            //开始
	            int eventType=xmlPullParser.getEventType();
	            
	            try {
	                while(eventType!=XmlPullParser.END_DOCUMENT){
	                    String nodeName=xmlPullParser.getName();
	                    switch (eventType) {
	                    //文档开始
	                    case XmlPullParser.START_DOCUMENT:
	                        list=new ArrayList<PointLatLng>();
	                        break;
	                    //开始节点
	                    case XmlPullParser.START_TAG:
	                    	if("rt".equals(nodeName)){
	                    		if(Integer.parseInt(xmlPullParser.nextText())!=0)
	                    		{
	                    			Log.i("sysout","没有合适路线!");
	                    			return null;
	                    		}
	                    	}else if("dest".equals(nodeName)||"loc".equals(nodeName)||"src".equals(nodeName)){
	                            //实例化对象
	                        	point=new PointLatLng();
	                        }
	                    	else if("lat".equals(nodeName)){
	                            //实例化对象
	                        	point.setLat(Float.parseFloat(xmlPullParser.nextText()));
	                        }else if("lng".equals(nodeName)){
	                            //设置name
	                        	point.setLng(Float.parseFloat(xmlPullParser.nextText()));
	                        }
	                        break;
	                    //结束节点
	                    case XmlPullParser.END_TAG:
	                        if("dest".equals(nodeName)){
	                            list.add(point);
	                            point=null;
	                        }else if("loc".equals(nodeName)){
	                        	list.add(point);
	                        	point=null;
	                        }else if("src".equals(nodeName)){
	                    	list.add(point);
	                    	point=null;
	                        }
	                        break;
	                    default:
	                        break;
	                    }
	                    eventType=xmlPullParser.next();
	                }
	            } catch (NumberFormatException e) {
	            	Log.i("sysout","异常1");
	                e.printStackTrace();
	            } catch (IOException e) {
	            	Log.i("sysout","异常2");
	                e.printStackTrace();
	            }
	        } catch (Exception e) {
	        	Log.i("sysout","异常3");
	            e.printStackTrace();
	        }

	        return list;
	    }
	
	
	
	
	
    public Goods GoodsPullParseXML(InputStream inputStream)
    {
      
      Goods goods = null;
      //构建XmlPullParserFactory
      try {
          XmlPullParserFactory pullParserFactory=XmlPullParserFactory.newInstance();
          //获取XmlPullParser的实例
          XmlPullParser xmlPullParser=pullParserFactory.newPullParser();
          //设置输入流 
          xmlPullParser.setInput(inputStream,"UTF-8");
          //开始
          int eventType=xmlPullParser.getEventType();
          List<String> bannerList =null;
          try {
              while(eventType!=XmlPullParser.END_DOCUMENT){
                  String nodeName=xmlPullParser.getName();
                  switch (eventType) {
                  //文档开始
                  case XmlPullParser.START_DOCUMENT:
                  	
                      break;
                  //开始节点
                  case XmlPullParser.START_TAG:
                  	if("rt".equals(nodeName))
                  	{
                  		if(Integer.parseInt(xmlPullParser.nextText())!=0)
                  		{
                  			return null;
                  		}
                  	}
                  	else if("name".equals(nodeName)){
                          //实例化对象
                  		goods=new Goods();
                  		goods.setName(xmlPullParser.nextText());
                      }
                  	else if("info".equals(nodeName))
                  	{
                  		goods.setInfo(xmlPullParser.nextText());
                      }
                  	else if("pic".equals(nodeName))
                  	{
                  		//bannerList = new ArrayList<String>();
                  	}
                  	else if("banner".equals(nodeName))
                  	{
                  		/*List<String> bannerList = parseBanner(xmlPullParser, eventType);
                          if(null != bannerList && !bannerList.isEmpty())
                          {
                          	 goods.setPicNameList(bannerList);
                          }*/
                  		goods.setPicNameList(xmlPullParser.nextText());
                  		
                  	}
                  	else if("price".equals(nodeName))
                  	{
                  		goods.setPrice(Float.parseFloat(xmlPullParser.nextText()));
                  	}
                  	else if("param".equals(nodeName)){
                          
                  		goods.setParam(xmlPullParser.nextText());
                      }
                  	else if("addr".equals(nodeName)){
                  		
                  		goods.setAddress(xmlPullParser.nextText());
                  	}
                      break;
                  //结束节点
                  case XmlPullParser.END_TAG:
                      break;
                  default:
                      break;
                  }
                  eventType=xmlPullParser.next();
              }
          } catch (NumberFormatException e) {
          	Log.i("sysout","异常1");
              e.printStackTrace();
          } catch (IOException e) {
          	Log.i("sysout","异常2");
              e.printStackTrace();
          }
      } catch (Exception e) {
      	Log.i("sysout","异常3");
          e.printStackTrace();
      }
      
      return goods;
  }
    
   
    public List<ShopItem> ShopPullParseXML(InputStream inputStream) throws XmlPullParserException, IOException
    {
    	XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
    	XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
    	xmlPullParser.setInput(inputStream, "UTF-8");
    	int eventType = xmlPullParser.getEventType();
    	List<ShopItem> shopItemList =null;
    	ShopItem shopItem =null;
    	while(eventType!=xmlPullParser.END_DOCUMENT)
    	{
    		 String nodeName=xmlPullParser.getName();
    		 switch(eventType)
    		 {
    		 case XmlPullParser.START_DOCUMENT:
    			 break;
    		 case XmlPullParser.START_TAG:
    			  if("rt".equals(nodeName))
    			  {
    				  if(Integer.parseInt(xmlPullParser.nextText())!=0)
    				  {
    					  return null;
    				  }
    			  }
    			  else if("items".equals(nodeName))
    			  {
    				  shopItemList = new ArrayList<ShopItem>();
    			  }
    			  else if("item".equals(nodeName))
    			  {
    				  shopItem = new ShopItem();
    			  }
    			  else if("id".equals(nodeName))
    			  {
    				  shopItem.setId(Integer.parseInt(xmlPullParser.nextText()));
    			  }
    			  else if("name".equals(nodeName))
    			  {
    				  shopItem.setName(xmlPullParser.nextText());
    			  }
    			  else if("banner".equals(nodeName))
    			  {
    				  shopItem.setPicName(xmlPullParser.nextText());
    			  }
    			  else if("price".equals(nodeName))
    			  {
    				  shopItem.setPrice(Float.parseFloat(xmlPullParser.nextText()));
    			  }
    			  break;
    		 case XmlPullParser.END_TAG:
    			  if("item".equals(nodeName))
    			  {
    				  shopItemList.add(shopItem);
    				  shopItem = null;
    			  }
    			 break;
    		 default:
                 break;
    		 }
    		 eventType = xmlPullParser.next();
    	}
    	
    	return shopItemList;
    }
	
	
	
	private List<String> parseBanner(XmlPullParser xmlParser, int eventType)
          throws XmlPullParserException, IOException
  {

      String tagName = null;
      String text = null;
      List<String> banners = new ArrayList<String>();
      while (eventType != XmlPullParser.END_TAG)
      {
          text = null;

          tagName = xmlParser.getName().toLowerCase(Locale.getDefault());
          if ("banner".equals(tagName))
          {
              text = xmlParser.nextText();
              if (null != text && !"".equals(text))
              {
                  banners.add(text);
              }
          }

          eventType = xmlParser.next();
      }
      
    //  Log.i("sysout","banners:"+banners.size()+","+banners.get(0));
      return banners;
  }
	
	
	 public List getDataList(int startId,int endId) throws Exception
	 {
		return PointPullParseXML(getRoutePositionDataStream(startId,endId));
	 }
	
	private String InputStream2String(InputStream inputStream) throws IOException
	{
		byte[]data = new byte[4096];
		int n=0;
		StringBuilder stringBuilder = new StringBuilder();
		while((n= inputStream.read(data) )!=-1)
		{
			stringBuilder.append(new String(data,0,n));
		}
		return stringBuilder.toString();
	}

}

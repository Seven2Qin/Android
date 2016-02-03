package com.mendong.travel.wifi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DealData {

	private static final int BUFFER_SIZE = 4096;
	private int niNumOrder=0;
	private final static String URL = "http://www.xiaooo.cn/mobile/client/mdFunc.php";
	private Position position;
	public DealData()
	{
		position= new Position();
	}
	/**
	 * 将收集的wifi信号数据进行xml格式包装，放表上传到服务器上
	 * @param opType
	 * @param listSpot
	 * @return
	 */
	 public String createXml(List<Spot> listSpot)
	    {
		    
	        if (null == listSpot)
	        {
	            return null;
	        }
	        StringBuilder sb = new StringBuilder();

	        sb.append("<rt><opType>");
	        sb.append("findLoc");
	        sb.append("</opType><total>");
	        sb.append(listSpot.size());
	        sb.append("</total><spots>");
	        
	        for(Spot spot : listSpot) 
	        {
	        	sb.append("<spot><ssid>");
	        	sb.append(spot.getName());
	        	sb.append("</ssid><mac>");
	        	sb.append(spot.getMac());
	        	sb.append("</mac><level>");
	        	sb.append(spot.getLevel());
	        	sb.append("</level></spot>");
			}
	        sb.append("</spots></rt>");
	        

	        return sb.toString();
	    }
	 /**
	  * 将方法getXmlData 和  getDataStream合二为一方便调用
	  * @param Url
	  * @param opType
	  * @param spots
	  * @throws IllegalStateException
	  * @throws Exception
	  */
	 public void getResponse(List<Spot> spots) throws IllegalStateException, Exception
	 {
		 getXmlData(getDataStream(spots));
	 }
	 
	 /**
	  * 对从服务器上获取的数据进行xml解析
	  * @param inputStream
	  * @throws Exception
	  */
	 public void getXmlData(InputStream inputStream) throws Exception 
		{  
			
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xmlPullParserFactory.newPullParser();
			xpp.setInput(inputStream,"UTF-8");
			int eventType = xpp.getEventType();
			//初始化数据
			niNumOrder =  0 ;
			while(eventType != XmlPullParser.END_DOCUMENT)
			{
				if(eventType == XmlPullParser.START_DOCUMENT)
				{	/*Log.i("sysout", "START_DOCUMENT:"+eventType);*/}
				else if(eventType == XmlPullParser.START_TAG)
				{   /*Log.i("sysout", "START_TAG:"+xpp.getName());*/}
				else if(eventType == XmlPullParser.END_TAG)
				{	/*Log.i("sysout", "END_TAG:"+xpp.getName());*/}
				else if(eventType == XmlPullParser.TEXT)
				{
					if(niNumOrder==1)
					   position.setId(Integer.parseInt(xpp.getText()));
					else if(niNumOrder ==2)
					   position.setLat(Float.parseFloat(xpp.getText()));
					else if(niNumOrder ==3)
					   position.setLng(Float.parseFloat(xpp.getText()));
					niNumOrder++;	
				}
				
				//获得下一个解析事件
				eventType = xpp.next();
			}
			
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
	public InputStream getDataStream(List<Spot> spots) throws IllegalStateException, Exception
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		try 
		{
		    StringEntity entity = new StringEntity(createXml(spots));
		    post.setEntity(entity);
	    	HttpResponse httpResponse = client.execute(post);
	    	int ret = httpResponse.getStatusLine().getStatusCode();
	    	if(ret == HttpStatus.SC_OK)
	    	{
	    		return httpResponse.getEntity().getContent();
	    	}
	    	else
	    	{
	    		//Toast.makeText(context, "数据上传失败！", Toast.LENGTH_SHORT).show();
	    	}
		} 
		catch (UnsupportedEncodingException e) {e.printStackTrace();} 
		catch (ClientProtocolException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		
		return null;
	}
	
	/**
	 * 返回位置对象(包含经纬度)
	 * @return
	 */
	public Position getPosition()
	{
		return position;
	}

}

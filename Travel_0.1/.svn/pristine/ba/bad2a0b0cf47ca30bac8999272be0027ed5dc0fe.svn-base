package org.jump.utils.down.upload;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

import org.jump.utils.FileUtil;
import org.jump.utils.down.ProgressCallback;

import android.os.Handler;
import android.os.Message;



public class PostPool extends Handler
{

	public static final String TAG = "PostPool";

	private static final int POOL_SIZE = 2;

	private ArrayList<Sender> threads = new ArrayList<Sender>();

	private Stack<UploadParam> reqQueue = new Stack<UploadParam>();

	private static PostPool pool = null;

	public synchronized static PostPool getInstance()
	{
		if (null == pool)
		{
			pool = new PostPool();
		}
		return pool;
	}

	private PostPool()
	{
	}

	public synchronized void sendMessage(UploadParam post)
	{
		reqQueue.push(post);
		executeThread();
	}

	private synchronized UploadParam getPostRequest()
	{
		UploadParam ret = null;
		if (!reqQueue.isEmpty())
		{
			ret = reqQueue.pop();
		}
		return ret;
	}

	private void executeThread()
	{
		int size = threads.size();
		for (int i = 0; i < size; ++i)
		{
			if (!threads.get(i).running)
			{
				synchronized (threads.get(i).lock)
				{
					threads.get(i).lock.notify();
				}
				return;
			}
		}
		Sender th = new Sender("http thread " + size);
		if (size < POOL_SIZE)
			threads.add(th);
		th.start();
	}

	private void doPost()
	{
		UploadParam param = getPostRequest();
		if (null == param)
		{
			return;
		}
		String end = "\r\n";
		String twoHen = "--";
		String boundary = "wjd_" + java.util.UUID.randomUUID().toString()
				+ "_wjd";
		URL url = null;
		try
		{
			url = new URL(String.format(Locale.getDefault(),
					"http://192.168.1.150:8080/file/upLoad?ft=%s&rt=upload",
					param.ft));
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		if (null == url)
		{
			return;
		}
		HttpURLConnection conn = null;
		try
		{
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e)
		{
			if (null != param.retCallback)
			{
				obtainMessage(2, UploadRetCallback.FAIL, 0, param)
						.sendToTarget();
			}
			e.printStackTrace();
		}
		conn.setConnectTimeout(5000);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);

		try
		{
			conn.setRequestMethod("POST");
		} catch (ProtocolException e)
		{
			e.printStackTrace();
		}
		conn.setRequestProperty("Accept",
				"text/html, application/xhtml+xml, image/jpeg,*/*");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="
				+ boundary);
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");

		/* DataOutputStream */
		DataOutputStream ds;
		try
		{
			ds = new DataOutputStream(conn.getOutputStream());
			ds.writeBytes(twoHen + boundary + end);
			ds.writeBytes("Content-Disposition:form-data; "
					+ "name=\"f\";filename=\"" + param.localPath + "\"" + end);
			ds.writeBytes("Content-Type:image/jpg" + end + end);

			/* FileInputStream */
			FileInputStream fis = new FileInputStream(FileUtil.newInstance()
					.getFile(param.localPath, false));

			/* 1024bytes */
			byte[] buffer = new byte[4096];

			int count = 0;
			int length = -1;
			int available = fis.available();
			while ((length = fis.read(buffer)) != -1)
			{
				ds.write(buffer, 0, length);
				count += length;
				if (null != param.progressCallback)
				{
					obtainMessage(1, 100 * count / available, 0,
							param.progressCallback).sendToTarget();
				}
			}
			/* close streams */
			fis.close();

			ds.writeBytes(end);
			ds.writeBytes(twoHen + boundary + twoHen);
			ds.writeBytes(end);
			ds.flush();
			int rspCode = conn.getResponseCode();
//			Loger.print(this.getClass().getName(),
//					"upload response code ============ " + rspCode, Loger.ERROR);
			boolean success = HttpURLConnection.HTTP_OK == rspCode;
			if (success)
			{
				InputStream is = conn.getInputStream();
				byte[] buff = new byte[128];
				int len = 0;
				StringBuffer sb = new StringBuffer();
				while ((len = is.read(buff)) != -1)
				{
					sb.append(new String(buff, 0, len));
				}
				param.response = sb.toString();
			}
			if (null != param.retCallback)
			{
				obtainMessage(
						2,
						!success ? UploadRetCallback.FAIL
								: UploadRetCallback.SUCCESS, 0, param)
						.sendToTarget();
			}
		} catch (IOException e)
		{
			if (null != param.retCallback)
			{
				obtainMessage(2, UploadRetCallback.FAIL, 0, param)
						.sendToTarget();
			}
			e.printStackTrace();
		}
	}

	class Sender extends Thread
	{

		byte[] lock = new byte[0];

		boolean running = false;

		public Sender()
		{
			super();
		}

		public Sender(String threadName)
		{
			super(threadName);
		}

		@Override
		public void run()
		{
			doPost();
			running = threads.contains(this);
			while (running)
			{
				synchronized (lock)
				{
					try
					{
						running = false;
						lock.wait();
					} catch (InterruptedException e)
					{
					}
				}
				doPost();
				running = true;
			}
		}
	}

	@Override
	public void handleMessage(Message msg)
	{
		if (msg.what == 1)
		{
			ProgressCallback callback = (ProgressCallback) msg.obj;
			callback.publishProgress(msg.arg1);
		} else if (msg.what == 2)
		{
			UploadParam param = (UploadParam) msg.obj;
			param.retCallback.postResult(param, msg.arg1);
		}
		super.handleMessage(msg);
	}
}

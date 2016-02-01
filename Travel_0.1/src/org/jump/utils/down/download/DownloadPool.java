package org.jump.utils.down.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.jump.utils.FileUtil;
import org.jump.utils.down.ProgressCallback;

import android.os.Handler;
import android.os.Message;



public class DownloadPool extends Handler
{

	public static final String TAG = "DownloadPool";

	private static final int POOL_SIZE = 3;

	private ArrayList<Sender> threads = new ArrayList<Sender>();

	private Stack<DownloadParam> reqQueue = new Stack<DownloadParam>();

	private Stack<DownloadParam> doDownload = new Stack<DownloadParam>();

	private static DownloadPool pool = null;

	public synchronized static DownloadPool getInstance()
	{
		if (null == pool)
		{
			pool = new DownloadPool();
		}
		return pool;
	}

	private DownloadPool()
	{
	}

	public synchronized void sendMessage(DownloadParam post)
	{
		if (reqQueue.contains(post) || doDownload.contains(post))
		{
			return;
		}
		reqQueue.push(post);
		executeThread();
	}

	public synchronized DownloadParam getDownloadRequest()
	{
		DownloadParam ret = null;
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

	private void doDownload()
	{
		DownloadParam param = getDownloadRequest();
		if (null == param)
		{
			return;
		}
		doDownload.push(param);
		HttpParams params = new BasicHttpParams();
		// set timeout
		params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);

		// set message body
		HttpClient httpClient = new DefaultHttpClient(params);

		HttpGet get = new HttpGet(param.fId);
		// send message
		HttpResponse response = null;
		try
		{
			response = httpClient.execute(get);
			boolean success = response.getStatusLine().getStatusCode() == 200;
			if (success)
			{
				storeFile(response.getEntity().getContent(), param);
			}
			if (null != param.retCallback)
			{
				obtainMessage(
						2,
						!success ? DownloadRetCallback.FAIL
								: DownloadRetCallback.SUCCESS, 0,
						param.retCallback).sendToTarget();
			}
		} catch (IOException e)
		{
			if (null != param.retCallback)
			{
				obtainMessage(2, DownloadRetCallback.FAIL, 0, param.retCallback)
						.sendToTarget();
			}
			e.printStackTrace();
		} finally
		{
			doDownload.remove(param);
		}
	}

	public boolean storeFile(InputStream is, DownloadParam param)
	{
		boolean flag = false;
		if (null == is)
			return flag;
		File file = null;
		FileOutputStream fos = null;
		try
		{
			file = FileUtil.newInstance().getFile(param.localPath, true);
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length = 0;
			int count = 0;
			int available = is.available();
			while ((length = is.read(buffer)) > 0)
			{
				fos.write(buffer, 0, length);
				count += length;
				if (null != param.progressCallback)
				{
					obtainMessage(1, 100 * count / available, 0,
							param.progressCallback).sendToTarget();
				}
			}
			flag = true;
		} catch (Exception e)
		{
//			Loger.print("FileUtil", e.getMessage(), Loger.ERROR);
			flag = false;
		} finally
		{
			if (null != fos)
			{
				try
				{
					fos.flush();
					fos.close();
				} catch (IOException e)
				{
//					Loger.print("FileUtil", e.getMessage(), Loger.ERROR);
					flag = false;
				}
			}
			if (null != is)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					flag = false;
				}
			}
		}
		return flag;
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
			doDownload();
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
				doDownload();
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
			DownloadRetCallback callback = (DownloadRetCallback) msg.obj;
			callback.postResult(msg.arg1);
		}
		super.handleMessage(msg);
	}
}

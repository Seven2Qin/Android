package org.jump.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;

public class FileUtil
{

	private static final String BASE_URL = "/jmail";
	private static final String ATTACHMENT = "/jmail/attachment";
	private static final String IMAGE = "/jmail/image";
	private static final String AUDIO = "/jmail/audio";
	private static final String TEMP = "/jmail/temp";

	private static FileUtil ins = null;

	public synchronized static FileUtil newInstance()
	{
		if (null == ins)
			ins = new FileUtil();
		return ins;
	}

	private FileUtil()
	{
	}

	public synchronized File getFile(String fileName, boolean needToCreate)
	{
		File file = null;
		if (externalStorageAvilable())
		{
			file = new File(Environment.getExternalStorageDirectory(), fileName);
			if (!file.exists() && needToCreate)
			{
				try
				{
					file.createNewFile();
				} catch (IOException e)
				{
					file = null;
				}
			}
		}
		return file;
	}

	public synchronized File getFileAbsolute(String fileName)
	{
		File file = null;
		if (externalStorageAvilable())
		{
			file = new File(fileName);
		}
		return file;
	}

	public synchronized long getFileLength(String fileName, boolean create)
	{
		File file = getFile(fileName, create);
		if (null != file && file.exists())
		{
			return file.length();
		}
		return 0;
	}

	public void initDir(Context context)
	{
		if (externalStorageAvilable())
		{
			File rloveDir = new File(Environment.getExternalStorageDirectory(),
					BASE_URL);
			if (!rloveDir.exists())
				rloveDir.mkdir();
			File attachment = new File(
					Environment.getExternalStorageDirectory(), ATTACHMENT);
			if (!attachment.exists())
				attachment.mkdir();
			File img = new File(Environment.getExternalStorageDirectory(),
					IMAGE);
			if (!img.exists())
				img.mkdir();
			File temp = new File(Environment.getExternalStorageDirectory(),
					TEMP);
			if (!temp.exists())
				temp.mkdir();
			File audio = new File(Environment.getExternalStorageDirectory(),
					AUDIO);
			if (!audio.exists())
				audio.mkdir();
		}
	}

	public static String append(String url)
	{
		return BASE_URL + "/" + url;
	}

	public static String appendWithAttach(String url)
	{
		return ATTACHMENT + "/" + url;
	}

	public static String appendWithImg(String url)
	{
		return IMAGE + "/" + url;
	}

	public static String appendWithAudio(String url)
	{
		return AUDIO + "/" + url;
	}

	public static String appendWithTemp(String url)
	{
		return TEMP + "/" + url;
	}

	public void delDirectory(String dir)
	{
		File file = getFile(dir, false);
		if (null != file && file.exists() && file.isDirectory())
		{
			File[] files = file.listFiles();
			for (File f : files)
			{
				if (f.isFile())
					f.delete();
			}
		}
	}

	public void delFile(String route)
	{
		File file = getFile(route, false);
		if (null != file && file.exists())
		{
			file.delete();
		}
	}

	public synchronized boolean storeFile(InputStream is, String name)
	{
		boolean flag = false;
		if (null == is)
			return flag;
		File file = null;
		FileOutputStream fos = null;
		try
		{
			file = getFile(name, true);
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = is.read(buffer)) > 0)
			{
				fos.write(buffer, 0, length);
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

	public synchronized boolean fileExist(String route)
	{
		File file = getFile(route, false);
		return null != file && file.exists();
	}

	public synchronized void renameFile(String srcRoute, String destRoute)
	{
		File file = getFile(srcRoute, false);
		if (null != file && file.exists())
		{
			file.renameTo(getFile(destRoute, true));
		}
	}

	public String constructRoute(String prefix, String suffix)
	{
		return new StringBuffer(prefix).append("/").append(suffix).toString();
	}

	public boolean externalStorageAvilable()
	{
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}
}

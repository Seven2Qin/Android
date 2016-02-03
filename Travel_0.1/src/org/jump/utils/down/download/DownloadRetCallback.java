package org.jump.utils.down.download;

public interface DownloadRetCallback
{
	public static final int SUCCESS = 0;
	public static final int FAIL = 1;

	public void postResult(int ret);
}

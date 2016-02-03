package org.jump.utils.down.download;

import org.jump.utils.down.ProgressCallback;



public class DownloadParam
{

	public String timestamp;

	public String fId;

	public String localPath;

	public ProgressCallback progressCallback;

	public DownloadRetCallback retCallback;

	public String ft = "123";

	public String getSuffix()
	{
		return localPath.substring(localPath.lastIndexOf(".") + 1);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DownloadParam other = (DownloadParam) obj;
		if (timestamp == null)
		{
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
}

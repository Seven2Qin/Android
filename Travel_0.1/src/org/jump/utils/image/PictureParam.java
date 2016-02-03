package org.jump.utils.image;

import org.jump.utils.FileUtil;
import org.jump.utils.down.download.DownloadParam;


public class PictureParam extends DownloadParam
{
	public int width;

	public static PictureParam constructPictureParam(String fid,
			String timestamp, int width)
	{
		PictureParam param = new PictureParam();
		param.fId = fid;
		param.localPath = FileUtil.appendWithImg(fid + ".jpg");
		param.timestamp = timestamp;
		param.width = width;
		return param;
	}
	
	public static PictureParam constructPictureParam(String url, int width)
    {
        PictureParam param = new PictureParam();
        param.fId = url;
        //取文件名
        String fid = "1";
        param.localPath = FileUtil.appendWithImg(fid + ".jpg");
        param.width = width;
        return param;
    }
}

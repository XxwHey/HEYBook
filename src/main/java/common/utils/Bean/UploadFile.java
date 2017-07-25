package common.utils.Bean;

import java.io.File;

/**
 * Created by Administrator on 2017/2/27.
 * 上传文件工具类
 */
public class UploadFile {

    private String origFileName;

    private String realFileName;

    private long fileLength;

    private String fileType;

    private String absolutePath;

    private File file;

    public UploadFile(String origFileName, String realFileName, long fileLength, String fileType, String absolutePath, File file) {
        super();
        this.origFileName = origFileName;
        this.realFileName = realFileName;
        this.fileLength = fileLength;
        this.fileType = fileType;
        this.absolutePath = absolutePath;
        this.file = file;
    }

    public String getOrigFileName() {
        return origFileName;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public long getFileLength() {
        return fileLength;
    }

    public String getFileType() {
        return fileType;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public File getFile() {
        return file;
    }
}

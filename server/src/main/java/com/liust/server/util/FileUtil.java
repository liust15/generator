package com.liust.server.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    /**
     * @param fileContent 源文件内容
     * @param zos      zip输出流
     * @param filePathAndName    文件路径和文件名
     *                 false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    public static void compress(ZipOutputStream zos, String fileContent, String filePathAndName) throws IOException {
        byte[] buf = new byte[2 * 1024];
        zos.putNextEntry(new ZipEntry(filePathAndName));
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        int len;
        while ((len = inputStream.read(buf)) != -1) {
            zos.write(buf, 0, len);
        }
        zos.closeEntry();
    }
}

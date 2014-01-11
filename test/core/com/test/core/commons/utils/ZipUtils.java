
package com.test.core.commons.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.test.core.log.Log;

public class ZipUtils
{
    public static void main(String[] args) throws IOException
    {
        OutputStream bos = null;
        try
        {
            bos = new BufferedOutputStream(new FileOutputStream("c:/xx.zip"));
            zip(new File("c:/Regx.xml"), bos);
        }
        finally
        {
            IOUtils.closeQuietly(bos);
        }

    }

    static Log log = Log.getInstance(ZipUtils.class);

    private ZipUtils()
    {
    }

    /**
     * 压缩一个目录为一个zip文件
     * @param dir
     *        全路径目录名
     * @param zipFileName
     *        zip文件名
     * @throws IOException
     */
    static public void zipDir(String dir, String zipFileName) throws IOException
    {
        log.debug("zip ", zipFileName, "....");
        zipDir(new File(dir), new File(zipFileName));
    }

    static public void zipDir(File srcDir, File toZipFile) throws IOException
    {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toZipFile));
        ZipOutputStream out = new ZipOutputStream(bos);
        zip(out, srcDir, "");
        log.debug("zip  ", srcDir, " to ", toZipFile, " done");
        out.close();
    }

    static public void zipDir(File srcDir, OutputStream outStream) throws IOException
    {
        BufferedOutputStream bos = new BufferedOutputStream(outStream);
        ZipOutputStream out = new ZipOutputStream(bos);
        zip(out, srcDir, "");
        log.debug("zip  ", srcDir, " to  outstream done");
        out.close();
    }

    /**
     * 将一个压缩文件，解压到一个目录下
     * @param srcZipFile
     *        源zip文件
     * @param toDir
     *        解压目录
     * @throws IOException
     */
    static public void upzip(File srcZipFile, File toDir) throws IOException
    {
        upzip(srcZipFile.getAbsolutePath(), toDir.getAbsolutePath());
    }

    static public void upzip(String zipFileName, String toDir) throws IOException
    {
        log.debug("Extracting to: ", toDir);
        final int BUFFER = 2048;
        BufferedOutputStream dest = null;
        FileInputStream fis = new FileInputStream(zipFileName);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null)
        {
            if (entry.getName().equals("/"))
            {
                continue;
            }
            log.debug("Extracting: ", entry);
            int count;
            byte data[] = new byte[BUFFER];
            FileOutputStream fos = new FileOutputStream(toDir + "/" + entry.getName());
            dest = new BufferedOutputStream(fos, BUFFER);
            while ((count = zis.read(data, 0, BUFFER)) != -1)
            {
                dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
        }
        zis.close();
    }

    static private void zip(ZipOutputStream out, File f, String base) throws IOException
    {
        if (f.isDirectory())
        {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++)
            {
                zip(out, fl[i], base + fl[i].getName());
            }
        }
        else
        {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(in);
            byte[] buf = new byte[1024 * 2];
            int len;
            log.debug("zip ", base, "...");
            while ((len = bis.read(buf)) != -1)
            {
                out.write(buf, 0, len);
            }
            in.close();
        }
    }

    static public void zip(File file, OutputStream output)
    {
        FileInputStream input  = null;
        ZipOutputStream zipout = null;
        try
        {
            if (file.isDirectory())
            {
                throw new IOException("must not be a Directory!");
            }
            zipout = new ZipOutputStream(output);
            input = FileUtils.openInputStream(file);
            zipout.putNextEntry(new ZipEntry(file.getName()));
            byte[] buf = new byte[1024 * 2];
            int len;
            while ((len = input.read(buf)) != -1)
            {
                zipout.write(buf, 0, len);
            }
            log.debug("zip ", file, " ok!");
        }
        catch (Exception e)
        {
            log.error("zip ", file, " ", e.getMessage());
        }
        finally
        {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(zipout);
        }
    }
}

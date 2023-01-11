package com.lixin.common.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXin
 * @date 2021/4/15 13:50
 * @description FileUtils was created at  2021/4/15 13:50 by LiXin
 */
public class FileUtils {
    /**
     * 从assert获取文件路径
     * @param context
     * @return
     */
    public List<String> getAssetPath(Context context) {
        AssetManager am = context.getAssets();
        String[] path = null;
        try {
            path = am.list("");  // ""获取所有,填入目录获取该目录下所有资源
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < path.length; i++) {
            if (path[i].contains("EyeCoolLive.lic") || path[i].contains("Duck.dat")) {
                filePaths.add(path[i]);
            }
        }
        return filePaths;
    }
    /**
     * @param bfile         文件的byte流
     * @param filePath      文件要保存的路径
     * @param fileName      文件保存的名字
     */
    public static void saveFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            //通过创建对应路径的下是否有相应的文件夹。
            File dir = new File(filePath);
            if (!dir.exists()) {// 判断文件目录是否存在
                //如果文件存在则删除已存在的文件夹。
                dir.mkdirs();
            }
            //如果文件存在则删除文件
            file = new File(filePath, fileName);
            if(file.exists()){
                file.delete();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            //把需要保存的文件保存到SD卡中
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

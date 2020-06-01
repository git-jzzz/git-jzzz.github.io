package cn.sb.audioInterface.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class IoServiceImpl implements IoService {
    @Override
    public String readFile(String path, String musicjson) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                buf.append(line + "\n");
            }
            int start = buf.indexOf("\"audio\": ") + 9;
            int end = buf.indexOf("});") - 1;
            buf.replace(start, end, musicjson);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    br = null;
                }
            }
        }
        return buf.toString();
    }

    @Override
    public void writeFile(String path, String content) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                bw = null;
            }
        }
    }
}

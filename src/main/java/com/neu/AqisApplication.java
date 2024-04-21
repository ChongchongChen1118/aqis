package com.neu;

import com.neu.grid.mapper.GridIdeaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

@SpringBootApplication
public class AqisApplication {

    static GridIdeaMapper gridIdeaMapper;
    @Autowired
    public void setGridIdeaMapper(GridIdeaMapper gridIdeaMapper){
        AqisApplication.gridIdeaMapper=gridIdeaMapper;
    }
    public static void main(String[] args) {
        SpringApplication.run(AqisApplication.class, args);
//        jieshou();
    }

    public static void jieshou() {
        try {
            // 蓝牙设备的地址，需要替换为你要连接的蓝牙设备地址
            String bluetoothDeviceAddress = "F7FD2B117BB6";

            // 使用BlueCove连接到蓝牙设备
            String connectionString = "btspp://" + bluetoothDeviceAddress + ":1;authenticate=false;encrypt=false;master=false";
            StreamConnection connection = (StreamConnection) Connector.open(connectionString);

            // 打开输入流以接收数据
            InputStream inputStream = connection.openInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

            // 无限循环来持续读取数据
            while (true) {
                char[] buffer = new char[1024];
                int bytesRead = reader.read(buffer);

                if (bytesRead == -1) {
                    // 读取到流的末尾，连接可能已关闭
                    break;
                }

                // 将接收到的数据以UTF-8编码打印出来
                String receivedData = new String(buffer, 0, bytesRead);
                System.out.println(receivedData);
                gridIdeaMapper.insertLanya(receivedData,new Date());
            }

            // 关闭连接和输入流
            reader.close();
            inputStream.close();
            connection.close();
        }
        catch (Exception e) {
            jieshou();
        }
    }
}

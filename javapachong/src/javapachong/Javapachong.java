/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapachong;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeString.indexOf;

/**
 *
 * @author weme
 */
//简易版的爬虫项目
public class Javapachong {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       Scanner input = new Scanner(System.in);
       System.out.println("please input the url:");
       String urlString = input.nextLine();
       Crawler(urlString);
    }
    public static void Crawler(String urlString) throws IOException{
        ArrayList<String> listWaitTravel = new ArrayList<>();//创建等待浏览列表
        ArrayList<String> listTravelled = new ArrayList<>();//创建已浏览列表
        listWaitTravel.add(urlString);//将要浏览的URL添加
       // String list = listWaitTravel.remove(0);
        while(!listWaitTravel.isEmpty() && listTravelled.size()<=100){
            String StartString = listWaitTravel.remove(0);
            if(!listTravelled.contains(StartString)){
                 listTravelled.add(StartString);
                System.out.println("Crawler:" + StartString);
                //listTravelled.add(StartString);
                for(String s: getUrlString(StartString)){
                   if(!listTravelled.contains(s))
                    listWaitTravel.add(s);
                }
            }
        }
    }
    public static ArrayList<String> getUrlString(String StartString) throws IOException{
        ArrayList<String> list = new ArrayList<String>();//创建一个列表用来保存每一个URL接下来的URL
        try {
            URL url = new URL(StartString);
            Scanner input1 = new Scanner(url.openStream());//读取URL内的文件流
            int current = 0;
            while(input1.hasNext()){
                String line = input1.nextLine();
                current = line.indexOf("http:",current);//找到URL的首位置
                while(current>0){
                    int endIndex = line.indexOf("\"",current);//找到URL尾位置，注意：为了表示"，得用\"
                    if(endIndex>0){
                        list.add(line.substring(current,endIndex));
                        current = line.indexOf("http:",endIndex);
                    }
                    else current = -1;
                }
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Javapachong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}

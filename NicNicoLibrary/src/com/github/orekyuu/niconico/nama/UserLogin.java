package com.github.orekyuu.niconico.nama;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.github.orekyuu.niconico.XMLHelper;

public class UserLogin implements Login{

	private String thread;
	private String address;
	private String port;

	private String ticket;

	private String sendData;

	UserLogin(String mail,String password){
		sendData="mail="+mail+"&password="+password;
	}

	@Override
	public void login() {
		authentication1();
		authentication2();
	}

	private void authentication1(){
		try{
			URL url=new URL("https://secure.nicovideo.jp/secure/login?site=nicolive_antenna");
			HttpURLConnection http=(HttpURLConnection) url.openConnection();
			http.setConnectTimeout(3000);
			http.setRequestMethod("POST");
			http.setUseCaches(false);
			http.setDoOutput(true);
			http.connect();

			DataOutputStream os=new DataOutputStream(http.getOutputStream());
			os.writeBytes(sendData);
			os.flush();

			Scanner scanner=new Scanner(http.getInputStream(),"utf-8");
			String lines="";
			while(scanner.hasNext()){
				lines+=scanner.nextLine();
			}
			ticket=XMLHelper.getXMLValue(lines, "ticket").get(0);
			scanner.close();
			http.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void authentication2(){
		try{
			URL url=new URL("http://live.nicovideo.jp/api/getalertstatus");
			HttpURLConnection http=(HttpURLConnection) url.openConnection();
			http.setConnectTimeout(3000);
			http.setRequestMethod("POST");
			http.setUseCaches(false);
			http.setDoOutput(true);
			http.connect();

			DataOutputStream os=new DataOutputStream(http.getOutputStream());
			os.writeBytes("ticket="+ticket);
			os.flush();

			Scanner scanner=new Scanner(http.getInputStream(),"utf-8");
			String lines="";
			while(scanner.hasNext()){
				lines+=scanner.nextLine();
			}
			address=XMLHelper.getXMLValue(lines, "addr").get(0);
			port=XMLHelper.getXMLValue(lines, "port").get(0);
			thread=XMLHelper.getXMLValue(lines, "thread").get(0);

			scanner.close();
			http.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String getThreadID() {
		return thread;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getPort() {
		return port;
	}

}

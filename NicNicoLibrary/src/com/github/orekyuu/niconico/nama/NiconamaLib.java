package com.github.orekyuu.niconico.nama;

import java.util.ArrayList;
import java.util.List;

/**
 * ニコ生関係のライブラリです
 * @author kyuuban
 *
 */
public class NiconamaLib {

	private NiconamaThread guest;
	private List<NiconamaListener> list;

	public NiconamaLib(){
		list=new ArrayList<NiconamaListener>();
	}
	/**
	 * ゲストで認証してスレッドを開始
	 */
	public void startGuestThread(){
		Login login=new GuestLogin();
		login.login();
		guest=new NiconamaThread(login.getAddress(), login.getPort(), login.getThreadID(), list);
		guest.start();
	}

	/**
	 * ユーザーで認証してスレッドを開始
	 * @param mail
	 * @param password
	 */
	public void startUserThread(String mail,String password){
		Login login=new UserLogin(mail,password);
		login.login();
		guest=new NiconamaThread(login.getAddress(), login.getPort(), login.getThreadID(), list);
		guest.start();
	}

	/**
	 * リスナを登録
	 * @param listener
	 */
	public void addNiconamaListener(NiconamaListener listener){
		list.add(listener);
	}
}

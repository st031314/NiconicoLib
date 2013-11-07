package com.github.orekyuu.niconico.nama;

/**
 * ログインできることを表すインターフェイス
 * @author kyuuban
 *
 */
public interface Login {

	/**
	 * ログイン
	 */
	void login();

	/**
	 * ThreadID
	 * @return
	 */
	String getThreadID();

	/**
	 * サーバーのアドレス
	 * @return
	 */
	String getAddress();

	/**
	 * ポート
	 * @return
	 */
	String getPort();
}

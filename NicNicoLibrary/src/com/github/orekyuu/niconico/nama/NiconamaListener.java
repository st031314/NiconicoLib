package com.github.orekyuu.niconico.nama;

/**
 * メッセージを受け取るためのリスナ
 * @author kyuuban
 *
 */
public interface NiconamaListener {

	/**
	 * 情報を受取る
	 * @param info
	 */
	void onReceive(StreamInfo info);
}

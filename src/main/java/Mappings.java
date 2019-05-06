/**
 * セレクタを定義するクラス
 *
 * @author Yusuke Ota
 *
 */

public class Mappings {

	//会員ログイン
	public static final  String MEMBER_LOGIN = "#JS_loginBoxHeader > div > div > ul > li:nth-child(2) > a > div";

	//お客様番号
	public static final String MEMBER_NO = "#member_no";

	//アクセスコード
	public static final String ACCESS_CD = "#access_cd";

	//ログインボタン
	public static final String LOGIN_BTN ="#js-login-form-main-login > div > div.login-form-inputs > button > span";

	public static final String LOGIN_CONTINUE_BTN = "#JS_login";

	// 予約確認ボタン
	public static final String RESERVATION_BTN ="#JS_121_jmbStsBoxB > div.l-wrapper > footer > nav > ul > li.l-nth-1 > a";

	// 国内線特典航空券ボタン
	public static final String SPECIAL_TICKET_BTN = "#contents > div.selectedInfoBlockA01 > ul.rsvSearch_rn.marB25 > li:nth-child(1) > a";

	// 国内線特典航空券 一番目の予約
	public static final String SPECIAL_TICKET_FIRST_RESERVATION ="#contents > div.selectedInfoBlockA01 > table > tbody > tr > td.pdTB10 > a > img";

	// 予約変更ボタン
	public static final String CHANGE_RESERVATION_BTN ="body > div.actionBlockA01 > form > table > tbody > tr > td > input:nth-child(1)";

	// 変更ボタン
	public static final String CHANGE_BTN = "body > form > div.selectedInfoBlockA01 > table > tbody > tr > td.pseudo-lastChild > a";

	// 変更する日
	public static final String DAY = "body > form > div.flightSelectFormBlockA01 > table.selectionBlock03 > tbody > tr > td > table:nth-child(2) > tbody > tr > td:nth-child(1) > select:nth-child(2)";

	// 空席状況確認ボタン
	public static final String CONFIRM_BTN = "body > form > div.actionBlockA01 > div > table > tbody > tr > td > input";

	// 予約一覧
	public static final String RESERVATION_LIST = "table > tbody > tr > td.pricebtn:nth-child(5)";

}

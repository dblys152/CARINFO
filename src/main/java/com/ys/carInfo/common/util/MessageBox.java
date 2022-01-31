package com.ys.carInfo.common.util;

import java.util.regex.Pattern;

import org.springframework.ui.Model;

public class MessageBox {
	/**
	 * 권한 에러 메시지를 보이주고 뒤로 가기
	 * @param model
	 * @return
	 */
	public static String showPermErrMsgAndBack(Model model) {
		return showPermErrMsgAndMoveTo(model, null);
	}

	/**
	 * 권한 에러 메시지를 보여주고 해당 url로 이동.
	 * @param model, url
	 * @return
	 */
	public static String showPermErrMsgAndMoveTo(Model model, String url) {
		return showMsgAndMoveTo(model, "권한이 없습니다.", url);
	}

	/**
	 * 메세지를 보여주고 뒤로 가기
	 * @param model, msg
	 * @return
	 */
	public static String showMsgAndBack(Model model, String msg) {
		return showMsgAndMoveTo(model, msg, null);
	}

	/**
	 * 메세지를 보여주고 여러 페이지 뒤로 가기
	 * @param model, msg, pages
	 * @return
	 */
	public static String showMsgAndBack(Model model, String msg, int pages) {
		model.addAttribute("messages", escapeEnter(msg));
		model.addAttribute("history", -pages);
		return "/common/messageAndMove";
	}

	/**
	 * 메세지를 보여주고 해당 url로 이동
	 * @param model, msg, url
	 * @return
	 */
	public static String showMsgAndMoveTo(Model model, String msg, String url) {
		model.addAttribute("messages", escapeEnter(msg));
		model.addAttribute("redirect", url);
		return "/common/messageAndMove";
	}


	/**
	 * 메세지를 보여주고 해당창 닫기
	 * @param model, msg, url
	 * @return
	 */
	public static String showMsgAndClose(Model model, String msg) {
		model.addAttribute("close", escapeEnter(msg));
		return "/common/messageAndMove";
	}

	/**
	 * 메세지를 보여주고 해당창 닫고 부모 창 리플레시
	 * @param model, msg, url
	 * @return
	 */
	public static String showMsgRefreshParentAndClose(Model model, String msg) {
		model.addAttribute("refreshParentAndClose", escapeEnter(msg));
		return "/common/messageAndMove";
	}

	private static String escapeEnter(String text) {
		if(Pattern.compile("(\r\n|\r|\n|\n\r)").matcher(text).find())
			return text.replaceAll("(\r\n|\r|\n|\n\r)", "\\\\n");
		return text;
	}
}

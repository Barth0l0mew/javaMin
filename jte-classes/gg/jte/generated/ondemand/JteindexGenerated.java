package gg.jte.generated.ondemand;
import Session.MainPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,12,12,12,13,13,13,15,15,17,17,18};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MainPage page) {
		jteOutput.writeContent("\r\n<!DOCTYPE html>\r\n<html lang=\"ru\">\r\n<head>\r\n    <meta charset=\"utf-8\" />\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n    <title>Hello</title>\r\n</head>\r\n<body>\r\n<main>\r\n    ");
		if (page.getCurrentUser() != null) {
			jteOutput.writeContent("\r\n        Добро пожаловать, ");
			jteOutput.setContext("main", null);
			jteOutput.writeUserContent(page.getCurrentUser());
			jteOutput.writeContent(".\r\n        Чтобы разлогиниться, удалите куку JSESSIONID из браузера\r\n    ");
		} else {
			jteOutput.writeContent("\r\n        Вы не залогинились.\r\n    ");
		}
		jteOutput.writeContent("\r\n</main>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MainPage page = (MainPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}

package support;

public class SplitHTML {

	public static void main(String[] args) {

		String HTML = "";
		HTML += "<p>HTML P Tag String</p>";
		HTML += "<p><a href='/test/link.html'>HTML A Tag String</a></p>";

		String htmlTagPattern = "<{1}[^>]{1,}>{1}"; // HTML Tag Pattern
		String htmlSplit = HTML.replaceAll(htmlTagPattern, ""); // 移除HTML Tag

		System.out.println("htmlSplit : " + htmlSplit); // 輸出htmlSplit : HTML P Tag StringHTML A Tag String

	}

}

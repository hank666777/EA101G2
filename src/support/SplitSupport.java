package support;

public class SplitSupport {
	
	public static String[] getImgURL(String postdetail) throws ArrayIndexOutOfBoundsException  {
		String str = postdetail; 
				
		String[] tokens = str.split("//");
		String[] img = null;
		for(int i=0;i<tokens.length;i++) {
			if(tokens[i].contains(".jpg")) {
				img = tokens[i].split(">") ;
				break;
			};
			
		}
		
		return img;
	}
	
	public static String trimHTML(String HTML) {
		

		String htmlTagPattern = "<{1}[^>]{1,}>{1}"; // HTML Tag Pattern
		String htmlSplit = HTML.replaceAll(htmlTagPattern, ""); // 移除HTML Tag

		//System.out.println("htmlSplit : " + htmlSplit); // 輸出htmlSplit : HTML P Tag StringHTML A Tag String

		
		return htmlSplit;
	}

	
	public static void main(String[] args) {
		//test Method getImgURL...
//		String str = 
//				"<div style=\"text-align:left\"><div class=\"wsite-image wsite-image-border-thin\" style=\"padding-bottom:10px; padding-left:2px !important; padding-right:2px !important; padding-top:10px; text-align:center\"><span style=\"font-size:16px\"><span style=\"color:#626262\"><span style=\"font-family:Lato,sans-serif\"><span style=\"background-color:#ffffff\"><img alt=\"图片\" src=\"https://yowhistlebaby.weebly.com/uploads/2/7/0/5/27056787/6078677_orig.jpg\" /></span></span></span></span><div>&nbsp;</div>";
//		
//		String [] urlArray = getImgURL(str);
//		
//		for(String url: urlArray) {
//			System.out.println(url);
//		}
		
		//test Method trimHTML...
//		String HTML = "";
//	    HTML+= "<p>HTML P Tag String</p>";
//	    HTML+= "<p><a href='/test/link.html'>HTML A Tag String</a></p>";
//	    
//	    trimHTML(HTML);

		

	}

}

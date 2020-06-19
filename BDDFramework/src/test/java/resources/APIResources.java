package resources;

public enum APIResources {

	CreateIssueAPI("/rest/api/2/issue"),
	GetIssueAPI("/rest/api/2/issue/","{issueIdOrKey}"),
	DeleteIssueAPI("/rest/api/2/issue/","{issueIdOrKey}");
	private String resource;

	APIResources(String string) {
		// TODO Auto-generated constructor stub
	}

	APIResources(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	
	public String getResource() {
		return resource;
	}

}

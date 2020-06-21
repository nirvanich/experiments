package resources;

public enum APIResources {

	JiraIssueAPI("/rest/api/2/issue");
	private String resource;

	APIResources(String string) {
		// TODO Auto-generated constructor stub
		this.resource = string;
	}
	
	public String getResource() {
		
		return resource;
	}

}

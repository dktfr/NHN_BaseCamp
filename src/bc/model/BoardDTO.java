package bc.model;

import java.sql.Timestamp;

public class BoardDTO
{
	private int num;
	private String email;
	private String pwd;
	private String content;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	
	public BoardDTO() {}
	public BoardDTO(int num, String email, String pwd, String content, Timestamp createdDate, Timestamp modifiedDate)
	{
		this.num = num;
		this.email = email;
		this.pwd = pwd;
		this.content = content;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("num : ").append(num).append(", ")
				.append("email : ").append(email).append(", ")
				.append("pwd : ").append(pwd).append(", ")
				.append("content : ").append(content).append(", ")
				.append("date : ").append(createdDate);
		return builder.toString();
	}
	
	public int getNum() { return num; }
	public void setNum(int num) { this.num = num; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getPwd() { return pwd; }
	public void setPwd(String pwd) { this.pwd = pwd; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public String getCreatedDateToString()
	{
		int length = createdDate.toString().length();
		return createdDate.toString().substring(0, length-2);
	}
	public Timestamp getCreatedDate() { return createdDate; }
	public void setCreatedDate(Timestamp createdDate) { this.createdDate = createdDate; }
	
	public String getModifiedDateToString()
	{
		int length = modifiedDate.toString().length();
		return modifiedDate.toString().substring(0, length-2);
	}
	public Timestamp getModifiedDate() { return modifiedDate; }
	public void setModifiedDate(Timestamp modifiedDate) { this.modifiedDate = modifiedDate; }
}

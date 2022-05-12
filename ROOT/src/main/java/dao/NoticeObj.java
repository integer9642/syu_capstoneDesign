package dao;

public class NoticeObj {
	private String id, content, ts, images;
	public NoticeObj(String id, String content, String ts, String images) {
	this.id = id;
	this.content = content;
	this.ts = ts;
	this.images = images;
	}
	public String getId() { return this.id; }
	public String getContent() { return this.content; }
	public String getTs() { return this.ts; }
	public String getImages() { return this.images; }

}

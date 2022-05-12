package dao;

public class ImageObj {
	private String id, ts, images;
	public ImageObj(String id, String ts, String images) {
	this.id = id;
	this.ts = ts;
	this.images = images;
	}
	public String getId() { return this.id; }
	public String getTs() { return this.ts; }
	public String getImages() { return this.images; }

}

package cn.e3mall.common.pojo;

import java.io.Serializable;
/**
 * EasyUI的Tree节点
 * <p>Title: EasyUITreeNode</p>
 * <p>Description:树节点工具类 </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class EasyUITreeNode implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = -3722391707720182282L;
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private String text;
	private String state;
}

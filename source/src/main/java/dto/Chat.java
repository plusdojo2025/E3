package dto;

import java.io.Serializable;

public class Chat implements Serializable {
    private String chatText;    // テキスト本文
    private int roomId;         // ルームID
    private int senderId;       // 送信者ID
    private String chatDate;    // 送信日時
	private int id;             // ID
	
	
	public String getChatText() {
		return chatText;
	}
	public void setChatText(String chatText) {
		this.chatText = chatText;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getChatDate() {
		return chatDate;
	}
	public void setChatDate(String chatDate) {
		this.chatDate = chatDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Chat(String chatText, int roomId, int senderId, String chatDate, int id) {
		super();
		this.chatText = chatText;
		this.roomId = roomId;
		this.senderId = senderId;
		this.chatDate = chatDate;
		this.id = id;
	}
 
	public Chat (){
		super();
		this.chatText = "";
		this.roomId = 0;
		this.senderId = 0;
		this.chatDate = "";
		this.id = 0;
	}

}

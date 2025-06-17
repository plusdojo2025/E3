package dto;

import java.io.Serializable;

public class Chat implements Serializable {
	private int id;             // ID
	private int roomId;         // ルームID
	private int senderId;       // 送信者ID
    private String chatText;    // テキスト本文
    private String chatDate;    // 送信日時

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getChatText() {
		return chatText;
	}

	public void setChatText(String chatText) {
		this.chatText = chatText;
	}

	public String getChatDate() {
		return chatDate;
	}

	public void setChatDate(String chatDate) {
		this.chatDate = chatDate;
	}

	public Chat(int id, int roomId, int senderId, String chatText, String chatDate) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.senderId = senderId;
		this.chatText = chatText;
		this.chatDate = chatDate;
	}
 
	public Chat (){
		super();
		this.id = 0;
		this.roomId = 0;
		this.senderId = 0;
		this.chatText = "";
		this.chatDate = "";
	}

}

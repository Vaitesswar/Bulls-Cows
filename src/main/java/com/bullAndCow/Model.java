package com.bullAndCow;

import java.time.LocalDateTime;
import java.util.Objects;

public class Model {
	
	private int gameID;
	private int no1;
	private int no2;
	private int no3;
	private int no4;
	private LocalDateTime timeStamp;
	private int partial;
	private int exact;
	private boolean status;
	
	public Model(){};
	
	public Model(int gameID, int no1, int no2, int no3, int no4, LocalDateTime timeStamp, int partial, int exact,
			boolean status) {
		super();
		this.gameID = gameID;
		this.no1 = no1;
		this.no2 = no2;
		this.no3 = no3;
		this.no4 = no4;
		this.timeStamp = timeStamp;
		this.partial = partial;
		this.exact = exact;
		this.status = status;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getNo1() {
		return no1;
	}
	public void setNo1(int no1) {
		this.no1 = no1;
	}
	public int getNo2() {
		return no2;
	}
	public void setNo2(int no2) {
		this.no2 = no2;
	}
	public int getNo3() {
		return no3;
	}
	public void setNo3(int no3) {
		this.no3 = no3;
	}
	public int getNo4() {
		return no4;
	}
	public void setNo4(int no4) {
		this.no4 = no4;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getPartial() {
		return partial;
	}
	public void setPartial(int partial) {
		this.partial = partial;
	}
	public int getExact() {
		return exact;
	}
	public void setExact(int exact) {
		this.exact = exact;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(exact, gameID, no1, no2, no3, no4, partial, status, timeStamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		return exact == other.exact && gameID == other.gameID && no1 == other.no1 && no2 == other.no2
				&& no3 == other.no3 && no4 == other.no4 && partial == other.partial && status == other.status
				&& Objects.equals(timeStamp, other.timeStamp);
	}
	@Override
	public String toString() {
		return "Model [gameID=" + gameID + ", no1=" + no1 + ", no2=" + no2 + ", no3=" + no3 + ", no4=" + no4
				+ ", timeStamp=" + timeStamp + ", partial=" + partial + ", exact=" + exact + ", status=" + status + "]";
	}	

}

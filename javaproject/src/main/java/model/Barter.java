package model;

public class Barter {
	private boolean is_complete;
	private int service_id_1;
	private int service_id_2;
	public Barter(){
		is_complete = false;
		service_id_1 = 0;
		service_id_2 = 0;
	}
	public void setIs_complete(boolean is_complete) {
		this.is_complete = is_complete;
	}
	public boolean getIs_complete() {
		return is_complete;
	}
	public int getService_id1() {
		return service_id_1;
	}
	public void setService_id1(int service_id_1) {
		this.service_id_1 = service_id_1;
	}
	public int getService_id2() {
		return service_id_2;
	}
	public void setService_id2(int service_id_2) {
		this.service_id_2 = service_id_2;
	}
	
}

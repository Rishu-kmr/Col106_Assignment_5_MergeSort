package col106.assignment5;

public class DateNode implements DateInterface {

	int day;
	int month;
	int year;

	public DateNode(int day, int month , int year){
		this.day = day;
		this.month= month;
		this.year = year;
	}

	public int getDay(){
		return this.day;
	}

	public int getMonth(){
		return this.month;
	}

	public int getYear(){
		return this.year;
	}

	public int compareTo(DateNode dateobj) {
		// TODO Auto-generated method stub
		if(this.year>dateobj.year) {
			return 1;
		}
		else if(this.year<dateobj.year) {
			return -1;
		}
		else if(this.month > dateobj.month) {
			return 1;
		}
		else if(this.month < dateobj.month) {
			return -1;
		}
		else if(this.day > dateobj.day) {
			return 1;
		}
		else if(this.day < dateobj.day) {
			return -1;
		}
		return 0;
	}

}

package ressources;

/** Enum�ration des fr�quences possibles */
public enum Frequence {
	F14(1.4),
	F16(1.6),
	F18(1.8),
	F20(2.0),
	F22(2.2),
	F24(2.4),
	F26(2.6),
	F28(2.8),
	F30(3.0);
	
	private double frequence;
	
	private Frequence(double freq){
		this.frequence=freq;
	}
	
	public double getFrequence(){
		return this.frequence;
	}
	
}

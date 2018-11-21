package model;

public class Mesa {
	private int idMesa;
	private int numeromesa;
	private int numerosillas;
	public Mesa(int idMesa, int numeromesa, int numerosillas) {
		super();
		this.idMesa = idMesa;
		this.numeromesa = numeromesa;
		this.numerosillas = numerosillas;
	}
	public Mesa( int numromesa, int numrosillas) {
		super();
		
		this.numeromesa = numromesa;
		this.numerosillas = numrosillas;
	}
	
	public Mesa() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public int getNumeromesa() {
		return numeromesa;
	}
	public void setNumeromesa(int numeromesa) {
		this.numeromesa = numeromesa;
	}
	public int getNumerosillas() {
		return numerosillas;
	}
	public void setNumerosillas(int numerosillas) {
		this.numerosillas = numerosillas;
	}
	public String toString(){
		
		return this.numeromesa+""; 
	}
	
	
}

package dip;

// Class niveau inf�rieur
public class Book implements Product{

	@Override
	public void seeReviews() {
		System.out.println("See Review - Critique livre");
	}
	
	@Override
	public void getSample() {
		System.out.println("Read Sample - Lire un �chantillon");
	}
}

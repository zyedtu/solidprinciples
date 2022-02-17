package dip;

// Class niveau inférieur
public class Book implements Product{

	@Override
	public void seeReviews() {
		System.out.println("See Review - Critique livre");
	}
	
	@Override
	public void getSample() {
		System.out.println("Read Sample - Lire un échantillon");
	}
}

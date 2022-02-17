					# S.O.L.I.D PRINCIPLES #
					
# Le Principe De Responsabilit� Unique - Single Responsibility Principle (SRP):   
Ce principe dit qu'il ne devrait jamais y avoir plus d'une raison pour qu'une classe change. Une classe doit �tre ax�e sur **une seule fonctionnalit�**, r�pondre � une pr�occupation sp�cifique. Cela signifie que chaque classe, ou structure similaire, dans votre code ne doit avoir qu'un seul travail � faire. Tout dans la classe doit �tre li� � cet objectif unique, c'est-�-dire �tre coh�rent. Cela ne signifie pas que vos classes ne doivent contenir qu'une seule m�thode ou propri�t�.     

Vous pouvez �viter ces probl�mes en posant une question simple avant d'apporter des modifications, Quelle est la responsabilit� de votre classe/composant/microservice ?   
Si votre r�ponse inclut le mot "et", vous viiler tr�s probablement le principe de responsabilit� unique.    
### Exemple qui viole le principe de Responsabilit� Unique:   
Prenons cette classe employ�:  

		public class Employee {
		
			public Double calculatePay() {
				// calculate pay employee
				return 0.0;
			}
		
		    public void saveEmployee() {
		    	// save employee
		    }
		
		    public void getEmployeeReport() {
		    	// fetch report employee
		    }
		}
Combien de responsabilit�s dans cette classe ?     
La bonne r�ponse est **trois**.   
Ici, nous avons une logique de **calcul**, une logique de **base de donn�es** et une logique de **rapport**. Tout m�lang� dans une m�me classe.      
Si vous avez plusieurs responsabilit�s combin�es en une seule classe, il peut �tre difficile de changer une partie sans en casser d'autres.     
Le m�lange des responsabilit�s rend �galement la classe plus difficile � comprendre et plus difficile � tester.     
### Solution:   
Le moyen le plus simple de r�soudre ce probl�me consiste � diviser la classe en trois classes diff�rentes, chacune n'ayant qu'une seule responsabilit� : l'acc�s � la base de donn�es, le calcul du paiement et la cr�ation de rapports, tous s�par�s.   

		public class EmployeePayment {
			public Double calculatePay() {
				// logic to calculate employee payment
				return 0.0;
			}
		}

		public class EmployeeRepository {
			public void saveEmployee() {
				// logic to save employee object
			}
		}

		public class EmployeeReport {
			public void getEmployeeReport() {
				// logic to generate employee report
			}
		}
### Un exemple de vie r�elle:    
Vous pouvez trouver de nombreux exemples de Responsabilit� Unique, en l'occurrence **L'  interface EntityManager** de JPA, fournit un ensemble de m�thodes pour enregistrer, mettre � jour, supprimer et lire des entit�s d'une base de donn�es relationnelle. Sa responsabilit� est de g�rer les entit�s qui sont associ�es au contexte de persistance actuel.          
### avantage du principe de responsabilit� unique:  
# Le Principe Ouvert Ferm� - The Open Closed Principle (OCP):   
Le principe dit: Les composants logiciels doivent �tre ouverts � l'extension, mais ferm�s � la modification �. En termes simples, les composants logiciels tels que les classes, les modules et les fonctions doivent �tre ouverts aux extensions mais ferm�s aux modifications.       
Bien s�r, la seule exception � la r�gle concerne la correction de bogues dans le code existant. Donc, nous ne devrions modifier notre classe qu'au moment de la correction des bogues.      
Il existe plusieurs mani�re pour �tendre une classe:   
* H�ritage de la classe.    
* Red�finir les comportements requis de la classe.   
* Extension de certains comportements de la classe.    

### Exemple qui viole le Principe Ouvert Ferm� OCP:    
Supposons que nous devions �crire un programme qui calcule l'aire de diff�rentes formes. Nous commen�ons par cr�er une classe pour notre premi�re forme, disons Rectangle qui a 2 attributs longueur et largeur.    
Ensuite, nous cr�ons une classe pour calculer la surface de ce Rectangle qui a une m�thode calculateRectangleArea() qui prend le Rectangle comme param�tre d'entr�e:   

		public class AreaCalculator {
			public Double calculateRectangleArea(Rectangle rectangle) {
				return rectangle.length * rectangle.width;
			}
		}
Jusqu'ici tout va bien. Plutard, nous devions �crire un programme pour notre deuxi�me forme qui est un cercle. Ainsi, nous cr�ons rapidement une nouvelle classe Circle avec un rayon d'attribut unique.   

		public class Circle {
			public Double radius;
		}
Ensuite, nous modifions la classe AreaCalculator pour ajouter des calculs de cercle via une nouvelle m�thode calculateCircleArea().    

	public class AreaCalculator {
		public Double calculateRectangleArea(Rectangle rectangle) {
			return rectangle.length * rectangle.width;
		}
		
		public Double calculateCircleArea(Circle circle) {
			return 3.14 * circle.radius * circle.radius;
		}
	}
Cependant, veuillez noter qu'il existe **des d�fauts** dans la conception de notre solution ci-dessus.    
Au fur et � mesure que les types de formes augmentent, cela devient plus compliqu� car la classe *AreaCalculator* continue de changer, Ainsi, cette conception **n'est pas ferm�e** pour *modification*.     
### La solution:   
Voyons maintenant une conception plus �l�gante qui r�sout les d�fauts de la conception ci-dessus en adh�rant au principe ouvert/ferm�.     
Tout d'abord, nous allons rendre le design extensible, pour cela on va d�finir un type *Shape* et fair en sorte que *Rectangle* et *Circle* impl�mente cette interface.    

		public class Rectangle implements Shape{
			public Double length;
		    public Double width;
		    
			@Override
			public Double calculateArea() {
				return length * width;
			}
		}
la classe Circle:

		public class Circle implements Shape{
			public Double radius;
		
			@Override
			public Double calculateArea() {
				return 3.14 * radius * radius;
			}
		}
Comme mentionn� ci-dessus, il existe une interface de base Shape. Toutes les formes impl�mentent d�sormais l'interface de base Shape.      
Nous avons apport� un certain **degr� d'extensibilit�** car les formes sont d�sormais une instance des interfaces Shape. Cela nous permet d'utiliser Shape au lieu de classes individuelles.    
Le dernier point est le *consommateur* de ces formes. Le consommateur sera la classe AreaCalculator qui ressemblera maintenant � ceci:    

		public class AreaCalculator {
			public Double calculateShapeArea(Shape shape) {
				return shape.calculateArea();
			}
		}
Cette classe AreaCalculator supprime maintenant compl�tement nos d�fauts de conception not�s ci-dessus et fournit une solution propre qui adh�re au principe ouvert-ferm�.    
### Les avantages du principe ouvert ferm�:    
* Extensibilit� plus facile.       
* Plus facile � entretenir.  
* La flexibilit�.  

# Le Principe De Substitution De Liskov - Liskov Substitution Principle (LSP):    
le principe de substitution de Liskov en terme simple, si la classe  A  est un sous-type de la classe  B , alors nous devrions pouvoir remplacer les objets de B  par des objets de A (c'est-�-dire que les objets de type A peuvent remplacer les objets de type B)  sans changer le comportement (correction, fonctionnalit�, etc.) de notre programme.   
LSP s'applique aux hi�rarchies d'h�ritage, toutes les sous-classes doivent donc fonctionner de la m�me mani�re que leurs classes de base **Les types d�riv�s doivent �tre compl�tement substituables � leurs types de base**. 		   
### Exemple qui viole le principe De Substitution De Liskov LSP:    
On cr�e une classe Abstraite *Account*:  

	public abstract class Account {
		protected abstract void deposit(BigDecimal amount);
		protected abstract void withdraw(BigDecimal amount);
	}
Cette classe dispose de deux m�thode: 
* deposit(): pour le **d�p�t** dans une compte.
* withdraw(): pour **retrait** de compte ( retirer un solde du compte du montant sp�cifi� � condition que le montant > 0)       

Admettant qu'on a un service *BankingAppWithdrawalService* qui fait appel a la classe *Account*:     

	public class BankingAppWithdrawalService {
	
		private Account account;
	
	    public BankingAppWithdrawalService(Account account) {
	        this.account = account;
	    }
	
	    public void withdraw(BigDecimal amount) {
	        account.withdraw(amount);
	    }
	} 
D�sormais, la banque souhaite proposer � ses clients un compte de d�p�t � terme � taux d'int�r�t �lev� avec condition que le client ne peut pas **retirer** de ce compte.   
Dans notre concepte orient�e objet, ce nouveau compte de d�p�t � terme *FixedTermDepositAccount* h�rite du compte *Account*.      

	public class FixedTermDepositAccount extends Account {
	
		@Override
		protected void deposit(BigDecimal amount) {
			// Deposit into this account
	
		}
	
		@Override
		protected void withdraw(BigDecimal amount) {
			throw new UnsupportedOperationException("Withdrawals are not supported by "
					+ "FixedTermDepositAccount!!");
		}
	}	
Maintenant on va tester notre service avec la classe **IspMain**:   

	public class IspMain {
	
		public static void main(String[] args) {
			Account myFixedTermDepositAccount = new FixedTermDepositAccount();
			myFixedTermDepositAccount.deposit(new BigDecimal(1000.00));
	
			BankingAppWithdrawalService withdrawalService = new BankingAppWithdrawalService(myFixedTermDepositAccount);
			withdrawalService.withdraw(new BigDecimal(100.00));
	
		}
	}
Sans surprise, l'application bancaire plante avec l'erreur :

	java.lang.UnsupportedOperationException: Withdrawals are not supported by FixedTermDepositAccount!!

Qu'est ce qui ne s'est pas bien pass� ?         
En fait, *BankingAppWithdrawalService* est une service d'un client de la classe *Account*.  Il s'attend � ce que Account et ses sous-types garantissent le comportement que la classe Account a sp�cifi� pour sa m�thode de retrait. En revanche la sous-classe *FixedTermDepositAccount* ne prend pas en charge la m�thode *de retrait (withdraw)*.     
Par cons�quent, nous ne pouvons pas remplacer de mani�re fiable FixedTermDepositAccount par Account.     
En d'autres termes, le FixedTermDepositAccount a **viol�** le principe de substitution de Liskov.    
### La solution:     
D'apr�s *Robert C. Martin* Les sous-types doivent �tre substituables � leurs types de base.   
Un sous-type ne devient pas automatiquement substituable � son sur-type. Pour �tre substituable, le sous-type doit **se comporter comme son supertype**.     
Donc pour trouver la solution, il doit commen�er par comprendre la cause premi�re.     
Dans notre exemple, FixedTermDepositAccount **n'�tait pas un sous-type comportemental** de Account.       
La conception du compte suppose � tort que tous les types de compte autorisent les retraits. Par cons�quent, tous les sous-types de compte,  y compris FixedTermDepositAccount qui ne prend pas en charge les retraits, ont h�rit� de la m�thode de retrait .       
Pour cela on fait un refactor de la classe Account, avec une seule m�thode *deposit*   

	public abstract class Account {
		protected abstract void deposit(BigDecimal amount);
	}
On cr�e une classe qui implement la m�thode de retrait et h�rite de la classe Account:   

	public class WithdrawableAccount extends Account {
	
		@Override
		protected void deposit(BigDecimal amount) {
			this.amount = amount;
		}
	
		protected void withdraw(BigDecimal amount) {
			if(this.amount.compareTo(amount) >= 0 )
				this.amount.subtract(amount);
			else
				throw new RuntimeException("Solde du compte est insuffusant!");
		}
	}
Ensuite on refactor notre service avec retrait *BankingAppWithdrawalService* comme ci-dessous:    

	public class BankingAppWithdrawalService {
	
		private WithdrawableAccount withdrawableAccount;
	
	    public BankingAppWithdrawalService(WithdrawableAccount withdrawableAccount) {
	        this.withdrawableAccount = withdrawableAccount;
	    }
	
	    public void withdraw(BigDecimal amount) {
	    	withdrawableAccount.withdraw(amount);
	    }
	}   
Ex�cutant maintenant notre classe main avec l'ajout de notre nouvelle classe *WithdrawableAccount*:   

	public class IspMain {
	
		public static void main(String[] args) {
			WithdrawableAccount myFixedTermDepositAccount = new WithdrawableAccount();
			myFixedTermDepositAccount.deposit(new BigDecimal(1000.00));
	
			BankingAppWithdrawalService withdrawalService = new BankingAppWithdrawalService(myFixedTermDepositAccount);
			withdrawalService.withdraw(new BigDecimal(100.00));
	
		}
	}
tout est **ok**.     
### Les avantages du principe de substitution de Liskov (LSP):  
* R�utilisabilit� du code
* Maintenance simplifi�e
* Couplage r�duit

# Le Principe De S�gr�gation D'interface - Interface Segregation Principle (ISP):   
Le principe de s�gr�gation des interfaces stipule que *les clients ne doivent pas �tre contraints de d�pendre d'interfaces qu'ils n'utilisent pas*.       
En bref, il serait mauvais pour vous de forcer le client � d�pendre d'une certaine chose, dont il n'a pas besoin.     
### Exemple qui viole le principe De S�gr�gation D'interface ISP:   
Supposons qu'il existe une interface de restaurant qui contient des m�thodes pour accepter les commandes des clients en ligne, des clients par t�l�phone et des clients sans rendez-vous.  Il contient �galement des m�thodes de gestion des paiements en ligne (pour les clients en ligne) et des paiements en personne. Les paiements en personne concernent les clients sans rendez-vous ainsi que les clients par t�l�phone. De plus, les clients par t�l�phone paient en personne au moment de la livraison de la commande.      

		public interface RestaurantInterface {
			public void acceptOnlineOrder();
			public void acceptTelephoneOrder();
			public void acceptWalkInCustomerOrder();
			public void payOnline();
			public void payInPerson();
		}  
 Dans cette interface, nous avons 5 m�rhodes Ils servent � accepter une commande en ligne, � prendre une commande par t�l�phone, � accepter les commandes d'un client sans rendez-vous afin de passer la commande. De m�me, accepter le paiement en ligne et accepter le paiement en personne afin d'effectuer les paiements.     
Impl�mentant maintenant une classe pour les client en ligne:    

		public class OnlineCustomerImpl implements RestaurantInterface {
		
			@Override
			public void acceptOnlineOrder() {
				// logic for placing online order
			}
		
			@Override
			public void acceptTelephoneOrder() {
				throw new UnsupportedOperationException();
			}
		
			@Override
			public void acceptWalkInCustomerOrder() {
				throw new UnsupportedOperationException();
			}
		
			@Override
			public void payOnline() {
				// logic for paying online
			}
		
			@Override
			public void payInPerson() {
				throw new UnsupportedOperationException();
			}
		}
Cette classe est  destin� aux clients en ligne, nous devrons lever UnsupportedOperationException pour les m�thodes qui ne s'appliquent pas aux clients en ligne. Ceci est �galement appel� **pollution d'interface**. Ici, nous pouvons observer une **violation** claire du principe de s�gr�gation d'interface(ISP).     
### La solution:     
Comment surmonter ce probl�me ? nous appliquerons le principe de s�gr�gation d'interface **ISP** pour refactoriser la conception ci-dessus.   

* S�parer les fonctionnalit�es de payement et le passage d'une commande en deux interfaces diff�rents:      

		public interface OrderInterface {
			public void placeOrder();
		}
		
		public interface PaymentInterface {
			public void payForOrder();
		} 

* Chaque client va maintenant impl�menter les deux interfaces comme ci-dessous:    

	public class OnlineCustomerImpl implements OrderInterface, PaymentInterface {
	
		@Override
		public void payForOrder() {
			// logic for paying online
		}
	
		@Override
		public void placeOrder() {
			// logic for placing online order
		}
	}
	
	public class TelephoneCustomerImpl implements OrderInterface, PaymentInterface{
	
		@Override
		public void payForOrder() {
			// logic to do telephonic payment  
		}
	
		@Override
		public void placeOrder() {
			// logic to place telephonic order
		}
	}
### Quelle est la similitude entre ISP et SRP:      
Le principe de s�gr�gation d'interface et le principe de responsabilit� unique ont � peu pr�s le m�me objectif : garantir des composants logiciels petits, cibl�s et hautement coh�rents. La diff�rence est que le principe de responsabilit� unique *SRP* concerne les **classes**, tandis que le principe de s�paration des interfaces *ISP* concerne les **interfaces**.    
### les avantages du principe de s�gr�gation d'interface:      
* Meilleure lisibilit� du code.    
* Plus facile � mettre en �uvre.    
* Plus facile � maintenir.    
* Meilleure organisation du code.   
* Pas besoin de lever des exceptions inutilement.     
              
# Principe d'inversion de d�pendance - Dependency Inversion Principle (DIP):
Les modules de haut niveau ne doivent pas d�pendre des modules de bas niveau. Les deux devraient d�pendre d'abstractions.  

### Exemple qui viole le principe inversion de d�pendance:
On cr�e une classe de niveau inf�rieur **(lower-level)** qui permet aux utilisateurs de voir des critiques et de lire un �chantillon de chaque livre.

		public class Book {
		
			void seeReviews() {
				System.out.println("See Review - Critique livre");
			}
			
			void readSample() {
				System.out.println("Read Sample - Lire un �chantillon");
			}
		}
Maintenant on cr�e une classe de niveau sup�rieur **(higher-level)**, qui permet d'ajouter un livre � leur �tag�re et de personnaliser l'�tag�re:   

		public class Shelf {
		
			Book book;
			
			void addBook(Book book) {
				System.out.println("Add Book - Ajouter un livre � leur �tag�re");
			}
			
			void customizeShelf() {
				System.out.println("CustomizeShelf - personnaliser l'�tag�re");
		   }
		}

Tout semble correct, mais comme la classe **Shelf** de *haut niveau* **d�pend du Book** de *bas niveau*, le code ci-dessus viole le principe d'inversion des d�pendances. Cela devient clair lorsque le magasin nous demande de permettre aux clients d'ajouter �galement des DVD � leurs rayons. Afin de r�pondre � la demande, nous cr�ons une nouvelle classe de DVD.     

		public class DVD {
		
			void seeReviews() {
				System.out.println("See Review - Critique DVD");
			}
		
			void watchSample() { 
				System.out.println("Watch Sample - Critique DVD");
		   }
		}
Ensuite, on doit modifier la classe Shelf afin qu'elle puisse accepter les DVD, Cependant, cela enfreindrait clairement le principe ouvert/ferm� �galement.    

### La solution:
la solution est de cr�er une couche d'abstraction pour les classes de niveau inf�rieur (Book et DVD).     
Nous le ferons en introduisant l'interface Product, les deux classes l'impl�menteront:     

		public interface Product {
		
			void seeReviews();
		
			void getSample();
		}
la classe book impl�ment Product:   

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
Ainsi que la classe DVD:  

	public class DVD implements Product{
	
		@Override
		public void seeReviews() {
			System.out.println("See Review - Critique DVD");
		}
	
		@Override
		public void getSample() { 
			System.out.println("Watch Sample - Critique DVD");
	   }
	}
Maintenant, Shelf peut r�f�rencer l'interface Product au lieu de ses impl�mentations (Book et DVD). Le code refactoris� nous permet �galement d'introduire ult�rieurement de nouveaux types de produits (par exemple, Magazine) que les clients peuvent �galement mettre sur leurs �tag�res.    

		public class Shelf {
		
			Product product;
			
			void addBook(Product product) {
				System.out.println("Add product - Ajouter un produit � leur �tag�re");
			}
			
			void customizeShelf() {
				System.out.println("CustomizeShelf - personnaliser l'�tag�re");
		   }
		}
### Les avantages du principe d'inversion de d�pendance:   
* Maintient votre code faiblement coupl�.     
* Maintenance plus facile.     
* Meilleure r�utilisabilit� du code.     
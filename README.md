					# S.O.L.I.D PRINCIPLES #

Dans cet article, nous discuterons des **principes solides**, les principes SOLID sont l'ensemble des cinq principes utilisés pour concevoir un logiciel. En fait, le mot **SOLIDE** est l'acronyme de l'ensemble de cinq principes qui contient la première lettre de chaque principe.   
Les principes de conception aident les équipes à prendre des décisions, et avoir un produit beau.   
En respectant les principes SOLID, on évite les odeurs de code, avoir un code propre et maintenable et facile à tester.

![Alt text](https://github.com/zyedtu/solidprinciples/blob/master/SOLID_Principles.jpg?raw=true "Title")

# Le Principe De Responsabilité Unique - Single Responsibility Principle (SRP):   
Ce principe dit qu'il ne devrait jamais y avoir plus d'une raison pour qu'une classe change. Une classe doit être axée sur **une seule fonctionnalité**, répondre à une préoccupation spécifique. Cela signifie que chaque classe, ou structure similaire, dans votre code ne doit avoir qu'un seul travail à faire. Tout dans la classe doit être lié à cet objectif unique, c'est-à-dire être cohérent. Cela ne signifie pas que vos classes ne doivent contenir qu'une seule méthode ou propriété.     

Vous pouvez éviter ces problèmes en posant une question simple avant d'apporter des modifications, Quelle est la responsabilité de votre classe/composant/microservice ?   
Si votre réponse inclut le mot "et", vous viiler très probablement le principe de responsabilité unique.    
### Exemple qui viole le principe de Responsabilité Unique:   
Prenons cette classe employé:  

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
Combien de responsabilités dans cette classe ?     
La bonne réponse est **trois**.   
Ici, nous avons une logique de **calcul**, une logique de **base de données** et une logique de **rapport**. Tout mélangé dans une même classe.      
Si vous avez plusieurs responsabilités combinées en une seule classe, il peut être difficile de changer une partie sans en casser d'autres.     
Le mélange des responsabilités rend également la classe plus difficile à comprendre et plus difficile à tester.     
### Solution:   
Le moyen le plus simple de résoudre ce problème consiste à diviser la classe en trois classes différentes, chacune n'ayant qu'une seule responsabilité : l'accès à la base de données, le calcul du paiement et la création de rapports, tous séparés.   

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
### Un exemple de vie réelle:    
Vous pouvez trouver de nombreux exemples de Responsabilité Unique, en l'occurrence **L'  interface EntityManager** de JPA, fournit un ensemble de méthodes pour enregistrer, mettre à jour, supprimer et lire des entités d'une base de données relationnelle. Sa responsabilité est de gérer les entités qui sont associées au contexte de persistance actuel.          
### avantage du principe de responsabilité unique:  
# Le Principe Ouvert Fermé - The Open Closed Principle (OCP):   
Le principe dit: Les composants logiciels doivent être ouverts à l'extension, mais fermés à la modification ». En termes simples, les composants logiciels tels que les classes, les modules et les fonctions doivent être ouverts aux extensions mais fermés aux modifications.       
Bien sûr, la seule exception à la règle concerne la correction de bogues dans le code existant. Donc, nous ne devrions modifier notre classe qu'au moment de la correction des bogues.      
Il existe plusieurs manière pour étendre une classe:   
* Héritage de la classe.    
* Redéfinir les comportements requis de la classe.   
* Extension de certains comportements de la classe.    

### Exemple qui viole le Principe Ouvert Fermé OCP:    
Supposons que nous devions écrire un programme qui calcule l'aire de différentes formes. Nous commençons par créer une classe pour notre première forme, disons Rectangle qui a 2 attributs longueur et largeur.    
Ensuite, nous créons une classe pour calculer la surface de ce Rectangle qui a une méthode calculateRectangleArea() qui prend le Rectangle comme paramètre d'entrée:   

		public class AreaCalculator {
			public Double calculateRectangleArea(Rectangle rectangle) {
				return rectangle.length * rectangle.width;
			}
		}
Jusqu'ici tout va bien. Plutard, nous devions écrire un programme pour notre deuxième forme qui est un cercle. Ainsi, nous créons rapidement une nouvelle classe Circle avec un rayon d'attribut unique.   

		public class Circle {
			public Double radius;
		}
Ensuite, nous modifions la classe AreaCalculator pour ajouter des calculs de cercle via une nouvelle méthode calculateCircleArea().    

	public class AreaCalculator {
		public Double calculateRectangleArea(Rectangle rectangle) {
			return rectangle.length * rectangle.width;
		}
		
		public Double calculateCircleArea(Circle circle) {
			return 3.14 * circle.radius * circle.radius;
		}
	}
Cependant, veuillez noter qu'il existe **des défauts** dans la conception de notre solution ci-dessus.    
Au fur et à mesure que les types de formes augmentent, cela devient plus compliqué car la classe *AreaCalculator* continue de changer, Ainsi, cette conception **n'est pas fermée** pour *modification*.     
### La solution:   
Voyons maintenant une conception plus élégante qui résout les défauts de la conception ci-dessus en adhérant au principe ouvert/fermé.     
Tout d'abord, nous allons rendre le design extensible, pour cela on va définir un type *Shape* et fair en sorte que *Rectangle* et *Circle* implémente cette interface.    

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
Comme mentionné ci-dessus, il existe une interface de base Shape. Toutes les formes implémentent désormais l'interface de base Shape.      
Nous avons apporté un certain **degré d'extensibilité** car les formes sont désormais une instance des interfaces Shape. Cela nous permet d'utiliser Shape au lieu de classes individuelles.    
Le dernier point est le *consommateur* de ces formes. Le consommateur sera la classe AreaCalculator qui ressemblera maintenant à ceci:    

		public class AreaCalculator {
			public Double calculateShapeArea(Shape shape) {
				return shape.calculateArea();
			}
		}
Cette classe AreaCalculator supprime maintenant complètement nos défauts de conception notés ci-dessus et fournit une solution propre qui adhère au principe ouvert-fermé.    
### Les avantages du principe ouvert fermé:    
* Extensibilité plus facile.       
* Plus facile à entretenir.  
* La flexibilité.  

# Le Principe De Substitution De Liskov - Liskov Substitution Principle (LSP):    
le principe de substitution de Liskov en terme simple, si la classe  A  est un sous-type de la classe  B , alors nous devrions pouvoir remplacer les objets de B  par des objets de A (c'est-à-dire que les objets de type A peuvent remplacer les objets de type B)  sans changer le comportement (correction, fonctionnalité, etc.) de notre programme.   
LSP s'applique aux hiérarchies d'héritage, toutes les sous-classes doivent donc fonctionner de la même manière que leurs classes de base **Les types dérivés doivent être complètement substituables à leurs types de base**. 		   
### Exemple qui viole le principe De Substitution De Liskov LSP:    
On crée une classe Abstraite *Account*:  

	public abstract class Account {
		protected abstract void deposit(BigDecimal amount);
		protected abstract void withdraw(BigDecimal amount);
	}
Cette classe dispose de deux méthode: 
* deposit(): pour le **dépôt** dans une compte.
* withdraw(): pour **retrait** de compte ( retirer un solde du compte du montant spécifié à condition que le montant > 0)       

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
Désormais, la banque souhaite proposer à ses clients un compte de dépôt à terme à taux d'intérêt élevé avec condition que le client ne peut pas **retirer** de ce compte.   
Dans notre concepte orientée objet, ce nouveau compte de dépôt à terme *FixedTermDepositAccount* hérite du compte *Account*.      

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

Qu'est ce qui ne s'est pas bien passé ?         
En fait, *BankingAppWithdrawalService* est une service d'un client de la classe *Account*.  Il s'attend à ce que Account et ses sous-types garantissent le comportement que la classe Account a spécifié pour sa méthode de retrait. En revanche la sous-classe *FixedTermDepositAccount* ne prend pas en charge la méthode *de retrait (withdraw)*.     
Par conséquent, nous ne pouvons pas remplacer de manière fiable FixedTermDepositAccount par Account.     
En d'autres termes, le FixedTermDepositAccount a **violé** le principe de substitution de Liskov.    
### La solution:     
D'après *Robert C. Martin* Les sous-types doivent être substituables à leurs types de base.   
Un sous-type ne devient pas automatiquement substituable à son sur-type. Pour être substituable, le sous-type doit **se comporter comme son supertype**.     
Donc pour trouver la solution, il doit commençer par comprendre la cause première.     
Dans notre exemple, FixedTermDepositAccount **n'était pas un sous-type comportemental** de Account.       
La conception du compte suppose à tort que tous les types de compte autorisent les retraits. Par conséquent, tous les sous-types de compte,  y compris FixedTermDepositAccount qui ne prend pas en charge les retraits, ont hérité de la méthode de retrait .       
Pour cela on fait un refactor de la classe Account, avec une seule méthode *deposit*   

	public abstract class Account {
		protected abstract void deposit(BigDecimal amount);
	}
On crée une classe qui implement la méthode de retrait et hérite de la classe Account:   

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
Exécutant maintenant notre classe main avec l'ajout de notre nouvelle classe *WithdrawableAccount*:   

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
* Réutilisabilité du code
* Maintenance simplifiée
* Couplage réduit

# Le Principe De Ségrégation D'interface - Interface Segregation Principle (ISP):   
Le principe de ségrégation des interfaces stipule que *les clients ne doivent pas être contraints de dépendre d'interfaces qu'ils n'utilisent pas*.       
En bref, il serait mauvais pour vous de forcer le client à dépendre d'une certaine chose, dont il n'a pas besoin.     
### Exemple qui viole le principe De Ségrégation D'interface ISP:   
Supposons qu'il existe une interface de restaurant qui contient des méthodes pour accepter les commandes des clients en ligne, des clients par téléphone et des clients sans rendez-vous.  Il contient également des méthodes de gestion des paiements en ligne (pour les clients en ligne) et des paiements en personne. Les paiements en personne concernent les clients sans rendez-vous ainsi que les clients par téléphone. De plus, les clients par téléphone paient en personne au moment de la livraison de la commande.      

		public interface RestaurantInterface {
			public void acceptOnlineOrder();
			public void acceptTelephoneOrder();
			public void acceptWalkInCustomerOrder();
			public void payOnline();
			public void payInPerson();
		}  
 Dans cette interface, nous avons 5 mérhodes Ils servent à accepter une commande en ligne, à prendre une commande par téléphone, à accepter les commandes d'un client sans rendez-vous afin de passer la commande. De même, accepter le paiement en ligne et accepter le paiement en personne afin d'effectuer les paiements.     
Implémentant maintenant une classe pour les client en ligne:    

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
Cette classe est  destiné aux clients en ligne, nous devrons lever UnsupportedOperationException pour les méthodes qui ne s'appliquent pas aux clients en ligne. Ceci est également appelé **pollution d'interface**. Ici, nous pouvons observer une **violation** claire du principe de ségrégation d'interface(ISP).     
### La solution:     
Comment surmonter ce problème ? nous appliquerons le principe de ségrégation d'interface **ISP** pour refactoriser la conception ci-dessus.   

* Séparer les fonctionnalitées de payement et le passage d'une commande en deux interfaces différents:      

		public interface OrderInterface {
			public void placeOrder();
		}
		
		public interface PaymentInterface {
			public void payForOrder();
		} 

* Chaque client va maintenant implémenter les deux interfaces comme ci-dessous:    

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
Le principe de ségrégation d'interface et le principe de responsabilité unique ont à peu près le même objectif : garantir des composants logiciels petits, ciblés et hautement cohérents. La différence est que le principe de responsabilité unique *SRP* concerne les **classes**, tandis que le principe de séparation des interfaces *ISP* concerne les **interfaces**.    
### les avantages du principe de ségrégation d'interface:      
* Meilleure lisibilité du code.    
* Plus facile à mettre en uvre.    
* Plus facile à maintenir.    
* Meilleure organisation du code.   
* Pas besoin de lever des exceptions inutilement.     
              
# Principe d'inversion de dépendance - Dependency Inversion Principle (DIP):
Les modules de haut niveau ne doivent pas dépendre des modules de bas niveau. Les deux devraient dépendre d'abstractions.  

### Exemple qui viole le principe inversion de dépendance:
On crée une classe de niveau inférieur **(lower-level)** qui permet aux utilisateurs de voir des critiques et de lire un échantillon de chaque livre.

		public class Book {
		
			void seeReviews() {
				System.out.println("See Review - Critique livre");
			}
			
			void readSample() {
				System.out.println("Read Sample - Lire un échantillon");
			}
		}
Maintenant on crée une classe de niveau supérieur **(higher-level)**, qui permet d'ajouter un livre à leur étagère et de personnaliser l'étagère:   

		public class Shelf {
		
			Book book;
			
			void addBook(Book book) {
				System.out.println("Add Book - Ajouter un livre à leur étagère");
			}
			
			void customizeShelf() {
				System.out.println("CustomizeShelf - personnaliser l'étagère");
		   }
		}

Tout semble correct, mais comme la classe **Shelf** de *haut niveau* **dépend du Book** de *bas niveau*, le code ci-dessus viole le principe d'inversion des dépendances. Cela devient clair lorsque le magasin nous demande de permettre aux clients d'ajouter également des DVD à leurs rayons. Afin de répondre à la demande, nous créons une nouvelle classe de DVD.     

		public class DVD {
		
			void seeReviews() {
				System.out.println("See Review - Critique DVD");
			}
		
			void watchSample() { 
				System.out.println("Watch Sample - Critique DVD");
		   }
		}
Ensuite, on doit modifier la classe Shelf afin qu'elle puisse accepter les DVD, Cependant, cela enfreindrait clairement le principe ouvert/fermé également.    

### La solution:
la solution est de créer une couche d'abstraction pour les classes de niveau inférieur (Book et DVD).     
Nous le ferons en introduisant l'interface Product, les deux classes l'implémenteront:     

		public interface Product {
		
			void seeReviews();
		
			void getSample();
		}
la classe book implément Product:   

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
Maintenant, Shelf peut référencer l'interface Product au lieu de ses implémentations (Book et DVD). Le code refactorisé nous permet également d'introduire ultérieurement de nouveaux types de produits (par exemple, Magazine) que les clients peuvent également mettre sur leurs étagères.    

		public class Shelf {
		
			Product product;
			
			void addBook(Product product) {
				System.out.println("Add product - Ajouter un produit à leur étagère");
			}
			
			void customizeShelf() {
				System.out.println("CustomizeShelf - personnaliser l'étagère");
		   }
		}
### Les avantages du principe d'inversion de dépendance:   
* Maintient votre code faiblement couplé.     
* Maintenance plus facile.     
* Meilleure réutilisabilité du code.     

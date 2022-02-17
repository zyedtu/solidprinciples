package isp;

public class OnlineCustomerImpl implements OrderInterface, PaymentInterface {

	@Override
	public void payForOrder() {
		// logic for paying online
	}

	@Override
	public void placeOrder() {
		// logic for placing online order
	}

//	@Override
//	public void acceptOnlineOrder() {
//		// logic for placing online order
//	}
//
//	@Override
//	public void acceptTelephoneOrder() {
//		throw new UnsupportedOperationException();
//	}
//
//	@Override
//	public void acceptWalkInCustomerOrder() {
//		throw new UnsupportedOperationException();
//	}
//
//	@Override
//	public void payOnline() {
//		// logic for paying online
//	}
//
//	@Override
//	public void payInPerson() {
//		throw new UnsupportedOperationException();
//	}

}

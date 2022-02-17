package lsp;

import java.math.BigDecimal;

public class IspMain {

	public static void main(String[] args) {
		WithdrawableAccount myFixedTermDepositAccount = new WithdrawableAccount();
		myFixedTermDepositAccount.deposit(new BigDecimal(1000.00));

		BankingAppWithdrawalService withdrawalService = new BankingAppWithdrawalService(myFixedTermDepositAccount);
		withdrawalService.withdraw(new BigDecimal(100.00));

	}

}

package edu.unq.desapp.groupA.backend.model;

public enum PaymentType {

	CANCELED {
		@Override
		public String paymentName() {
			return "Canceled";
		}
	}, HOME_DELIVERY {
		@Override
		public String paymentName() {
			return "Requested for Home Delivery";
		}
	}, CASH_REGISTER {
		@Override
		public String paymentName() {
			return "Paid at Cash Register";
		}
	};

	public abstract String paymentName();
}

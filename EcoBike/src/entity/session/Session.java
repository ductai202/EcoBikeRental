package entity.session;


import entity.Bike;
import entity.Station;
import entity.User;
import entity.payment.PaymentTransaction;

/**
 * @author ADMIN phiên người dùng bao gồm thông tin xe và bãi xe người dùng đã
 *         chọn
 *
 */
public class Session {

	private Station station;
	private Bike bike;
	private User user;
	private PaymentTransaction paymentTransaction;
	private static Session sessionInstance;

	public static Session getSession() {
		if (sessionInstance == null)
			sessionInstance = new Session();
		return sessionInstance;
	}

	public static void setSessionInstance(Session sessionInstance) {
		Session.sessionInstance = sessionInstance;
	}

	private Session() {
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Session [station=" + station + ", bike=" + bike + ", user=" + user + ", paymentTransaction="
				+ paymentTransaction + "]";
	}

	public void emptySession() {
		Session.getSession().setBike(null);
		Session.getSession().setStation(null);
		Session.getSession().setUser(null);
	}

}

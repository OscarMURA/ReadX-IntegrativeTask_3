public abstract class User {

	private String name;
	private String ID;
	private Calendar dateLinkage;

	/**
	 * 
	 * @param name
	 * @param id
	 * @param dateLinkage
	 */
	public User(String name, String id, Calendar dateLinkage) {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param hexa
	 */
	public abstract String addProduct(String name, String hexa);

}
package data;
public class Company {

		private String companyName;
		private Address companyAddress;
		
		
		public Company(String companyName, String country, String city, String street, int number){
			companyAddress = new Address(country, city, street, number);
			this.companyName = companyName;
		}
		
		public String getName() {
			return companyName;
		}
		
		public Address getCompanyAddress(){
			return companyAddress;
		}
}

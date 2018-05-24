public class TestCode {

    public TestCode(){
        testCustomer();
        testValidator();
        testStringUtils();
        testCustomerMaintApp();
    }

    public void testCustomer(){
        System.out.println("\nStart Customer test.");

        Customer testCustomer = new Customer();
        System.out.printf("Email Address : %s \n First Name : %s \n Last Name : %s \n",
                testCustomer.getEmailAddress(), testCustomer.getFirstName(), testCustomer.getLastName());
        testCustomer.setEmailAddress("gcooper4@nwacc.edu");
        testCustomer.setFirstName("Garrett");
        testCustomer.setLastName("Cooper");
        System.out.printf("Email Address : %s \n First Name : %s \n Last Name : %s \n",
                testCustomer.getEmailAddress(), testCustomer.getFirstName(), testCustomer.getLastName());

        System.out.println("End Customer test.");
    }

    public void testCustomerMaintApp(){
        System.out.println("\nStart CustomerMaintApp test.");

        CustomerMaintApp testApp = new CustomerMaintApp();

        System.out.println("End CustomerMaintApp test.");

    }

    public void testValidator(){

        System.out.println("\nStart Validator test.");

        System.out.printf("Enter a string : ");
        Validator.stringValidator();
        int min = 1;
        int max = 5;
        System.out.printf("Enter a number between %d and %d : ", min, max);
        Validator.integerValidator(min, max);

        System.out.println("End Validator test.");
    }

    public void testStringUtils(){
        System.out.println("\nStart StringUtils test.");

        String testString = "dog";
        int length = testString.length();
        System.out.printf("%s| end \tLength : %d\n", testString, length);
        testString = StringUtils.rightPad(testString, 30);
        length = testString.length();
        System.out.printf("%s| end \tLength : %d\n", testString, length);

        System.out.println("End StringUtils test.");
    }
}

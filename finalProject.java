import java.util.Scanner; //importing scanner
public class finalProject { //program begins
    public static void main(String[] args) { //main method

        Scanner input = new Scanner(System.in); //scanner declaration

        //array declaration for String, int, char, and double
        String[] name = new String[10];
        int[] student_id = new int[10];
        char[] student_type = new char[10];
        double[] student_fees = new double[10];
        //variable declaration
        int local_count = 0, international_count = 0, classes;
        double extra_fees, discount_amt, totalMonetaryDiscount = 0, totalFeesOwed = 0;
        final double discount = 0.15;
        char course_type, answer;

        //for loop to populate the arrays 'name', 'student_id', and 'student_type' as well as to process the 10 students for their course type and class amount
        //and count the local and international students
        for (int student = 0; student < name.length; student++) {

            //input statements that will read and place the student's last name, id, and type into their respective arrays
            System.out.println("Please enter your Student ID: ");
            student_id[student] = input.nextInt();

            System.out.println("Please enter your last name: ");
            name[student] = input.next();

            System.out.println("Please enter your student type. L for local and I for international: ");
            student_type[student] = input.next().charAt(0);

            //while loop to verify the correct char response, it displays an error message and will repeat until the
            //user enters the correct response
            while (student_type[student] != 'L' && student_type[student] != 'I') {
                System.out.println("Invalid type. Please enter again.");

                System.out.println("Please enter your student type. L for local and I for international: ");
                student_type[student] = input.next().charAt(0);
            } //while loop ends

            //if statements to count the amount of local and international students
            if (student_type[student] == 'L') {
                local_count++;
            }

            if (student_type[student] == 'I') {
                international_count++;
            } //if statements end

            //input statement that will read and accept the course type the user enters
            System.out.println("Please enter your course type. Your options are E, M, P, C: ");
            course_type = input.next().charAt(0);

            //while loop that will display an error message if the user doesn't enter any of the valid course types and will repeat the course type question
            while (course_type != 'E' && course_type != 'M' && course_type != 'P' && course_type != 'C') {
                System.out.println("Invalid course type. Enter Again.");

                System.out.println("Please enter your course type. Your options are E, M, P, C: ");
                course_type = input.next().charAt(0);
            } //while loop ends

            //input statement to read and accept the amount of classes taken within selected course type
            System.out.println("Amount of classes taken with selected course type: ");
            classes = input.nextInt();

            //method call for store the fee calculations done within method in the student_fees array
            student_fees[student] = studentFees(course_type, classes);

            //input statement to read and accept a yes or no answer if student is taking classes from more than one course type
            System.out.println("Do you take classes from other course types? Y or N: ");
            answer = input.next().charAt(0);

            //if statements that will display appropriate responses if the user selects either yes or no
            if (answer == 'N') {
                System.out.println("Your information has been recorded.");
            }

            if (answer == 'Y') {
                System.out.println("Please enter the other course types and number of classes taken. Press N to exit.");

                //while loop being used as a continuous loop, it will continue asking for course type and classes until the user enter N, the exit code.
                while (answer != 'N') {
                    System.out.println("Please enter your course type. Your options are E, M, P, C: ");
                    course_type = input.next().charAt(0);

                    //while loop that will display an error message if the user doesn't enter any of the valid course types and will repeat the course type question
                    //until the correct course type options is entered
                    while (course_type != 'E' && course_type != 'M' && course_type != 'P' && course_type != 'C') {
                        System.out.println("Invalid course type. Enter Again.");

                        System.out.println("Please enter your course type. Your options are E, M, P, C: ");
                        course_type = input.next().charAt(0);
                    } //end of while loop

                    System.out.println("Amount of classes taken with selected course type: ");
                    classes = input.nextInt();

                    //method call to store the fee calculations done in method in extra_fees
                    extra_fees = studentFees(course_type, classes);

                    //calculating the fees to be paid by adding the extra_fees to the initial student_fees
                    student_fees[student] = extra_fees + student_fees[student];

                    //to update the while loop
                    System.out.println("Do you take classes from other course types? Y or N: ");
                    answer = input.next().charAt(0);

                    System.out.println("Your information has been recorded.");
                } //while loop ends
            } //if statement ends

            //if statement being used to calculate discount for local students
            if(student_type[student] == 'L') {
                //finding the amount of money they are discounted
                discount_amt = student_fees[student] * discount;

                //calculating the final fee that has to be paid by subtracting the discount from the amount that is stored in the student_fees array
                //for that particular student
                student_fees[student] = student_fees[student] - discount_amt;

                //calculating the total monetary discount that the school has given
                totalMonetaryDiscount+=discount_amt;
            } //if statement ends

            //calculating the total amount of fees owed to the school
            totalFeesOwed+=student_fees[student];

        } //for loop ends

        //for loop being used to output the contents of the arrays for student id, name, type, and fees owed
        for (int ss = 0; ss < name.length; ss++) {
            System.out.println("---------------------------------");

            System.out.println("STUDENT ID: " + student_id[ss]);
            System.out.println("STUDENT'S LAST NAME: " + name[ss]);
            System.out.println("STUDENT TYPE: " + student_type[ss]);
            System.out.println("FEES OWED: " +student_fees[ss]);

            System.out.println("---------------------------------");

        } //for loop ends

        //blank print statement to make the output look neater
        System.out.println();

        //more output statements for total amount of local and international students, and total amount of discounts and fees
        System.out.println("LOCAL STUDENTS: " + local_count);
        System.out.println("INTERNATIONAL STUDENTS: " + international_count);
        System.out.println("TOTAL MONETARY DISCOUNT: " +totalMonetaryDiscount);
        System.out.println("TOTAL FEES OWED TO INSTITUTION: " +totalFeesOwed);

    } //main class ends

    //method called studentFees, it takes on the arguments courseType and class_amt. this method uses a switch to calculate the amount of money
    //the students owe to the school and returns that value
    public static double studentFees(char courseType, int class_amt) {
        //variable declaration
        double studentFees = 0;
        final double feeE = 850.53, feeM = 895.25, feeP = 450.67, feeC = 921.14;

        //switch statement to go through the different course types and to calculate the fees accordingly
        switch (courseType) {
            case 'E' -> studentFees = class_amt * feeE;
            case 'M' -> studentFees = class_amt * feeM;
            case 'P' -> studentFees = class_amt * feeP;
            case 'C' -> studentFees = class_amt * feeC;
        } //switch ends

        //return statement
        return studentFees;

    } //studentFees method ends
}//program ends
package project.hospitalmanagement;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Hospital hospital=new Hospital();
        int choice;
        do {
            System.out.println("\n1. Register Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. View Patients");
            System.out.println("4. View Doctors");
            System.out.println("5. View Available Slots");
            System.out.println("6. Book Appointment");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. View Doctor Schedule");
            System.out.println("9. View Patient History");
            System.out.println("10. Exit");
            System.out.println("Enter choice:");
            choice=sc.nextInt();
            try {
                switch(choice) {
                    case 1:
                        System.out.println("Enter patient ID:");
                        int patientId=sc.nextInt();
                        System.out.println("Enter patient name:");
                        String patientName=sc.next();
                        System.out.println("Enter patient age:");
                        int age=sc.nextInt();
                        hospital.addPatient(new Patient(patientId,patientName,age));
                        break;
                    case 2:
                        System.out.println("Enter doctor ID:");
                        int doctorId=sc.nextInt();
                        System.out.println("Enter doctor name:");
                        String doctorName=sc.next();
                        System.out.println("Enter specialization:");
                        String specialization=sc.next();
                        hospital.addDoctor(new Doctor(doctorId,doctorName,specialization));
                        break;
                    case 3:
                        hospital.showPatients();
                        break;
                    case 4:
                        hospital.showDoctors();
                        break;
                    case 5:
                        System.out.println("Enter doctor ID:");
                        int slotDoctor=sc.nextInt();
                        System.out.println("Enter date yyyy-mm-dd:");
                        LocalDate slotDate=LocalDate.parse(sc.next());
                        hospital.showAvailableSlots(slotDoctor,slotDate);
                        break;
                    case 6:
                        System.out.println("Enter patient ID:");
                        int bookPatient=sc.nextInt();
                        System.out.println("Enter doctor ID:");
                        int bookDoctor=sc.nextInt();
                        System.out.println("Enter date yyyy-mm-dd:");
                        LocalDate bookDate=LocalDate.parse(sc.next());
                        hospital.showAvailableSlots(bookDoctor,bookDate);
                        System.out.println("Enter time from available slots:");
                        String bookTime=sc.next();
                        hospital.bookAppointment(bookPatient,bookDoctor,bookDate,bookTime);
                        break;
                    case 7:
                        System.out.println("Enter doctor ID:");
                        int cancelDoctor=sc.nextInt();
                        System.out.println("Enter date yyyy-mm-dd:");
                        LocalDate cancelDate=LocalDate.parse(sc.next());
                        System.out.println("Enter time:");
                        String cancelTime=sc.next();
                        hospital.cancelAppointment(cancelDoctor,cancelDate,cancelTime);
                        break;
                    case 8:
                        System.out.println("Enter doctor ID:");
                        int scheduleDoctor=sc.nextInt();
                        hospital.showDoctorSchedule(scheduleDoctor);
                        break;
                    case 9:
                        System.out.println("Enter patient ID:");
                        int historyPatient=sc.nextInt();
                        hospital.showPatientHistory(historyPatient);
                        break;
                    case 10:
                        System.out.println("Thank you");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } while(choice!=10);
        sc.close();
    }
}
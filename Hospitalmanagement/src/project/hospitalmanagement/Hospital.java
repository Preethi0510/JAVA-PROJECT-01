package project.hospitalmanagement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
public class Hospital {
    private HashMap<Integer,Patient> patients=new HashMap<>();
    private HashMap<Integer,Doctor> doctors=new HashMap<>();
    private HashMap<String,Appointment> appointments=new HashMap<>();
    private ArrayList<Appointment> history=new ArrayList<>();
    private ArrayList<String> slots=new ArrayList<>();
    public Hospital() {
        slots.add("10:00");
        slots.add("11:00");
        slots.add("12:00");
        slots.add("14:00");
        slots.add("15:00");
        slots.add("16:00");
        Patient p1=new Patient(101,"Preethi",23);
        Patient p2=new Patient(102,"Aishu",24);
        patients.put(101,p1);
        patients.put(102,p2);
        Doctor d1=new Doctor(1,"Kaviya","Cardiologist");
        Doctor d2=new Doctor(2,"Ram","Dentist");
        doctors.put(1,d1);
        doctors.put(2,d2);
        LocalDate date=LocalDate.of(2026,10,5);
        Appointment a1=new Appointment(p1,d1,date,"10:00");
        Appointment a2=new Appointment(p2,d2,date,"14:00");
        appointments.put(1+"-"+date+"-10:00",a1);
        appointments.put(2+"-"+date+"-14:00",a2);
        history.add(a1);
        history.add(a2);
    }
    public void addPatient(Patient patient) {
        if(patients.containsKey(patient.getId())) {
            throw new RuntimeException("Patient ID already exists");
        }
        patients.put(patient.getId(),patient);
        System.out.println("Patient registered successfully");
    }
    public void addDoctor(Doctor doctor) {
        if(doctors.containsKey(doctor.getId())) {
            throw new RuntimeException("Doctor ID already exists");
        }
        doctors.put(doctor.getId(),doctor);
        System.out.println("Doctor added successfully");
    }
    public void showPatients() {
        for(Patient patient:patients.values()) {
            System.out.println(patient);
        }
    }
    public void showDoctors() {
        for(Doctor doctor:doctors.values()) {
            System.out.println(doctor);
        }
    }
    public void showAvailableSlots(int doctorId,LocalDate date) {
        if(!doctors.containsKey(doctorId)) {
            throw new RuntimeException("Doctor not found");
        }
        boolean found=false;
        System.out.println("Available slots:");
        for(String slot:slots) {
            String key=doctorId+"-"+date+"-"+slot;
            if(!appointments.containsKey(key)) {
                System.out.println(slot);
                found=true;
            }
        }
        if(!found) {
            System.out.println("No slots available");
        }
    }
    public void bookAppointment(int patientId,int doctorId,LocalDate date,String time) {
        if(!patients.containsKey(patientId)) {
            throw new RuntimeException("Patient not found");
        }
        if(!doctors.containsKey(doctorId)) {
            throw new RuntimeException("Doctor not found");
        }
        if(!slots.contains(time)) {
            throw new RuntimeException("Invalid time slot. Available slots are 10:00, 11:00, 12:00, 14:00, 15:00, 16:00");
        }
        String key=doctorId+"-"+date+"-"+time;
        if(appointments.containsKey(key)) {
            throw new RuntimeException("This slot is already booked");
        }
        Patient patient=patients.get(patientId);
        Doctor doctor=doctors.get(doctorId);
        Appointment appointment=new Appointment(patient,doctor,date,time);
        appointments.put(key,appointment);
        history.add(appointment);
        System.out.println("Appointment booked successfully");
    }
    public void cancelAppointment(int doctorId,LocalDate date,String time) {
        String key=doctorId+"-"+date+"-"+time;
        if(appointments.containsKey(key)) {
            appointments.remove(key);
            System.out.println("Appointment cancelled successfully");
        } else {
            System.out.println("Appointment not found");
        }
    }
    public void showDoctorSchedule(int doctorId) {
        if(!doctors.containsKey(doctorId)) {
            throw new RuntimeException("Doctor not found");
        }
        boolean found=false;
        for(Appointment appointment:appointments.values()) {
            if(appointment.getDoctor().getId()==doctorId) {
                System.out.println(appointment);
                found=true;
            }
        }
        if(!found) {
            System.out.println("No active appointments for this doctor");
        }
    }
    public void showPatientHistory(int patientId) {
        if(!patients.containsKey(patientId)) {
            throw new RuntimeException("Patient not found");
        }
        boolean found=false;
        for(Appointment appointment:history) {
            if(appointment.getPatient().getId()==patientId) {
                System.out.println(appointment);
                found=true;
            }
        }
        if(!found) {
            System.out.println("No appointment history");
        }
    }
}
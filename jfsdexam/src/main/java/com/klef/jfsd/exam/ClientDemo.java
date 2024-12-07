package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Vehicle.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // Insert operation
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Tesla");
        vehicle.setModel("Model S");
        vehicle.setEngine("Electric");
        vehicle.setWheels(4);
        vehicle.setSeats(5);
        session.save(vehicle);
        System.out.println("Vehicle inserted successfully!");

        transaction.commit();

        // View operation
        List<Vehicle> vehicles = session.createQuery("from Vehicle", Vehicle.class).list();
        for (Vehicle v : vehicles) {
            System.out.println("Vehicle ID: " + v.getId() + ", Name: " + v.getName() + 
                               ", Model: " + v.getModel() + ", Engine: " + v.getEngine() + 
                               ", Wheels: " + v.getWheels() + ", Seats: " + v.getSeats());
        }

        session.close();
        sessionFactory.close();
    }
}

package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import java.io.File;
class Cemetery {
    private String name;
    private String address;
    public ArrayList<String> availableFlowers;
    private ArrayList<Employee> employees;
    private ArrayList<Service> services;

    public Cemetery(String name, String address) {
        this.name = name;
        this.address = address;
        this.availableFlowers = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.services = new ArrayList<>();
    }
    static Log my_log;

    static {
        try{
            my_log = new Log("Cemetery.log", Level.ALL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public void addFlower(String flower) {
        availableFlowers.add(flower);
    }

    public void displayEmployees() {
        System.out.println("Сотрудники:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i+1) + ". " + employees.get(i));

        }
        my_log.logger.info("employees");
    }

    public void displayServices() {
        System.out.println("Услуги:");
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i+1) + ". " + services.get(i));
        }
        my_log.logger.info("services");
    }

    public void removeEmployee(int index) {
        if (index >= 0 && index < employees.size()) {
            employees.remove(index);
            System.out.println("Сотрудник удален успешно.");
        } else {
            System.out.println("Неправильный ввод.");
        }
        my_log.logger.info("Minus employee");
    }

    public void removeService(int index) {
        if (index >= 0 && index < services.size()) {
            services.remove(index);
            System.out.println("Услуга удалена успешно.");
        } else {
            System.out.println("Неправильынй ввод.");
        }
        my_log.logger.info("Minus service");
    }

    public void displayAddress() {
        System.out.println("Адрес: " + address);
        my_log.logger.info("Address reveal");
    }
}

class Employee {
    private String name;
    private double salary;
    private String position;

    public Employee(String name, double salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }
    static Log my_log;

    static {
        try{
            my_log = new Log("Employeee.log", Level.ALL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        my_log.logger.info("Employee reveal");
        return "Фио: " + name + ", Зарплата: " + salary + ", Должность: " + position;

    }
}

class Service {
    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    static Log my_log;

    static {
        try{
            my_log = new Log("Service.log", Level.ALL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        my_log.logger.info("Service reveal");

        return "Название: " + name + ", Цена: " + cost;
    }
}

public class Main {
    static Log my_log;

    static {
        try{
            my_log = new Log("Main.log", Level.ALL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Cemetery cemetery = new Cemetery("Кладбище", "Метро Пыхтино");
        cemetery.addFlower("Роза");
        cemetery.addFlower("Гвоздика");
        cemetery.addFlower("Тюльпаны");

        cemetery.addEmployee(new Employee("Гвоздиков Н.В.", 2200.0, "Дворник"));
        cemetery.addEmployee(new Employee("Грушин Н.В.", 1000000.0, "Директор"));
        cemetery.addEmployee(new Employee("Якшин М.Е.", 1337.0, "Гробовщики"));

        cemetery.addService(new Service("Захоронение", 2500.0));
        cemetery.addService(new Service("Уборка на могиле", 1000.0));
        cemetery.addService(new Service("Вознесение цветов", 300.0));

        Scanner scanner = new Scanner(System.in);
        int choice;
        my_log.logger.info("Work is working");
        do {

            System.out.println("\n1. Вывод сотрудников");
            System.out.println("2. Добавить сотрудника");
            System.out.println("3. Уволить сотрудника");
            System.out.println("4. Вывести услуги");
            System.out.println("5. Добавить услугу");
            System.out.println("6. Удалить услугу");
            System.out.println("7. Вывести адреc");
            System.out.println("8. Добавить цветы");
            System.out.println("9. Вывести список цветов");
            System.out.println("0. Выход");
            System.out.print("Введите номер: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAllEmployees(cemetery);
                    break;
                case 2:
                    addEmployeeWithInput(cemetery, scanner);
                    break;
                case 3:
                    removeEmployeeByIndexWithInput(cemetery, scanner);
                    break;
                case 4:
                    displayAllServices(cemetery);
                    break;
                case 5:
                    addServiceWithInput(cemetery, scanner);
                    break;
                case 6:
                    removeServiceByIndexWithInput(cemetery, scanner);
                    break;
                case 7:
                    displayCemeteryAddress(cemetery);
                    break;
                case 8:
                    addFlowerWithInput(cemetery, scanner);
                    break;
                case 9:
                    displayAvailableFlowers(cemetery);
                    break;
                case 0:
                    exitProgram();
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    my_log.logger.info("Wrong choice income");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void addEmployeeWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Ввести имя сотрудника: ");
        String name = scanner.nextLine();
        System.out.print("Ввести зарплату: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ввести должность: ");
        String position = scanner.nextLine();
        Employee newEmployee = new Employee(name, salary, position);
        cemetery.addEmployee(newEmployee);
        my_log.logger.info("Wrong choice income");
    }

    public static void removeEmployeeByIndexWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Введите индекс сотрудника которого нужно уволить: ");
        int empIndex = scanner.nextInt();
        cemetery.removeEmployee(empIndex - 1);
        my_log.logger.info("Minus employee");
    }

    public static void addServiceWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Ввести название услуги: ");
        String serviceName = scanner.nextLine();
        System.out.print("Ввести цену услуги: ");
        double serviceCost = scanner.nextDouble();
        scanner.nextLine();
        Service newService = new Service(serviceName, serviceCost);
        cemetery.addService(newService);
        my_log.logger.info("Plus service");

    }

    public static void removeServiceByIndexWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Введите индекс услуги для ее удаления: ");
        int serviceIndex = scanner.nextInt();
        cemetery.removeService(serviceIndex - 1);
        my_log.logger.info("Minus service");

    }

    public static void displayCemeteryAddress(Cemetery cemetery) {
        cemetery.displayAddress();
    }

    public static void displayAllEmployees(Cemetery cemetery) {
        cemetery.displayEmployees();
    }

    public static void displayAllServices(Cemetery cemetery) {
        cemetery.displayServices();
    }

    public static void addFlowerWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Введите название цветов: ");
        String flowerName = scanner.nextLine();
        cemetery.addFlower(flowerName);
        my_log.logger.info("Plus flower");

    }

    public static void displayAvailableFlowers(Cemetery cemetery) {
        System.out.println("Цветы:");
        for (String flower : cemetery.availableFlowers) {
            System.out.println("- " + flower);


        }
        my_log.logger.info("Flowers reveal");
    }

    public static void exitProgram() {
        my_log.logger.info("Work is not working");

        System.out.println("Выход");
    }
}


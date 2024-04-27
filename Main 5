import java.util.ArrayList;
import java.util.Scanner;

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
    }

    public void displayServices() {
        System.out.println("Услуги:");
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i+1) + ". " + services.get(i));
        }
    }

    public void removeEmployee(int index) {
        if (index >= 0 && index < employees.size()) {
            employees.remove(index);
            System.out.println("Сотрудник удален успешно.");
        } else {
            System.out.println("Неправильный ввод.");
        }
    }

    public void removeService(int index) {
        if (index >= 0 && index < services.size()) {
            services.remove(index);
            System.out.println("Услуга удалена успешно.");
        } else {
            System.out.println("Неправильынй ввод.");
        }
    }

    public void displayAddress() {
        System.out.println("Адрес: " + address);
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

    @Override
    public String toString() {
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

    @Override
    public String toString() {
        return "Название: " + name + ", Цена: " + cost;
    }
}

public class Main {
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
        do {
            System.out.println("\n1. Вывод сотрудников");
            System.out.println("2. Добавить сотрудника");
            System.out.println("3. Уволить сотрудника");
            System.out.println("4. Вывести услуги");
            System.out.println("5. Добавить услугу");
            System.out.println("6. Удалить услугу");
            System.out.println("7. Вывести адрем");
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
    }

    public static void removeEmployeeByIndexWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Введите индекс сотрудника которого нужно уволить: ");
        int empIndex = scanner.nextInt();
        cemetery.removeEmployee(empIndex - 1);
    }

    public static void addServiceWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Ввести название услуги: ");
        String serviceName = scanner.nextLine();
        System.out.print("Ввести цену услуги: ");
        double serviceCost = scanner.nextDouble();
        scanner.nextLine();
        Service newService = new Service(serviceName, serviceCost);
        cemetery.addService(newService);
    }

    public static void removeServiceByIndexWithInput(Cemetery cemetery, Scanner scanner) {
        System.out.print("Введите индекс услуги для ее удаления: ");
        int serviceIndex = scanner.nextInt();
        cemetery.removeService(serviceIndex - 1);
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
    }

    public static void displayAvailableFlowers(Cemetery cemetery) {
        System.out.println("Цветы:");
        for (String flower : cemetery.availableFlowers) {
            System.out.println("- " + flower);
        }
    }

    public static void exitProgram() {
        System.out.println("Выход");
    }
}

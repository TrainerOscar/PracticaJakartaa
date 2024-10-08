
import dao.UserDAO;


import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        List<User> User;
        List<Integer> existingIds = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        boolean pass;
        int counter;
        char op = '0';

        while (op != '5') {
            System.out.println("User Registries Administrator\n");
            System.out.println("1. Add new user");
            System.out.println("2. See current users");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("5. Exit\n");
            System.out.print("Choose an option: ");
            op = sc.next().charAt(0);

            switch (op) {
                case '1':
                    System.out.println("\n1. Add new client\n");
                    User = userDAO.getUsers();
                    counter = 1;
                    existingIds.clear();
                    if (!userDAO.isEmpty()) {
                        System.out.println("List of existing id's");
                        for (User user : userDAO.getUsers()) {
                            counter++;
                            System.out.print("{ " + user.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            existingIds.add(user.getId());
                        }
                    }
                        User newUser = new User();
                    pass = false;
                    while (!pass) {
                        System.out.print("Insert the client's unique id: ");
                        newUser.setId(Integer.parseInt(sc.next()));
                        sc.nextLine();
                        pass = true;
                        for (int id : existingIds) {
                            if (newUser.getId() == id) {
                                pass = false;
                                System.out.println("You cannot use an already existing id!\n");
                            }
                        }
                    }
                    System.out.print("Insert the client's name: ");
                    newUser.setName(sc.nextLine());
                    System.out.print("Insert the client's RUC number: ");
                    newUser.setRuc(sc.nextLine());
                    System.out.print("Insert the client's department: ");
                    newUser.setDepartment(sc.nextLine());
                    System.out.print("Insert the client's city: ");
                    newUser.setCity(sc.nextLine());
                    System.out.print("Insert the client's website url: ");
                    newUser.setWebsite(sc.next());
                    newUser.setClientState(true);
                    userDAO.save(newUser);
                    System.out.println("\nThe client was successfully added to the database.\n");
                    sc.nextLine();
                    break;

                case '2':
                    userDAO = (UserDAO) UserDAO.getUsers();
                    System.out.println("\n2. See current clients\n");
                    if (userDAO.isEmpty()) {
                        System.out.println("There are no clients in the database.\n");
                    } else {
                        for (User user : UserDAO.getUsers()) {
                            System.out.println(user);
                        }
                    }
                    System.out.println("\n");
                    sc.nextLine();
                    break;

                case '3':
                    System.out.println("\n3. Update a client register\n");
                    User = UserDAO.getUsers();
                    counter = 1;
                    if (!userDAO.isEmpty()) {
                        System.out.println("List of existing id's");
                        existingIds.clear();
                        for (User user : UserDAO.getUsers()) {
                            counter++;
                            System.out.print("{ " + UserDAO.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            existingIds.add(UserDAO.getId());
                        }
                    } else {
                        System.out.println("There are no clients to update in the database.\n");
                        sc.nextLine();
                        break;
                    }
                    User updatedUser = new User();
                    pass = false;
                    while (!pass) {
                        System.out.print("\n\nInsert the client's unique id: ");
                        updatedUser.setId(Integer.parseInt(sc.next()));
                        sc.nextLine();
                        for (int id : existingIds) {
                            if (UserDAO.getId() == id) {
                                pass = true;
                                break;
                            }
                        }
                        if (!pass) {
                            System.out.println("You have to insert an existing id!\n");
                        }
                    }
                    System.out.print("Insert the client's name: ");
                    updatedUser.setName(sc.nextLine());
                    System.out.print("Insert the client's RUC number: ");
                    updatedUser.setRuc(sc.nextLine());
                    System.out.print("Insert the client's department: ");
                    updatedUser.setDepartment(sc.nextLine());
                    System.out.print("Insert the client's city: ");
                    updatedUser.setCity(sc.nextLine());
                    System.out.print("Insert the client's website url: ");
                    updatedUser.setWebsite(sc.next());
                    System.out.print("Insert the client's state (true/false): ");
                    updatedUser.setClientState(sc.nextBoolean());
                    UserDAO.update(updatedUser);
                    System.out.print("\nThe client was successfully updated in the database.\n");
                    break;

                case '4':
                    System.out.println("\n4. Delete a client register\n");
                    User = UserDAO.getUsers();
                    counter = 1;
                    if (!userDAO.isEmpty()) {
                        System.out.println("List of existing id's");
                        existingIds.clear();
                        for (User user : UserDAO.getUsers()) {
                            counter++;
                            System.out.print("{ " + UserDAO.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            existingIds.add(UserDAO.getId());
                        }
                    } else {
                        System.out.println("\nThere are no clients in the database.\n");
                        break;
                    }
                    int removeId = 0;
                    pass = false;
                    while (!pass) {
                        System.out.print("\n\nInsert the id of the client you want to delete: ");
                        removeId = Integer.parseInt(sc.next());
                        sc.nextLine();
                        for (int id : existingIds) {
                            if (removeId == id) {
                                pass = true;
                                break;
                            }
                        }
                        if (!pass) {
                            System.out.println("You have to insert an existing id!\n");
                        }
                    }
                    User deletedClient = (models.User) UserDAO.findById(removeId);
                    userDAO.delete(deletedClient);
                    System.out.println("\nThe client was successfully deleted from the database.\n");
                    break;

                case '5':
                    System.out.println("\nThank you for using the program.\n");
                    break;

                default:
                    System.out.println("You have entered an invalid option! Please choose an option between 1 and 5.\n");
                    break;
            }
        }
    }
}

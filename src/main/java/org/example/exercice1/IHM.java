package org.example.exercice1;

import org.example.exercice1.entity.Student;
import org.example.exercice1.utils.ConnectionUtils;

import java.sql.*;
import java.util.Scanner;

public class IHM {
    //Attributs
    private static boolean stop = false;
    private static Scanner sc = new Scanner(System.in);

    //Méthodes
    public static void start(){
        while(!stop){
            System.out.println("[0] Quitter");
            System.out.println("[1] Ajouter un étudiant");
            System.out.println("[2] Afficher la totalité des étudiants");
            System.out.println("[3] Afficher un étudiant");
            System.out.println("[4] Afficher les étudiants d'une classe");
            System.out.println("[5] Supprimer un étudiant");
            System.out.println("Menu choisi :");

            switch(sc.nextInt()){
                case 0 -> stop();
                case 1 -> addStudent();
                case 2 -> showAllStudents();
                case 3 -> showStudent();
                case 4 -> showClass();
                case 5 -> removeStudent();
            }
        }
        sc.close();
    }

    private static void stop(){stop = true;}

    private static void addStudent(){
        try{
            Connection connection = ConnectionUtils.getSQLConnection();
            sc.nextLine();

            System.out.println("Nom de l'étudiant : ");
            String lastName = sc.nextLine();

            System.out.println("Prénom de l'étudiant : ");
            String firstName = sc.nextLine();

            System.out.println("Numéro de classe de l'étudiant : ");
            int classNumber = sc.nextInt();
            sc.nextLine();

            System.out.println("Date d'obtention du diplôme :");
            String graduationDate = sc.nextLine();

            Student student = new Student(firstName, lastName, graduationDate, classNumber);

            String request = "INSERT INTO etudiant(first_name, last_name, class_number, graduation_date) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getClassNumber());
            preparedStatement.setString(4, student.getGraduationDate());
            int nbRows = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            System.out.println(student);

            if(resultSet.next()){
                student.setId(resultSet.getInt(1));
            }

            System.out.println(student);
            System.out.println("Etudiant ajouté !");
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void showAllStudents(){
        try{
            Connection connection = ConnectionUtils.getSQLConnection();
            sc.nextLine();

            String request = "SELECT * FROM etudiant";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while(resultSet.next()){
                System.out.print("Prénom de l'étudiant : "+resultSet.getString("first_name"));
                System.out.print(" | Nom de l'étudiant : "+resultSet.getString("last_name"));
                System.out.print(" | Classe : "+resultSet.getInt("class_number"));
                System.out.println(" | Date d'obtention du diplôme : "+resultSet.getString("graduation_date"));
            }

            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void showStudent(){
        try{
            Connection connection = ConnectionUtils.getSQLConnection();
            sc.nextLine();

            System.out.println("ID de l'étudiant désiré : ");
            int id = sc.nextInt();

            String request = "SELECT * FROM etudiant WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                System.out.print("Prénom de l'étudiant : "+resultSet.getString("first_name"));
                System.out.print(" | Nom de l'étudiant : "+resultSet.getString("last_name"));
                System.out.print(" | Classe : "+resultSet.getInt("class_number"));
                System.out.print(" | Date d'obtention du diplôme : "+resultSet.getString("graduation_date"));
            }

            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void showClass(){
        try{
            Connection connection = ConnectionUtils.getSQLConnection();
            sc.nextLine();

            System.out.println("Numéro de classe désiré : ");
            int id = sc.nextInt();

            String request = "SELECT * FROM etudiant WHERE class_number = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Liste des étudiants de la classe "+id+" : ");

            while(resultSet.next()){
                System.out.print("Prénom de l'étudiant : "+resultSet.getString("first_name"));
                System.out.print(" | Nom de l'étudiant : "+resultSet.getString("last_name"));
                System.out.print(" | Classe : "+resultSet.getInt("class_number"));
                System.out.println(" | Date d'obtention du diplôme : "+resultSet.getString("graduation_date"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void removeStudent(){
        try{
            Connection connection = ConnectionUtils.getSQLConnection();
            sc.nextLine();

            System.out.println("ID de l'étudiant à supprimer : ");
            int id = sc.nextInt();

            String request = "DELETE FROM etudiant WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            System.out.println("Etudiant "+id+" a été supprimé");

            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

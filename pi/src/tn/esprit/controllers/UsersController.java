/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.net.URL;
import com.mysql.jdbc.Connection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Personne;
import tn.esprit.tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class UsersController implements Initializable {
  
    private Connection cnx;
    private PreparedStatement smt;
    private ResultSet rs;
    private MaConnexion con;
    @FXML
    private TextField search;
    @FXML
    private TableView<Personne> table;
    @FXML
    private TableColumn<Personne, String> username;
    @FXML
    private TableColumn<Personne, String> email;
    @FXML
    private TableColumn<Personne, String> role;   
    @FXML
    private TableColumn<Personne, Integer> phone;
    @FXML
    private TableColumn<Personne, String> address;
    @FXML
    private Button print;
    @FXML
    private Button delete;
    @FXML
    private TextField txt_u;
    @FXML
    private TextField txt_e;
    @FXML
    private TextField txt_role;
    @FXML
    private TextField txt_phone;
    @FXML
    private TextField txt_add;
    
    ObservableList<Personne> dynamique_search;
    
    public ObservableList<Personne> show() throws SQLException{
        ObservableList<Personne> data=FXCollections.observableArrayList();
        String sql="select * from user";
        cnx=(Connection) con.getCnx();
        smt=cnx.prepareStatement(sql);
        rs=smt.executeQuery();
         Personne p;

        while(rs.next()){
        p=new Personne(rs.getString("username"),rs.getString("email"),rs.getString("roles"),rs.getInt("num_tel"),rs.getString("full_address"));
        
        data.add(p);
        }
        
             return data;
    }
    
    private ObservableList<Personne>add;
    public void showdata() throws SQLException{
        add=show();
    username.setCellValueFactory(new PropertyValueFactory<>("nom"));
     email.setCellValueFactory(new PropertyValueFactory<>("email"));
     role.setCellValueFactory(new PropertyValueFactory<>("role"));
      phone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
      address.setCellValueFactory(new PropertyValueFactory<>("address"));
       table.setItems(add);
      


        


    
    
    }
    
    @FXML
    void search_dynamique() throws SQLException{
   
    dynamique_search=show();
     table.setItems(dynamique_search);
        FilteredList<Personne> filteredData = new FilteredList<>(dynamique_search, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(p -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (p.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                
				else if (p.getRole().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
                             else if (p.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                             else if (String.valueOf(p.getnumtel()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                             
                             }
				     else  
				    	 return false; // Does not match.
			});
		});
    
                // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Personne> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con=new MaConnexion();
        try {
            showdata();
            search_dynamique();
          
        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }    

    @FXML
    private void search_user(MouseEvent event) throws SQLException {
       String sql="select username,email,num_tel,roles,full_address from user where email ='"+search.getText()+ "'";
       int i=0;
       cnx=(Connection) con.getCnx();
        smt=cnx.prepareStatement(sql);
        rs=smt.executeQuery();
        if(rs.next()){
      txt_u.setText(rs.getString("username"));
  
    txt_e.setText(rs.getString("email"));
   
txt_role.setText(rs.getString("roles"));
 
 txt_phone.setText(rs.getString("num_tel"));
   
    txt_add.setText(rs.getString("full_address"));
        i=1;
        
        }
        if(i==0){
            Alert alert=new Alert(AlertType.ERROR,"User doesn't exist");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void delete_user(MouseEvent event) throws SQLException {
        String sql="delete from user where email='"+search.getText()+"'";
        smt=cnx.prepareStatement(sql);
        smt.executeUpdate();
           txt_u.setText("");
  
    txt_e.setText("");
   
txt_role.setText("");
 
 txt_phone.setText("");
   
    txt_add.setText("");
     Alert alert=new Alert(AlertType.CONFIRMATION,"User Deleted");
            alert.showAndWait();
            showdata();
            search_dynamique();
        
    }

   

    @FXML
    private void change() throws SQLException {
       Personne p=table.getSelectionModel().getSelectedItem();
       String sql="select * from user where email=?";
       smt=cnx.prepareStatement(sql);
        smt.setString(1, p.getEmail());
        rs=smt.executeQuery();
        if(rs.next()){
        txt_u.setText(rs.getString("username"));
  
    txt_e.setText(rs.getString("email"));
   
txt_role.setText(rs.getString("roles"));
 
 txt_phone.setText(rs.getString("num_tel"));
   
    txt_add.setText(rs.getString("full_address"));
            search.setText(rs.getString("email"));
            
            ;}
        
    }

   @FXML
    private void print_user(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException {
        BaseColor entete =new BaseColor(155,221,247);
        File folder=new File("user_pdf");
        if(!folder.exists()){
        folder.mkdir();
        }
        String nom_fichier="user_pdf/list.pdf";
        Font titleFont =new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.BLUE);
        Font RedFont =new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.RED);
        
           
        Document doc=new Document();
       
       cnx=(Connection) con.getCnx();
        PdfWriter.getInstance(doc,new FileOutputStream(nom_fichier));
        doc.open();
        doc.addTitle("User_List");
        doc.addAuthor("Admin");
        Paragraph preface=new Paragraph();
        Paragraph titre=new Paragraph("Liste des utilisateurs",titleFont);
        titre.setAlignment(Element.ALIGN_CENTER);
        preface.add(titre);
        
        PdfPTable table =new PdfPTable(5);
        PdfPCell c1=new PdfPCell(new Phrase("Username"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(entete);
        table.addCell(c1);
        c1=new PdfPCell(new Phrase("Email"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(entete);
        table.addCell(c1);
        c1=new PdfPCell(new Phrase("Phone Number"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(entete);
        table.addCell(c1);
         c1=new PdfPCell(new Phrase("Role"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(entete);
        table.addCell(c1);
         c1=new PdfPCell(new Phrase("Address"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(entete);
        table.addCell(c1);
        
         rs=smt.executeQuery("select username,email,num_tel,roles,full_address from user ");
         while(rs.next()){
         String nom=rs.getString("username");
         String emaill=rs.getString("email");
         String num_tel=rs.getString("num_tel");
         String roles=rs.getString("roles");
         String f_a=rs.getString("full_address");
         table.addCell(nom);
         table.addCell(emaill);
         table.addCell(num_tel);
         table.addCell(roles);
         table.addCell(f_a);
         }
         table.setHeaderRows(1);
         table.setWidthPercentage((float)100.0);
        
        doc.add(preface);
        doc.add(new Paragraph(""));
        
        doc.add(table);
 	Desktop.getDesktop().open(folder);
        doc.close();
        
           
        
        
    }
    

   
    
}

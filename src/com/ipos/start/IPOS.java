/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipos.start;

import com.ipos.entity.User;
import com.ipos.jpa.connection.ConnectionPersistence;
import com.ipos.jpa.controller.UserJpaController;
import com.ipos.view.main.MainFrame;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.auth.LoginService;

/**
 *
 * @author megeh
 */
public class IPOS extends Application {

    public static User currentUser;

    @Override
    public void start(Stage primaryStage) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    EntityManagerFactory emf = ConnectionPersistence.getEntityManagerFactory();
                    MainFrame main = MainFrame.getInstance();

                    final UserJpaController controller = new UserJpaController(emf);

                    JXLoginPane loginPane = new JXLoginPane(new LoginService() {
                        @Override
                        public boolean authenticate(String username, char[] password, String server) throws Exception {
                            // Authenticate user.
                            List<User> users = controller.findUserMd5(username, controller.getPasswordMd5(Arrays.toString(password)));

                            if (!users.isEmpty()) {
                                currentUser = users.get(0);
                                
                                return true;
                            }

                            return false;
                        }
                    });
                    
                    loginPane.setBannerText("Dgte. Rubi Marketing");

                    JXLoginPane.JXLoginDialog dialog = new JXLoginPane.JXLoginDialog(main, loginPane);

                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);

                    if (dialog.getStatus() != JXLoginPane.Status.SUCCEEDED) {
                        main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
                    } else {
                        main.setCurrentUserInfo();
                        main.setLocationRelativeTo(null);
                        main.setVisible(true);
                    }

                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(IPOS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

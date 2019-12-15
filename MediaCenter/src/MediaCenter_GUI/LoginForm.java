package MediaCenter_GUI;

import Client.MediaCenterInterface;
import Exceptions.PasswordIncorretaException;
import Exceptions.UtilizadorInexistenteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @created November 27, 2019
 */

public class LoginForm extends JFrame {

    static LoginForm thelogin;
    static JFrame frame;

    JPanel pnLoginPanel;
    JLabel lbUsernameLabel;
    JLabel lbPasswordLabel;
    JTextField tfUsernameField;
    JPasswordField tfPasswordField;
    JButton btGuestLogin;
    JButton btLoginButton;
    JButton btForgotButton;

    public void CloseFrame() {
        super.dispose();
    }

    /**
     *
     */
    public LoginForm(MediaCenterInterface mediacenter) {
        super("Login");

        pnLoginPanel = new JPanel();
        GridBagLayout gbLoginPanel = new GridBagLayout();
        GridBagConstraints gbcLoginPanel = new GridBagConstraints();
        pnLoginPanel.setLayout(gbLoginPanel);

        lbUsernameLabel = new JLabel("username:");
        gbcLoginPanel.gridx = 4;
        gbcLoginPanel.gridy = 4;
        gbcLoginPanel.gridwidth = 8;
        gbcLoginPanel.gridheight = 3;
        gbcLoginPanel.fill = GridBagConstraints.NONE;
        gbcLoginPanel.weightx = 0;
        gbcLoginPanel.weighty = 0;
        gbcLoginPanel.anchor = GridBagConstraints.EAST;
        gbcLoginPanel.insets = new Insets(5, 20, 0, 0);
        gbLoginPanel.setConstraints(lbUsernameLabel, gbcLoginPanel);
        pnLoginPanel.add(lbUsernameLabel);

        lbPasswordLabel = new JLabel("password:");
        gbcLoginPanel.gridx = 4;
        gbcLoginPanel.gridy = 8;
        gbcLoginPanel.gridwidth = 8;
        gbcLoginPanel.gridheight = 3;
        gbcLoginPanel.fill = GridBagConstraints.NONE;
        gbcLoginPanel.weightx = 0;
        gbcLoginPanel.weighty = 0;
        gbcLoginPanel.anchor = GridBagConstraints.EAST;
        gbcLoginPanel.insets = new Insets(0, 20, 0, 0);
        gbLoginPanel.setConstraints(lbPasswordLabel, gbcLoginPanel);
        pnLoginPanel.add(lbPasswordLabel);

        tfUsernameField = new JTextField();
        gbcLoginPanel.gridx = 12;
        gbcLoginPanel.gridy = 4;
        gbcLoginPanel.gridwidth = 11;
        gbcLoginPanel.gridheight = 3;
        gbcLoginPanel.fill = GridBagConstraints.BOTH;
        gbcLoginPanel.weightx = 0;
        gbcLoginPanel.weighty = 0;
        gbcLoginPanel.anchor = GridBagConstraints.CENTER;
        gbcLoginPanel.insets = new Insets(5, 0, 0, 20);
        gbLoginPanel.setConstraints(tfUsernameField, gbcLoginPanel);
        pnLoginPanel.add(tfUsernameField);

        tfPasswordField = new JPasswordField();
        gbcLoginPanel.gridx = 12;
        gbcLoginPanel.gridy = 8;
        gbcLoginPanel.gridwidth = 11;
        gbcLoginPanel.gridheight = 3;
        gbcLoginPanel.fill = GridBagConstraints.BOTH;
        gbcLoginPanel.weightx = 0;
        gbcLoginPanel.weighty = 0;
        gbcLoginPanel.anchor = GridBagConstraints.CENTER;
        gbcLoginPanel.insets = new Insets(0, 0, 5, 20);
        gbLoginPanel.setConstraints(tfPasswordField, gbcLoginPanel);
        pnLoginPanel.add(tfPasswordField);

        btGuestLogin = new JButton("Login as Guest");
        gbcLoginPanel.gridx = 4;
        gbcLoginPanel.gridy = 12;
        gbcLoginPanel.gridwidth = 9;
        gbcLoginPanel.gridheight = 4;
        gbcLoginPanel.fill = GridBagConstraints.BOTH;
        gbcLoginPanel.weightx = 1;
        gbcLoginPanel.weighty = 0;
        gbcLoginPanel.anchor = GridBagConstraints.NORTH;
        gbcLoginPanel.insets = new Insets(0, 0, 5, 0);
        gbLoginPanel.setConstraints(btGuestLogin, gbcLoginPanel);
        pnLoginPanel.add(btGuestLogin);

        btLoginButton = new JButton("Login");
        gbcLoginPanel.gridx = 14;
        gbcLoginPanel.gridy = 12;
        gbcLoginPanel.gridwidth = 9;
        gbcLoginPanel.gridheight = 4;
        gbcLoginPanel.fill = GridBagConstraints.BOTH;
        gbcLoginPanel.weightx = 1;
        gbcLoginPanel.weighty = 0;
        gbcLoginPanel.anchor = GridBagConstraints.NORTH;
        gbcLoginPanel.insets = new Insets(0, 0, 5, 0);
        gbLoginPanel.setConstraints(btLoginButton, gbcLoginPanel);
        pnLoginPanel.add(btLoginButton);

        btForgotButton = new JButton("Forgot Password");
        gbcLoginPanel.gridx = 12;
        gbcLoginPanel.gridy = 17;
        gbcLoginPanel.gridwidth = 11;
        gbcLoginPanel.gridheight = 1;
        gbcLoginPanel.fill = GridBagConstraints.NONE;
        gbcLoginPanel.weightx = 0;
        gbcLoginPanel.weighty = 0;
        gbcLoginPanel.anchor = GridBagConstraints.CENTER;
        gbLoginPanel.setConstraints(btForgotButton, gbcLoginPanel);
        pnLoginPanel.add(btForgotButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(pnLoginPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        btLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tfUsernameField.getText();
                char[] password = tfPasswordField.getPassword();

                if (!username.isBlank() && password.length > 0) {
                    try {
                        mediacenter.login(username, new String(password));
                        CloseFrame();
                    } catch (PasswordIncorretaException p) {
                        MessageDialog md = new MessageDialog("Error", p.getMessage());
                    } catch (UtilizadorInexistenteException u) {
                        MessageDialog md = new MessageDialog("Error", u.getMessage());
                    }

                } else if (username.isBlank() && password.length > 0) {
                    MessageDialog md = new MessageDialog("Error", "Please fill your username");
                } else if (password.length == 0 && !username.isBlank()) {
                    MessageDialog md = new MessageDialog("Error", "Pleasy type in your password");
                } else {
                    MessageDialog md = new MessageDialog("Error", "You need to type your username and password");
                }

            }
        });

        btForgotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetPassword rpf = new resetPassword(mediacenter);
            }
        });

        btGuestLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mediacenter.loginGuest();
                CloseFrame();
            }
        });
    }
}

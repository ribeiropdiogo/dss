package MediaCenter_GUI;

import Client.MediaCenterInterface;
import MediaCenterSystem.MediaCenter;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class textEntryForm extends JFrame
{

    public interface CatProcInterface {

        void update(MediaCenterInterface mi, DefaultListModel dlm, int idConteudo, String idCat, String newCat);

    }

    static textEntryForm thekjlkj;

    JPanel pnTextEntryForm;

    JPanel pnPanel1;
    JButton btButExecute;
    JButton btButCancel;
    JTextField tfTextEntry;

    public void closeWindow() {
        super.dispose();
    }

    public textEntryForm(MediaCenterInterface mi, DefaultListModel dlm, CatProcInterface ci, int idConteudo, String idCat, String wTitle, String baseString, String buttonName)
    {
        super( wTitle );
        thekjlkj = this;
        pnTextEntryForm = new JPanel();
        GridBagLayout gbTextEntryForm = new GridBagLayout();
        GridBagConstraints gbcTextEntryForm = new GridBagConstraints();
        pnTextEntryForm.setLayout( gbTextEntryForm );

        pnPanel1 = new JPanel();
        GridBagLayout gbPanel1 = new GridBagLayout();
        GridBagConstraints gbcPanel1 = new GridBagConstraints();
        pnPanel1.setLayout( gbPanel1 );

        btButExecute = new JButton( buttonName );
        gbcPanel1.gridx = 8;
        gbcPanel1.gridy = 7;
        gbcPanel1.gridwidth = 4;
        gbcPanel1.gridheight = 3;
        gbcPanel1.fill = GridBagConstraints.BOTH;
        gbcPanel1.weightx = 1;
        gbcPanel1.weighty = 0;
        gbcPanel1.anchor = GridBagConstraints.CENTER;
        gbcPanel1.insets = new Insets( 5,10,5,10 );
        gbPanel1.setConstraints( btButExecute, gbcPanel1 );
        pnPanel1.add( btButExecute );

        btButCancel = new JButton( "Cancel" );
        gbcPanel1.gridx = 3;
        gbcPanel1.gridy = 7;
        gbcPanel1.gridwidth = 4;
        gbcPanel1.gridheight = 3;
        gbcPanel1.fill = GridBagConstraints.BOTH;
        gbcPanel1.weightx = 1;
        gbcPanel1.weighty = 0;
        gbcPanel1.anchor = GridBagConstraints.CENTER;
        gbcPanel1.insets = new Insets( 5,10,5,10 );
        gbPanel1.setConstraints( btButCancel, gbcPanel1 );
        pnPanel1.add( btButCancel );

        tfTextEntry = new JTextField(baseString);
        gbcPanel1.gridx = 2;
        gbcPanel1.gridy = 3;
        gbcPanel1.gridwidth = 20;
        gbcPanel1.gridheight = 3;
        gbcPanel1.fill = GridBagConstraints.BOTH;
        gbcPanel1.weightx = 1;
        gbcPanel1.weighty = 0;
        gbcPanel1.anchor = GridBagConstraints.SOUTH;
        gbPanel1.setConstraints( tfTextEntry, gbcPanel1 );
        pnPanel1.add( tfTextEntry );
        gbcTextEntryForm.gridx = 1;
        gbcTextEntryForm.gridy = 5;
        gbcTextEntryForm.gridwidth = 16;
        gbcTextEntryForm.gridheight = 11;
        gbcTextEntryForm.fill = GridBagConstraints.BOTH;
        gbcTextEntryForm.weightx = 1;
        gbcTextEntryForm.weighty = 0;
        gbcTextEntryForm.anchor = GridBagConstraints.CENTER;
        gbcTextEntryForm.insets = new Insets( 15,30,15,30 );
        gbTextEntryForm.setConstraints( pnPanel1, gbcTextEntryForm );
        pnTextEntryForm.add( pnPanel1 );

        btButExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tfTextEntry.getText().equals(baseString) || tfTextEntry.getText().equals("")) {
                    MessageDialog md = new MessageDialog("Error","Valid values must be inserted!");
                } else {
                    ci.update(mi, dlm, idConteudo, idCat, tfTextEntry.getText());
                    thekjlkj.closeWindow();
                }
            }
        });

        btButCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( pnTextEntryForm );
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible( true );
    }
}
package MediaCenter_GUI;

import Client.MediaCenterInterface;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

public class ChangeCategory extends JFrame {
    static ChangeCategory thealterarCategoria;

    JPanel pnPanel0;
    JLabel lbLabel0;
    JList lsList0;
    JButton btBut0;
    JButton btBut1;
    JButton btBut2;

    public ChangeCategory(MediaCenterInterface mediacenter, int idConteudo)
    {
        super( "Change Category" );

        pnPanel0 = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        lbLabel0 = new JLabel( "Categorias"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 11;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 15,15,5,0 );
        gbPanel0.setConstraints( lbLabel0, gbcPanel0 );
        pnPanel0.add( lbLabel0 );

        DefaultListModel<String> model = new DefaultListModel<>();
        String []categorias = mediacenter.getCategorias(idConteudo);

        for(int i = 0; i < categorias.length ; i++)
            model.addElement(categorias[i]);

        lsList0 = new JList( model );
        lsList0.setSelectionBackground( new Color( 212,212,212 ) );
        lsList0.setSelectionForeground( new Color( 0,0,0 ) );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 11;
        gbcPanel0.gridheight = 7;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 0,15,15,15 );
        gbPanel0.setConstraints( lsList0, gbcPanel0 );
        pnPanel0.add( lsList0 );

        btBut0 = new JButton( "Alterar"  );
        gbcPanel0.gridx = 14;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btBut0, gbcPanel0 );
        pnPanel0.add( btBut0 );

        btBut0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = lsList0.getSelectedIndex();
                if(index == -1) {
                    MessageDialog md = new MessageDialog("Error", "You must select one of the categories!");
                } else {
                    String sel = lsList0.getSelectedValue().toString();
                    textEntryForm tef = new textEntryForm(mediacenter,model, ChangeCategory::updateAlt, idConteudo, sel,"New Category", "New Category", "Change");
                }
            }
        });

        btBut1 = new JButton( "Adicionar"  );
        gbcPanel0.gridx = 14;
        gbcPanel0.gridy = 7;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btBut1, gbcPanel0 );
        pnPanel0.add( btBut1 );

        btBut1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textEntryForm tef = new textEntryForm(mediacenter,model, ChangeCategory::updateAdd, idConteudo, "","Add Category", "New Category", "Add");
            }
        });

        btBut2 = new JButton( "Remover"  );
        gbcPanel0.gridx = 14;
        gbcPanel0.gridy = 8;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 0;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.CENTER;
        gbcPanel0.insets = new Insets( 0,0,0,15 );
        gbPanel0.setConstraints( btBut2, gbcPanel0 );
        pnPanel0.add( btBut2 );

        btBut2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int index = lsList0.getSelectedIndex();
                if(index == -1) {
                    MessageDialog md = new MessageDialog("Error", "You must select one of the categories!");
                } else {
                    String sel = lsList0.getSelectedValue().toString();
                    mediacenter.removerCategoria(idConteudo, sel);
                    model.remove(index);
                }
            }
        });

        setContentPane( pnPanel0 );
        setLocationRelativeTo(null);
        setSize(275, 220);
        setResizable(false);
        setVisible( true );
    }

    private static void updateAdd(MediaCenterInterface mi, DefaultListModel dlm, int idConteudo, String idCat, String newCat) {
        mi.adicionarCategoria(idConteudo, newCat);
        if(!dlm.contains(newCat))
            dlm.addElement(newCat);
    }

    private  static void updateAlt(MediaCenterInterface mi, DefaultListModel dlm, int idConteudo, String idCat, String newCat) {
        mi.alterarCategoria(idConteudo, idCat, newCat);
        dlm.removeElement(idCat);
        dlm.addElement(newCat);
    }
}

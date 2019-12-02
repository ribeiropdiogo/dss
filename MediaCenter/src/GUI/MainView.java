package GUI;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame
{
    static MainView theaaa;

    JPanel pnMainview;
    JSplitPane sppSplitPane1;

    JPanel pnLeftPanel;
    JLabel lbUsernameLabel;
    JList lsCategorias;
    JButton btLogoutButton;
    JLabel lbCategoriasLabel;

    JPanel pnRightPanel;
    JTable tbContentTable;

    JPanel pnHead;
    /**
     */
    public static void main( String args[] )
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");
        }
        catch ( ClassNotFoundException e )
        {
        }
        catch ( InstantiationException e )
        {
        }
        catch ( IllegalAccessException e )
        {
        }
        catch ( UnsupportedLookAndFeelException e )
        {
        }
        theaaa = new MainView();
    }

    public void CloseFrame(){
        super.dispose();
    }

    public MainView()
    {
        super( "Media Center" );

        //Painel Master
        pnMainview = new JPanel();
        GridBagLayout gbMainview = new GridBagLayout();
        GridBagConstraints gbcMainview = new GridBagConstraints();
        pnMainview.setLayout( gbMainview );

        //Divisória do Painel
        sppSplitPane1 = new JSplitPane( );
        sppSplitPane1.setDividerLocation( 138 );
        sppSplitPane1.setForeground( new Color( 0,0,0 ) );
        sppSplitPane1.setLastDividerLocation( 211 );

        //Painel da Esquerda

        pnLeftPanel = new JPanel();
        GridBagLayout gbLeftPanel = new GridBagLayout();
        GridBagConstraints gbcLeftPanel = new GridBagConstraints();
        pnLeftPanel.setLayout( gbLeftPanel );

        lbUsernameLabel = new JLabel( "username"  );
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 0;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.NONE;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets( 5,0,0,0 );
        gbLeftPanel.setConstraints( lbUsernameLabel, gbcLeftPanel );
        pnLeftPanel.add( lbUsernameLabel );

        String []dataCategorias = { "Rock", "Metal", "Pop", "Trance", "Action", "History", "asdas", "asdas","adas","qqqq","test","testa","teste" };
        lsCategorias = new JList( dataCategorias );
        lsCategorias.setName( "Categorias" );
        lsCategorias.setBackground( new Color( 238,238,238 ) );
        lsCategorias.setSelectionBackground( new Color( 212,212,212 ) );
        lsCategorias.setVisibleRowCount(10);
        JScrollPane scpCategorias = new JScrollPane( lsCategorias );
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 3;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 1;
        gbcLeftPanel.weighty = 1;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets( 5,5,0,0 );
        gbLeftPanel.setConstraints( scpCategorias, gbcLeftPanel );
        pnLeftPanel.add( scpCategorias );

        btLogoutButton = new JButton( "Logout"  );
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 1;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.NONE;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbLeftPanel.setConstraints( btLogoutButton, gbcLeftPanel );
        pnLeftPanel.add( btLogoutButton );

        lbCategoriasLabel = new JLabel( "Categorias"  );
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 2;
        gbcLeftPanel.gridwidth = 1;
        gbcLeftPanel.gridheight = 1;
        gbcLeftPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcLeftPanel.weightx = 0;
        gbcLeftPanel.weighty = 0;
        gbcLeftPanel.anchor = GridBagConstraints.NORTH;
        gbcLeftPanel.insets = new Insets( 5,5,0,0 );
        gbLeftPanel.setConstraints( lbCategoriasLabel, gbcLeftPanel );
        pnLeftPanel.add( lbCategoriasLabel );
        sppSplitPane1.setLeftComponent(pnLeftPanel);




        //Painel da Direita
        pnRightPanel = new JPanel();
        GridBagLayout gbRightPanel = new GridBagLayout();
        GridBagConstraints gbcRightPanel = new GridBagConstraints();
        pnRightPanel.setLayout( gbRightPanel );

        String [][]dataContentTable = new String[][] { new String[] {"musica1", "owner",
                "1:11", "+ ..."},
                new String[] {"musica2", "owner",
                        "1:11", "+ ..."},
                new String[] {"musica3", "owner",
                        "1:11", "+ ..."},
                new String[] {"musica4", "owner",
                        "1:11", "+ ..."},
                new String[] {"musica5", "owner",
                        "1:11", "+ ..."},
                new String[] {"musica6", "owner",
                        "1:11", "+ ..."},
                new String[] {"musica7", "owner",
                        "1:11", "+ ..."} };
        String []colsContentTable = new String[] { "Name", "Owner", "Duration", "Options" };
        tbContentTable = new JTable( dataContentTable, colsContentTable );
        tbContentTable.setAutoCreateRowSorter( true );
        tbContentTable.setAutoscrolls( false );
        tbContentTable.setEnabled( false );
        tbContentTable.setName( "" );
        JScrollPane scpContentTable = new JScrollPane( tbContentTable );
        gbcRightPanel.gridx = 0;
        gbcRightPanel.gridy = 0;
        gbcRightPanel.gridwidth = 1;
        gbcRightPanel.gridheight = 2;
        gbcRightPanel.fill = GridBagConstraints.BOTH;
        gbcRightPanel.weightx = 1;
        gbcRightPanel.weighty = 1;
        gbcRightPanel.anchor = GridBagConstraints.CENTER;
        gbRightPanel.setConstraints( scpContentTable, gbcRightPanel );
        pnRightPanel.add( scpContentTable );
        sppSplitPane1.setRightComponent(pnRightPanel);
        gbcMainview.gridx = 0;
        gbcMainview.gridy = 0;
        gbcMainview.gridwidth = 37;
        gbcMainview.gridheight = 26;
        gbcMainview.fill = GridBagConstraints.BOTH;
        gbcMainview.weightx = 1;
        gbcMainview.weighty = 1;
        gbcMainview.anchor = GridBagConstraints.CENTER;
        gbMainview.setConstraints( sppSplitPane1, gbcMainview );
        pnMainview.add( sppSplitPane1 );

        setDefaultCloseOperation( EXIT_ON_CLOSE );

        setContentPane( pnMainview );
        pack();
        setLocationRelativeTo(null);
        setSize(700,300);
        setVisible( true );

        btLogoutButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CloseFrame();
                LoginForm l = new LoginForm();
            }
        });
    }
}

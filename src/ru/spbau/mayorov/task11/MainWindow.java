package ru.spbau.mayorov.task11;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Represents main window of application.
 * @author Arseny Mayorov.
 */
public class MainWindow extends JFrame {

    /** Main panel. */
    private JPanel contentPane;
    /** 'Open' menu item. */
    private final JMenuItem mntmOpen;
    /** 'Close' menu item. */
    private final JMenuItem mntmClose;
    /** 'About' menu item. */
    private final JMenuItem mntmAbout;
    /** 'Exit' menu item. */
    private final JMenuItem mntmExit;
    /** 'Open' button. */
    private final JButton openButton;
    /** Tabs container. */
    private final JTabbedPane tabbedPane;
    /** 'Close' button. */
    private final JButton closeButton;
    /** 'About' button. */
    private final JButton aboutButton;
    /** 'Exit' button. */
    private final JButton exitButton;

    /** 'Open file' action. */
    private Action openAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(openButton);
            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                JTextArea textArea = new JTextArea();
                textArea.read(br, null);
                tabbedPane.addTab(selectedFile.getName(), textArea);
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
                updateClose();
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(openButton, "File not found.");
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(openButton, "Error opening file.");
            }
        }
    };



    /** 'Close tab' action. */
    private Action closeAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((tabbedPane != null) && (tabbedPane.getTabCount() > 0)) {
               tabbedPane.remove(tabbedPane.getSelectedIndex());
            }
            updateClose();
        }
    };

    /** 'About program' action. */
    private Action aboutAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(aboutButton, "Swing UI Lab. \n(c) Arseny Mayorov, SPbAU");
        }
    };

    /** Exit action. */
    private Action exitAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    /** Window initialization is done here.
     * @param userName name to greet in title.
     * */
    MainWindow(String userName) {
        super("Hello, " + userName + "!");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntmOpen = new JMenuItem(openAction);
        mntmOpen.setHideActionText(true);
        mntmOpen.setText("Open");
        mnFile.add(mntmOpen);

        mntmClose = new JMenuItem(closeAction);
        mntmClose.setHideActionText(true);
        mntmClose.setText("Close");
        mnFile.add(mntmClose);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        mntmAbout = new JMenuItem(aboutAction);
        mntmAbout.setHideActionText(true);
        mntmAbout.setText("About");
        mnFile.add(mntmAbout);

        mntmExit = new JMenuItem(exitAction);
        mntmExit.setHideActionText(true);
        mntmExit.setText("Exit");
        mnFile.add(mntmExit);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        openButton = new JButton(openAction);
        openButton.setHideActionText(true);
        openButton.setText("Open");
        GridBagConstraints c_openButton = new GridBagConstraints();
        c_openButton.fill = GridBagConstraints.HORIZONTAL;
        c_openButton.anchor = GridBagConstraints.NORTH;
        c_openButton.insets = new Insets(0, 0, 5, 5);
        c_openButton.gridx = 0;
        c_openButton.gridy = 0;
        contentPane.add(openButton, c_openButton);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints c_tabbedPane = new GridBagConstraints();
        c_tabbedPane.gridheight = 4;
        c_tabbedPane.insets = new Insets(0, 0, 5, 0);
        c_tabbedPane.fill = GridBagConstraints.BOTH;
        c_tabbedPane.gridx = 1;
        c_tabbedPane.gridy = 0;
        contentPane.add(tabbedPane, c_tabbedPane);

        closeButton = new JButton(closeAction);
        closeButton.setHideActionText(true);
        closeButton.setText("Close");
        GridBagConstraints c_closeButton = new GridBagConstraints();
        c_closeButton.anchor = GridBagConstraints.NORTH;
        c_closeButton.fill = GridBagConstraints.HORIZONTAL;
        c_closeButton.insets = new Insets(0, 0, 5, 5);
        c_closeButton.gridx = 0;
        c_closeButton.gridy = 1;
        contentPane.add(closeButton, c_closeButton);


        aboutButton = new JButton(aboutAction);
        aboutButton.setHideActionText(true);
        aboutButton.setText("About");
        GridBagConstraints c_aboutButton = new GridBagConstraints();
        c_aboutButton.anchor = GridBagConstraints.SOUTH;
        c_aboutButton.fill = GridBagConstraints.HORIZONTAL;
        c_aboutButton.insets = new Insets(0, 0, 5, 5);
        c_aboutButton.gridx = 0;
        c_aboutButton.gridy = 2;
        contentPane.add(aboutButton, c_aboutButton);

        exitButton = new JButton(exitAction);
        exitButton.setHideActionText(true);
        exitButton.setText("Exit");
        GridBagConstraints c_exitButton = new GridBagConstraints();
        c_exitButton.anchor = GridBagConstraints.SOUTH;
        c_exitButton.fill = GridBagConstraints.HORIZONTAL;
        c_exitButton.insets = new Insets(0, 0, 5, 5);
        c_exitButton.gridx = 0;
        c_exitButton.gridy = 3;
        contentPane.add(exitButton, c_exitButton);

        Component verticalGlue = Box.createVerticalGlue();
        GridBagConstraints c_verticalGlue = new GridBagConstraints();
        c_verticalGlue.insets = new Insets(0, 0, 0, 5);
        c_verticalGlue.gridx = 0;
        c_verticalGlue.gridy = 4;
        contentPane.add(verticalGlue, c_verticalGlue);

        updateClose();
    }

    /** Updates status of close button and menu item. */
    public void updateClose() {
        mntmClose.setEnabled(tabbedPane != null && tabbedPane.getTabCount() > 0);
        closeButton.setEnabled(tabbedPane != null && tabbedPane.getTabCount() > 0);
    }

}

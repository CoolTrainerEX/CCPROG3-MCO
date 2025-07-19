package com.ccprog3.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * List adder and remover
 * 
 * @param <E> The type of the elements of this list
 * @author Justin Ryan Uy
 */
public class ListAdder<E> extends JPanel {
    /**
     * List of added elements
     */
    DefaultListModel<E> resultListModel = new DefaultListModel<>();

    /**
     * Constructor for the component
     * 
     * @param listData The array of Objects to be loaded into the data model
     */
    public ListAdder(E[] listData) {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        JList<E> list = new JList<>(listData), resultList = new JList<>(resultListModel);

        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 5, 5);
        add(list, c);
        c.insets = new Insets(0, 5, 5, 0);
        add(resultList, c);
        c.gridy = 1;
        c.weighty = 0;
        c.insets = new Insets(5, 0, 0, 5);
        add(new CustomButton("Add", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultListModel.addElement(list.getSelectedValue());
            }
        }), c);

        c.insets = new Insets(5, 5, 0, 0);
        add(new CustomButton("Remove", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultListModel.removeElement(resultList.getSelectedValue());
            }
        }), c);
    }

    /**
     * Gets the List of added elements
     * 
     * @return List of added elements
     */
    public DefaultListModel<E> getResultList() {
        return resultListModel;
    }
}

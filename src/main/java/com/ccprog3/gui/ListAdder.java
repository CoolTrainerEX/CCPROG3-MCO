package com.ccprog3.gui;

import java.awt.GridLayout;
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
        super(new GridLayout(2, 2, 10, 10));

        JList<E> list = new JList<>(listData), resultList = new JList<>(resultListModel);

        add(list);
        add(resultList);
        add(new CustomButton("Add", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultListModel.addElement(list.getSelectedValue());
            }
        }));

        add(new CustomButton("Remove", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultListModel.removeElement(resultList.getSelectedValue());
            }
        }));
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

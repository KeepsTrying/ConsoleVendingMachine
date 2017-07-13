/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.vendingmachine;

import com.sguild.vendingmachine.vendingmachinecontroller.VendingMachineController;
import com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    /**
     * @param args the command line arguments
     * @throws
     * com.sguild.vendingmachine.vendingmachinedao.VendingMachineDataTransferException
     */
    public static void main(String[] args) throws VendingMachineDataTransferException {

        ApplicationContext thisContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        VendingMachineController controller = thisContext.getBean("controller", VendingMachineController.class);

        controller.run();

        /*
    }

        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VMAuditDao auditDao = new VMAuditDaoImpl();
        VendingMachineServiceLayer myJeeves = new VendingMachineServiceLayerImpl(myDao, auditDao);

        VendingMachineController controller = new VendingMachineController(myJeeves, myView);

        controller.run();

    }
         */
    }
}

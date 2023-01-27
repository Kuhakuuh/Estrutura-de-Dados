/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopartico;

import Enumerations.Estado;
import Locals.Connectors;
import Locals.Portal;
import Management.LocalManagement;

/**
 *
 * @author Tiago Lopes
 */
public class TrabalhoPartico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CalculateDistance calculate = new CalculateDistance();
        
        System.out.println(calculate.distance(40.7656918, 40.7697989, -73.9737489, -73.9723702, 0, 0));
        
        System.out.println("########################################");
        LocalManagement lm = new LocalManagement();
        
        Portal portal1 = new Portal();
        portal1.setNome("Ionia");
        portal1.setId(110);
        portal1.setEstado(Estado.NEUTRO);
        portal1.setEnergyAmount(150);
        portal1.setLatitude(35.432542);
        portal1.setLongitude(-44.973748);
        
        lm.addPortal(portal1);
        Connectors c1 = new Connectors();
        c1.setCooldown(5000);
        c1.setEnergyAmount(140);
        c1.setId(111);
        c1.setLatitude(23.23123);
        c1.setLatitude(-53.2314);
        
        lm.addConnector(c1);
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.dal;

import algebra.project.dal.sql.SqlRepository;

/**
 *
 * @author Kiki
 */
public class RepositoryFactory{

    private RepositoryFactory() {}
    
    public static Repository getRepository() throws Exception{
        return new SqlRepository();
    }
}

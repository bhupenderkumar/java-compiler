package users.cdi;

import common.utils.ReaderUtils;
import engine.pojos.EngineResult;
import engine.service.EngineService;
import roles.dao.RoleDao;
import users.dao.UserDao;

import javax.ejb.EJB;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Kacper on 2014-10-10.
 */
@Named
@ViewScoped
public class TestBean implements Serializable {


    private static final long serialVersionUID = -6035565227100873705L;
    @EJB
    private RoleDao dao;

    @EJB
    private UserDao dao2;

    @EJB
    private EngineService engineService;


}

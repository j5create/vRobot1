package kr.co.bebe.vrobot.interfaces;

import com.chaquo.python.PyObject;

public interface MainInterface {
    void setRobot(PyObject robot);
    PyObject getRobot();
    boolean isConnected();
}

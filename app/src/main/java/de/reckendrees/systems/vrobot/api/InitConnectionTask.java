package kr.co.bebe.vrobot.api;

import android.os.AsyncTask;


import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import kr.co.bebe.vrobot.interfaces.HomeInterface;

public class InitConnectionTask extends AsyncTask<String, Integer, PyObject> {

    private HomeInterface home;

    public InitConnectionTask(HomeInterface home){
        super();
        this.home = home;
    }
    protected PyObject doInBackground(String... inputs) {
        if(inputs.length < 1){
            return null;
        }
        try{
            Python py =  Python.getInstance();
            PyObject ankiLibrary = py.getModule("anki_vector");
            PyObject robot = ankiLibrary.callAttr(
                    "Robot",
                    inputs[0],
                    new Kwarg("ip",inputs[1]),
                    new Kwarg("name",inputs[2])

            );
            robot.callAttr("connect");
            return robot;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }
    protected void onPostExecute(PyObject robot){
        int status = -1;
        if(robot != null){
            status = 1;
        }
        home.updateConnectionStatus(status,robot);

    }

}


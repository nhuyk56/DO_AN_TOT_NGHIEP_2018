package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

import android.os.AsyncTask;
import android.util.Log;

import com.ntu.ynn_shop.ynn_shop.cac_thiet_lap.thiet_lap_server;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class tai_du_lieu_json extends AsyncTask<String, Void, String> {
    private String Method_DN;
    private String PARAM_DN = null;

    public tai_du_lieu_json(String method_DN, String PARAM_DN) {
        Method_DN = method_DN;
        this.PARAM_DN = PARAM_DN;
    }
    protected String doInBackground(String... strings) {
        try{
            String input=strings[0];
            SoapObject requests = new SoapObject(thiet_lap_server.NAME_SPACE, Method_DN);
            if(PARAM_DN != null)
                requests.addProperty(PARAM_DN, input);
            SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(requests);
            HttpTransportSE httpTransportSE = new HttpTransportSE(thiet_lap_server.SERVER_URL);
            httpTransportSE.call(thiet_lap_server.NAME_SPACE+Method_DN, envelope);
            SoapPrimitive data = (SoapPrimitive) envelope.getResponse();
            //if(data.toString() == "Thanh Cong") dialog.setMessage("Thanh Cong");
            //else dialog.setMessage("That bai");
            //dialog.setMessage("That bai");
            //Log.d("ynn123",data.toString());
            return data.toString();
        }
        catch (Exception ex)
        {
            Log.e("loi",ex.toString());
        }
        return null;
    }
}
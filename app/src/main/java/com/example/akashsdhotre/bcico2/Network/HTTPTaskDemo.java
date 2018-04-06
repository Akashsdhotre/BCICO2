package com.example.akashsdhotre.bcico2.Network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.akashsdhotre.bcico2.Interfaces.HTTPCallback;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by Subhodh on 11/4/2016.
 */

public class HTTPTaskDemo extends AsyncTask<String,String,String> {

    public String URLLink="http://...";
    public String method="POST";
    public String jRequest=null;
    public HTTPCallback httpCallback=null;
    private ProgressDialog progressDialog=null;
    private Activity activity=null;

    public int STATUS_CODE=-1;
    public String STATUS_MESSAGE="EXCEPTION";
    int version=-1;
    public void setData(HTTPCallback httpCallback, Activity activity, String method, String URL, String jRequest, int version){
        this.URLLink=URL;
        this.method=method;
        this.jRequest=jRequest;
        this.httpCallback=httpCallback;
        this.activity=activity;
        this.version=version;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(activity);
        progressDialog.setMessage("Connecting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );
            URL url=new URL(URLLink);
            System.out.println("URLLink-"+URLLink);
            HttpURLConnection httpsURLConnection= (HttpURLConnection) url.openConnection();
            //httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
           // httpsURLConnection.setProperty("Accept","*");
          //  httpsURLConnection.setRequestProperty("Content-Type","application/json");
            // httpsURLConnection.addRequestProperty("x-access-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YWJkZTU3NWU5NDE0MzBlZDk2YmQ5ZWQiLCJmaXJzdE5hbWUiOiJha2FzaCIsImxhc3ROYW1lIjoiZGhvdHJlIiwiZW1haWwiOiJha2FzaHNkaG90cmVAZ21haWwuY29tIiwiY2l0eSI6InB1bmUiLCJpbnRyZXN0IjoiZGV2ZWxvcG1lbnRfY2lyY2xlIiwiaW52ZXN0b3IiOiJzdHVkZW50IiwiZnJvbVJlZmVyZW5jZSI6IjEyMyIsInBob25lTnVtYmVyIjo5ODIzNzA0ODcwLCJwcm9maWxlSW1hZ2UiOiIvaG9tZS91c2VyL0RvY3VtZW50cy9CQ0lDT19XaG9sZV9Qcm9qZWN0L2JjaWNvL2NvbnRyb2xsZXJzLy4uL3B1YmxpYy9wcm9maWxlL3Byb2ZpbGUucG5nIiwibGV2ZWxGbGFnIjp0cnVlLCJyZWZlcmVuY2UiOiJha2FzaF9kaG90cmU0NDEiLCJwYXNzd29yZCI6InNoYTEkMWRkYjY0YjckMSRlYWIwODE2ZTFkZTc2NzIxZTk0MmQxZDMyOWRhNDZlMWE2YzIwNWJhIiwicmV3YXJkUG9pbnQiOjEwMCwiX192IjowLCJpc0FjdGl2ZSI6dHJ1ZSwiaXNBdXRoZW50aWNhdGVkIjp0cnVlLCJwb3N0RGF0YUludHJlc3QiOlt7InRleHQiOiJ0cmFkaW5nIiwiZmxhZyI6dHJ1ZSwiX2lkIjoiNWFiZGU1NzVlOTQxNDMwZWQ5NmJkYTAwIn0seyJ0ZXh0IjoidHJhaW5pbmciLCJmbGFnIjp0cnVlLCJfaWQiOiI1YWJkZTU3NWU5NDE0MzBlZDk2YmQ5ZmYifSx7InRleHQiOiJwcm9qZWN0cyIsImZsYWciOnRydWUsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmZSJ9LHsidGV4dCI6Im1pbmluZyIsImZsYWciOnRydWUsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmZCJ9LHsidGV4dCI6ImV2ZW50cyIsImZsYWciOnRydWUsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmYyJ9LHsidGV4dCI6ImNvbnN1bHRhbmN5IiwiZmxhZyI6dHJ1ZSwiX2lkIjoiNWFiZGU1NzVlOTQxNDMwZWQ5NmJkOWZiIn0seyJ0ZXh0Ijoib3RoZXIiLCJmbGFnIjp0cnVlLCJfaWQiOiI1YWJkZTU3NWU5NDE0MzBlZDk2YmQ5ZmEifSx7InRleHQiOiJpbnZlc3RfY2lyY2xlIiwiZmxhZyI6dHJ1ZSwiX2lkIjoiNWFiZGU1NzVlOTQxNDMwZWQ5NmJkOWY5In0seyJ0ZXh0IjoiZGV2ZWxvcG1lbnRfY2lyY2xlIiwiZmxhZyI6dHJ1ZSwiX2lkIjoiNWFiZGU1NzVlOTQxNDMwZWQ5NmJkOWY4In0seyJ0ZXh0IjoidHJhaW5lZV9jaXJjbGUiLCJmbGFnIjp0cnVlLCJfaWQiOiI1YWJkZTU3NWU5NDE0MzBlZDk2YmQ5ZjcifSx7InRleHQiOiJuZXdzX2NpcmNsZSIsImZsYWciOnRydWUsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmNiJ9LHsidGV4dCI6ImJsb2dzX2NpcmNsZSIsImZsYWciOnRydWUsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmNSJ9LHsidGV4dCI6ImxlYXJuaW5nX2NpcmNsZSIsImZsYWciOnRydWUsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmNCJ9LHsidGV4dCI6ImJ1c2luZXNzX2NpcmNsZSIsImZsYWciOnRydWUsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmMyJ9XSwibGV2ZWwiOlt7Im5hbWUiOiJsZXZlbF8xIiwidmFsdWUiOiIxIiwiX2lkIjoiNWFiZGU1NzVlOTQxNDMwZWQ5NmJkOWYyIn0seyJuYW1lIjoibGV2ZWxfMiIsInZhbHVlIjoiMiIsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDlmMSJ9LHsibmFtZSI6ImxldmVsXzMiLCJ2YWx1ZSI6IjMiLCJfaWQiOiI1YWJkZTU3NWU5NDE0MzBlZDk2YmQ5ZjAifSx7Im5hbWUiOiJsZXZlbF80IiwidmFsdWUiOiI0IiwiX2lkIjoiNWFiZGU1NzVlOTQxNDMwZWQ5NmJkOWVmIn0seyJuYW1lIjoibGV2ZWxfNSIsInZhbHVlIjoiNSIsIl9pZCI6IjVhYmRlNTc1ZTk0MTQzMGVkOTZiZDllZSJ9XSwiZXZlbnRJZCI6W10sImlhdCI6MTUyMjc2OTAwOCwiZXhwIjozMDQ1NTQxNjE2fQ.0pCfNL-aXFl-iccRrPyJCNKqfnEUS9nqb7C8lsSB2Yw");
            httpsURLConnection.setRequestMethod(method);

            // httpsURLConnection.setConnectTimeout(100);
            // httpsURLConnection.setReadTimeout(100);

            //creating output stream and write request
//            OutputStream OS=httpsURLConnection.getOutputStream();
//            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
//            writer.write(jRequest);
//            writer.flush();
//            writer.close();
//            OS.close();

            //check connectivity and get response
            httpsURLConnection.connect();
            STATUS_CODE=httpsURLConnection.getResponseCode();
            STATUS_MESSAGE=httpsURLConnection.getResponseMessage();
            System.out.println(STATUS_CODE+"--****---"+STATUS_MESSAGE);
            //****
            InputStream IN=new BufferedInputStream(httpsURLConnection.getInputStream());
            InputStreamReader SReader=new InputStreamReader(IN);
            BufferedReader BR=new BufferedReader(SReader);
            StringBuilder daata=new StringBuilder();
            String chunks;
            while ((chunks=BR.readLine())!=null){
                daata.append(chunks);
            }
            System.out.println("Data***********200  "+daata);
            //****
            if(STATUS_CODE==200){

                return daata.toString();
            }
            else{
                System.out.println("No 200  ");
                return null;
            }

        }catch (ProtocolException pE) {
            pE.printStackTrace();
            this.STATUS_CODE = -5;
            this.STATUS_MESSAGE = "Protocol Exception";

            return null;
        } catch (UnsupportedEncodingException usE) {
            usE.printStackTrace();
            this.STATUS_CODE = -4;
            this.STATUS_MESSAGE = "Unsupported Encoding Exception";
            return null;
        } catch (MalformedURLException murlE) {
            murlE.printStackTrace();
            this.STATUS_CODE = -3;
            this.STATUS_MESSAGE = "Malformed URL Exception";
            return null;
        } catch (IOException ioE) {
            ioE.printStackTrace();
            this.STATUS_CODE = -2;
            this.STATUS_MESSAGE = "Connection Failed";
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            this.STATUS_CODE = -1;
            this.STATUS_MESSAGE = e.getMessage().trim();
            return null;
        }

    }

    @Override
    protected void onPostExecute(String data) {
        System.out.println("*********************************");
        System.out.println("onPostExecute HTTPTask   " + data);
        if(data!=null){
           /*Log.d("Server API data",data);
           Log.d("Server response code",""+STATUS_CODE);
           Log.d("server response code",STATUS_MESSAGE);*/
        }
        super.onPostExecute(STATUS_MESSAGE);
        progressDialog.hide();
        progressDialog.dismiss();
        if(STATUS_CODE==200){
            System.out.println("calling on success");
            this.httpCallback.onSuccess(STATUS_CODE,STATUS_MESSAGE,data,version);
        }
        else if (STATUS_CODE < 0)     //Exceptions while processing
        {
            this.httpCallback.onError(STATUS_CODE, STATUS_MESSAGE);
        } else                          //Other Response from server
        {
            this.httpCallback.onFailure(STATUS_CODE, STATUS_MESSAGE);
        }
    }


    @Override
    protected void finalize() throws Throwable {
        URLLink=null;
        method=null;
        jRequest=null;
        httpCallback=null;
        STATUS_MESSAGE=null;
        super.finalize();
    }
}

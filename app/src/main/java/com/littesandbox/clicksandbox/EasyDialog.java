package com.littesandbox.clicksandbox;
import android.app.AlertDialog;
import android.content.Context;

public class EasyDialog
{
	public AlertDialog.Builder dialog;
	//**纯文字dialog**/
    public void setButtonListener()
    {
   //     dialog.setButton();
    }
    public void init(Context ctx)
    {
        //dialog =new AlertDialog.Builder(ctx).create();
        dialog=new AlertDialog.Builder(ctx);

    }
    
   public void setMessage(String title,String message)
   {//ctx必须是this getappcontext会闪退
	   dialog.setMessage(message);
	   dialog.setTitle(title);
   }
   public void show()
   {
       dialog.show();
   }
}

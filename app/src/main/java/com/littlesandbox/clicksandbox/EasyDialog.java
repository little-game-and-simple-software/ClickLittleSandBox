package com.littlesandbox.clicksandbox;
import android.app.AlertDialog;
import android.content.Context;

public class EasyDialog
{
	public AlertDialog builder;
	//**纯文字dialog**/
    public void setButtonListener()
    {
   //     builder.setButton();
    }
    public void init(Context ctx)
    {
        builder=new AlertDialog.Builder(ctx).create();
    }
    
   public void setMessage(String title,String message)
   {//ctx必须是this getappcontext会闪退
	   builder.setMessage(message);
	   builder.setTitle(title);
   }
   public void show()
   {
       builder.show();
   }
}

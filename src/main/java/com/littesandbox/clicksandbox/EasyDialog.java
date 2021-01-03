package com.littesandbox.clicksandbox;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class EasyDialog
{
	AlertDialog builder;
	//**纯文字dialog**/
    public void setButtonListener()
    {
   //     builder.setButton();
    }
   public void show(final Context ctx,String title,String message)
   {//ctx必须是this getappcontext会闪退
	   builder=new AlertDialog.Builder(ctx).create();
	   builder.setMessage(message);
	   builder.setTitle(title);
	   builder.setButton("确认",new DialogInterface.OnClickListener()
		   {
			   @Override
			   public void onClick(DialogInterface p1, int p2) 
			   {
				   Toast.makeText(ctx,"内部调用",1000).show();
			   }
	   });
	   builder.show();
   }
}

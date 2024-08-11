package be.heh.threadapp

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class BackgroundTask {
    private val MESSAGE_PRE_EXECUTE =1
    private val MESSAGE_PROGRESS_UPDATE =2
    private val MESSAGE_POST_EXECUTE =3

    private var pbMainProgressionthha1:ProgressBar?=null
    private var btMainStartthha:Button?=null
    private var viMainUi:View?=null
    constructor(v:View,b:Button,p:ProgressBar){
        pbMainProgressionthha1=p
        btMainStartthha=b
        viMainUi=v
    }
    private fun downloadPreExecute(){
        btMainStartthha?.visibility = View.GONE
        pbMainProgressionthha1!!.visibility =View.VISIBLE
        Toast.makeText(
            viMainUi?.context,
            "Le traitement de la tâche de fond est démarré !",
            Toast.LENGTH_SHORT).show()
    }
    private fun downloadProgressUpdate(progress:Int){
        Log.i("var progress : ", progress.toString())
        pbMainProgressionthha1!!.progress=progress
        Log.i("pb_main_progression: ",pbMainProgressionthha1!!.progress.toString())
    }
    private fun downloadPostExecute(){
        Toast.makeText(viMainUi?.context,
            "Le traitement de la tâche de fond est terminé !!!",
            Toast.LENGTH_SHORT).show()
        btMainStartthha?.visibility =View.VISIBLE
        pbMainProgressionthha1!!.visibility = View.GONE
    }
    private val monHandler:Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                MESSAGE_PRE_EXECUTE -> downloadPreExecute()
                MESSAGE_PROGRESS_UPDATE -> downloadProgressUpdate(msg.arg1)
                MESSAGE_POST_EXECUTE-> downloadPostExecute()
                else->{}
            }
        }
    }
    private fun sendPostExecuteMessage(){
        val postExecuteMessage=Message()
        postExecuteMessage.what = MESSAGE_POST_EXECUTE
        monHandler.sendMessage(postExecuteMessage)
    }
    private fun sendPreExecuteMessage() {
        val preExecuteMessage = Message()
        preExecuteMessage.what = MESSAGE_PRE_EXECUTE
        monHandler.sendMessage(preExecuteMessage)
    }
    private fun sendProgressMessage(i: Int) {
        val progressMessage = Message()
        progressMessage.what = MESSAGE_PROGRESS_UPDATE
        progressMessage.arg1 = i * 10
        monHandler.sendMessage(progressMessage)
    }
    private var monThread = Thread(){
        try {
            sendPreExecuteMessage()
            for (i in 0..19) {
                sendProgressMessage(i)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            sendPostExecuteMessage()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun Start(){
        if (!monThread.isAlive) monThread.start();
    }
}
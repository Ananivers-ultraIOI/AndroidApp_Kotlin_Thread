package be.heh.threadapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import be.heh.threadapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    lateinit var monThread:ThreadTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun xmlClicEvent(v:View){
        when(v.id){
            binding.btMainTxtchange.id -> bpModifTexte()
            binding.btMainStartTH.id -> bpThreadGo()
            binding.btMainStartAS.id -> bpAsyncTask(v)
            binding.btMainStartTrHa.id -> bpThreadHandler(v)
        }
    }
    @SuppressLint("SetTextI18n")
    fun bpModifTexte(){
        if(binding.tvMainTexte.text.equals("Hello World !")) binding.tvMainTexte.text = "Hey Android !"
        else binding.tvMainTexte.text = "Hello World !"
    }
    @SuppressLint("SetTextI18n")
    fun bpThreadGo(){
        if (binding.btMainStartTH.text.equals("Thread GO !")){
            monThread = ThreadTest(binding.pbMainProgressionTH)
            monThread .Go()
            binding.btMainStartTH.text = "Thread Stop !"
            Toast.makeText(applicationContext,"Activation du Thread",Toast.LENGTH_LONG).show()
        }else{
            monThread.Stop()
            Toast.makeText(applicationContext,"Activation du Thread",Toast.LENGTH_LONG).show()
            binding.btMainStartTH.text = "Thread GO !"
        }
    }
    private fun bpAsyncTask(v:View){
        val asyncrotask = AsyncroTask(v, binding.btMainStartAS,binding.pbMainProgressionAS)
        asyncrotask.execute("paramètres --->","<--- de traitement")
    }
    private fun bpThreadHandler(v:View){
        val bkgd1=BackgroundTask(v,binding.btMainStartTrHa,binding.pbMainProgressionTrHa1)
        bkgd1.Start()
        val bkgd2=BackgroundTask(v,binding.btMainStartTrHa,binding.pbMainProgressionTrHa2)
        bkgd2.Start()
    }
}
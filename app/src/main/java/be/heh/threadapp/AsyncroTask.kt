package be.heh.threadapp

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class AsyncroTask(v: View?, b: Button, p: ProgressBar) : AsyncTask<String, Int, String>() {
    @SuppressLint("StaticFieldLeak")
    private var pbMainProgressionas: ProgressBar? = p
    @SuppressLint("StaticFieldLeak")
    private var btMainStartas: Button? = b
    @SuppressLint("StaticFieldLeak")
    private var viMainUi: View? = v

    @Deprecated("Deprecated in Java")
    override fun onPreExecute() {
        super.onPreExecute()
// Mise à jour de l'interface
        btMainStartas?.visibility = View.GONE
        pbMainProgressionas!!.visibility = View.VISIBLE
        Toast.makeText(
            viMainUi?.context, "Démarrage de la tâche de fond AsyncTask",
            Toast.LENGTH_LONG)
            .show()
    }
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): String? {
        Log.i("Paramètre : ", params[0].toString() + params[1].toString())
        var result = ""
//remplissage de la barre de progression avec onProgressUpdate() !
        for (i in 0..19 ) {
//pb_main_progressionAS!!.progress=(i*5)
            onProgressUpdate(i * 5)
            result += i.toString()
            SystemClock.sleep(500)
        }
        return result
    }
    private fun onProgressUpdate(progress: Int) {
//super.onProgressUpdate(progress)
        pbMainProgressionas!!.progress = progress
    }
    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        Toast.makeText(
            viMainUi?.context, "Fin de l'exécution de la tâche de fond AsyncTask"
                    + result,
            Toast.LENGTH_LONG)
            .show()
// Mise à jour de l'interface
        pbMainProgressionas!!.visibility = View.GONE
        btMainStartas?.visibility = View.VISIBLE
    }
}
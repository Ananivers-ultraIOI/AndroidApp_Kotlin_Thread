package be.heh.threadapp

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.atomic.AtomicBoolean

class ThreadTest(pb: ProgressBar) {
    private var pb_main_progressionTH: ProgressBar? = pb
    private var is_Running: AtomicBoolean = AtomicBoolean(false)
    private lateinit var monThread: Thread

    fun Go() {
        pb_main_progressionTH!!.progress = 0
        monThread = Thread(Runnable() {
            var i = 0
            while (i < 20 && is_Running.get()) {
                pb_main_progressionTH!!.incrementProgressBy(5)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                i++
            }
        })
        is_Running.set(true)
        monThread!!.start()
    }
    fun Stop(){
        is_Running.set(false);
        monThread!!.interrupt();
        pb_main_progressionTH!!.setProgress(0);
    }
}
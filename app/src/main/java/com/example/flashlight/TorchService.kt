package com.example.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TorchService : Service() {
    // TorchService class should use Torch class
    private val torch: Torch by lazy{   // initialize torch object when the object is first used
        Torch(this)
    }

    private var isRunning = false

    // 외부에서 startService() method로 TorchService 서비스를 호출하면 onStartCommand() callback method가 호출됨
    // 보통 intent에 action값을 설정하여 호출함.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            // 앱에서 실행하는 경우
            "on" -> {
                torch.flashOn()
                isRunning = true
            }
            "off" -> {
                torch.flashOff()
                isRunning = false
            }
            // 서비스에서 실행하는 경우(액션값이 설정되지 않음)
            else ->{
                isRunning = !isRunning
                if(isRunning){
                    torch.flashOn()
                }else{
                    torch.flashOff()
                }
            }
        }

        // onStartCommand()는 START_STICKY, START_NOT_STICKY, START_REDELIVER_INTENT중 하나를 return함
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}

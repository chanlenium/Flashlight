package com.example.flashlight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val torch = Torch(this)

        // flashSwitch is button object having values on or off
        flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                torch.flashOn()
            }else{
                torch.flashOff()
            }
        }
        */

        // flashSwitch is button object having values on or off
        flashSwitch.setOnCheckedChangeListener { _, isChecked ->
            val intent = Intent(this, TorchService::class.java)
            if(isChecked){
                intent.action = "on"
                //startService(intentFor<TorchService>().setAction("on"))
            }else{
                intent.action = "off"
                //startService(intentFor<TorchService>().setAction("off"))
            }
            startService(intent)
        }
    }
}

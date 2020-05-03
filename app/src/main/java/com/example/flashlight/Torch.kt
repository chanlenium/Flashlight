package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

// CameraManager 클래스를 사용하여 플래시 기능 제어
// Provide methods flashOn() and flashOff()
class Torch(context: Context) { // To power on, CameraManager object is needed. And to get this object, Context object is needed, so the type of parameter of Torch is Context
    private var cameraId: String? = null    // declare variable to store cameraId which is unique for each device
    // getSystemService() method creates manager class which manages various services provided in Android
    // Since this method() returns object type, "as" is used to cast type
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init {
        cameraId = getCameraId()    // initialize variable "cameraId"
    }

    private fun getCameraId(): String?{
        val cameraIds = cameraManager.cameraIdList  // get camera ID list
        for(id in cameraIds){
            val info = cameraManager.getCameraCharacteristics(id)   // get camera characteristics for the id
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)   // get binary value to check flash is available
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)    // get infomation about the camera direction
            if(flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id   // if the camera ID is figured out, return id
            }
        }
        return null // if there is no available id, return null
    }

    fun flashOn(){
        cameraId?.let { cameraManager.setTorchMode(it, true) }
    }

    fun flashOff(){
        cameraId?.let { cameraManager.setTorchMode(it, false) }
    }
}


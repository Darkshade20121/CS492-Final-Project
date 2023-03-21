//package com.example.riot.ui
//
//import android.app.IntentService
//import android.app.NotificationManager
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.PixelFormat
//import android.hardware.display.DisplayManager
//import android.media.ImageReader
//import android.media.projection.MediaProjection
//import android.os.Environment
//import android.os.Parcelable
//import android.util.DisplayMetrics
//import android.view.View
//import android.view.WindowManager
//import androidx.core.app.NotificationCompat
//import com.example.riot.R
//import java.io.File
//import java.io.FileOutputStream
//
//const val EXTRA_MEDIA_PROJECTION = "media_projection"
//private val NOTIFICATION_ID = 1234
//
//class ScreenshotService : IntentService("ScreenshotService") {
//
//    private var mImageReader: ImageReader? = null
//    private var mWindowManager: WindowManager? = null
//    private var mDisplayMetrics: DisplayMetrics? = null
//
//    override fun onHandleIntent(intent: Intent?) {
//        // Get the MediaProjection from the Intent
//        val mediaProjection = intent?.getParcelableExtra<Parcelable>(EXTRA_MEDIA_PROJECTION) as? MediaProjection
//
//
//
//        // Get the screen dimensions
//        mDisplayMetrics = resources.displayMetrics
//        val width = mDisplayMetrics!!.widthPixels
//        val height = mDisplayMetrics!!.heightPixels
//
//        // Create an ImageReader to capture the screenshot
//        mImageReader = ImageReader.newInstance(width, height, PixelFormat.RGBA_8888, 1)
//        val virtualDisplay = mediaProjection?.createVirtualDisplay("Screenshot",
//            width, height, mDisplayMetrics!!.densityDpi,
//            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
//            mImageReader!!.surface, null, null)
//
//        // Capture the screenshot
//        val image = mImageReader!!.acquireLatestImage()
//
//        // Convert the Image to a Bitmap
//        val planes = image.planes
//        val buffer = planes[0].buffer
//        val pixelStride = planes[0].pixelStride
//        val rowStride = planes[0].rowStride
//        val rowPadding = rowStride - pixelStride * width
//        val bitmap = Bitmap.createBitmap(
//            width + rowPadding / pixelStride,
//            height, Bitmap.Config.ARGB_8888
//        )
//        bitmap.copyPixelsFromBuffer(buffer)
//
//        // Save the Bitmap to a file
//        val filename = "screenshot_${System.currentTimeMillis()}.png"
//        val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename)
//        val fos = FileOutputStream(file)
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
//        fos.close()
//
//        // Show a notification with the file path
//        val notification = NotificationCompat.Builder(this, NotificationManager.IMPORTANCE_DEFAULT)
//            .setContentTitle("Screenshot saved")
//            .setContentText(file.absolutePath)
//            .setSmallIcon(androidx.core.R.drawable.notification_action_background) // Change this maybe?
//            .build()
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(NOTIFICATION_ID, notification)
//
//        // Clean up resources
//        image.close()
//        virtualDisplay?.release()
//        mediaProjection?.stop()
//        mImageReader?.surface?.release()
//        mImageReader?.close()
//        val mOverlayView = View.inflate(this, androidx.wear.R.layout.ws_overlay_confirmation, null)
//        mWindowManager?.removeViewImmediate(mOverlayView)
//    }
//}

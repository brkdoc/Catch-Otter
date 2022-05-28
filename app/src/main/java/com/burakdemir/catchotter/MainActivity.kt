package com.burakdemir.catchotter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()
    var runnable= Runnable {  }
    var handler=Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        imageArray.add(otterImage1)
        imageArray.add(otterImage2)
        imageArray.add(otterImage3)
        imageArray.add(otterImage4)
        imageArray.add(otterImage5)
        imageArray.add(otterImage6)
        imageArray.add(otterImage7)
        imageArray.add(otterImage8)
        imageArray.add(otterImage9)

        hideImages()


        object:CountDownTimer(15500,1000){

            override fun onTick(millisUntilFinished: Long) {
                timeTxt.text="Time : ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for (images in imageArray){
                    images.visibility=View.INVISIBLE
                }
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game ?")
                alert.setPositiveButton("Yes"){dialog,which->
                    val intent=intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                    finish()
                }
                alert.show()
            }

        }.start()

    }
    fun increaseScore(view: View){
        score++
        resultTxt.text="Result : $score"
    }

    fun hideImages(){
        runnable=object:Runnable{
            override fun run() {
                for(images in imageArray){
                    images.visibility=View.INVISIBLE
                }
                val random=Random()
                var randomNum=random.nextInt(9)
                imageArray[randomNum].visibility=View.VISIBLE
                handler.postDelayed(runnable,700)
            }

        }
        handler.post(runnable)
    }
}
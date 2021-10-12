package com.main.kotlin.activity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


import android.view.View
import android.widget.Toast


import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager

import android.net.Uri
import android.os.Environment
import android.util.Log
import android.content.Intent

import android.content.BroadcastReceiver
import androidx.core.content.FileProvider
import com.main.kotlin.R
import java.io.File
import java.net.URL


class UrlAndDownload : AppCompatActivity() {
    val uriImage: String = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"
    val uriPdef: String = "https://www.clickdimensions.com/links/TestPDFfile.pdf"
    val REQUEST_PERMISSION = 100
    var fileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_and_download)
        val ImageDownload: Button = findViewById(R.id.ImageUrl)
        val PdfDownload: Button = findViewById(R.id.PdfUrl)


        ImageDownload.setOnClickListener(View.OnClickListener {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION
                )

            } else {
                downloadImage(uriImage, "image/*")
            }
        })
        PdfDownload.setOnClickListener(View.OnClickListener {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION
                )

            } else {
                downloadImage(uriPdef, "application/pdf")
            }

        })

    }

    fun downloadImage(url: String, mimetype: String) {

        val urlFileName = URL(url)
        fileName = urlFileName.path
        fileName = fileName?.substring(fileName?.lastIndexOf('/')!! + 1)

        Log.i("paras", "downloadImage:$urlFileName      $fileName")

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_WIFI or
                    DownloadManager.Request.NETWORK_MOBILE
        )
        request.setTitle(fileName)
        request.setDescription("Android Data download using DownloadManager.")

        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setVisibleInDownloadsUi(true)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS, fileName
        )
        request.setMimeType(mimetype)
        val SucessId: Long = downloadManager.enqueue(request)

        val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {

                if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
                    Toast.makeText(this@UrlAndDownload, "Download Completed", Toast.LENGTH_SHORT)
                        .show()
                    intent.extras?.let {
                        val fileDownload = File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                .toString() + "/" + fileName
                        )
                        val uriForDownloadFile: Uri = FileProvider.getUriForFile(
                            this@UrlAndDownload,
                            "com.main.kotlin" + ".fileprovider",
                            fileDownload
                        )

                        // Log.i("paras", "onReceive: fileName:$fileName  \npath:$fileDownload   $uriForDownloadFile")
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setDataAndType(uriForDownloadFile, mimetype)
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        context.startActivity(intent)
                    }
                }
            }
        }
        registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


